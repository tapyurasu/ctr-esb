package main.common.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * CORBAのリクエスト・レスポンスとREST APIの橋渡しを行うクラス
 */
public class CorbaRestBridge {
    private Properties mapping;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * コンストラクタ：マッピングプロパティを読み込む
     */
    public CorbaRestBridge(String mappingPath) throws IOException {
        mapping = new Properties();
        try (InputStream in = new FileInputStream(mappingPath)) {
            mapping.load(in);
        }
    }

    /**
     * CORBAのリクエストオブジェクトからREST用のJSONを生成
     */
    public String buildRestRequestJson(String apiName, Object corbaRequestObj) throws Exception {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        Class<?> clazz = corbaRequestObj.getClass();

        for (String key : mapping.stringPropertyNames()) {
            if (!key.startsWith(apiName + ".request.")) continue;

            String corbaField = key.substring((apiName + ".request.").length());
            String jsonKey = mapping.getProperty(key);
            try {
                Object value = clazz.getField(corbaField).get(corbaRequestObj);
                if (value != null) {
                    jsonMap.put(jsonKey, value.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapper.writeValueAsString(jsonMap);
    }

    /**
     * REST APIを呼び出してJSONレスポンスを取得
     */
    public String callRestApi(String url, String method, String jsonBody) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // リクエストボディを送信
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes("UTF-8"));
        }

        // レスポンスを受信
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) response.append(line);
        in.close();

        return response.toString();
    }

    /**
     * RESTのJSONレスポンス文字列をCORBAのレスポンスオブジェクトに変換
     */
    public Object convertRestResponseToCorbaOutput(String apiName, String jsonString, Class<?> corbaOutputClass) throws Exception {
        ObjectNode root = (ObjectNode) mapper.readTree(jsonString);
        Object resultObj = corbaOutputClass.newInstance();

        for (String key : mapping.stringPropertyNames()) {
            if (!key.startsWith(apiName + ".response.")) continue;

            String jsonPath = key.substring((apiName + ".response.").length());
            String corbaField = mapping.getProperty(key);
            JsonNode valueNode = getJsonValue(root, jsonPath);

            try {
                if (valueNode != null) {
                    String valStr = valueNode.isValueNode() ? valueNode.asText() : valueNode.toString();
                    corbaOutputClass.getField(corbaField).set(resultObj, valStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultObj;
    }

    /**
     * JSONノードからネストパスを辿って値を取得
     */
    private JsonNode getJsonValue(JsonNode root, String path) {
        String[] keys = path.split("\\.");
        JsonNode node = root;
        for (String key : keys) {
            node = node.get(key);
            if (node == null) return null;
        }
        return node;
    }
}