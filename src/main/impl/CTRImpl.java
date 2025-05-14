package main.impl;

import org.omg.CORBA.ORB;

import ESB.CTRPOA;
import ESB.accountInfo;
import ESB.accountInfoHolder;
import ESB.reqObj;
import ESB.userInfo;
import ESB.userInfoHolder;
import main.common.util.CorbaRestBridge;

public class CTRImpl extends CTRPOA {
	private ORB orb;
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	@Override
	public int sayHello(reqObj req, userInfoHolder userInfo, accountInfoHolder accountInfo) {
		// TODO 自動生成されたメソッド・スタブ 
		try {
			// マッピング定義読み込み
			CorbaRestBridge bridge = new CorbaRestBridge("src/main/resources/mapper/mapping.properties");

			// RESTリクエストJSONを作成
			String jsonRequest = bridge.buildRestRequestJson("getUserInfo", req);
			System.out.println("★ RESTリクエスト:");
			System.out.println(jsonRequest);
			
			// 実際にREST APIを呼び出す（URLとメソッドは仮）
			String apiUrl = "http://localhost:8080/api/user";
			String method = "POST";
			
			String jsonResponse = bridge.callRestApi(apiUrl, method, jsonRequest);
			System.out.println("★ RESTレスポンス:");
			System.out.println(jsonResponse);
			
			// CORBAレスポンス構造体に変換
			userInfo output = (userInfo) bridge.convertRestResponseToCorbaOutput(
			    "getUserInfo", jsonResponse, userInfo.class
			);
			
			// 結果出力
			output.accountInfo = "aaaa";
			System.out.println("\n★ CORBAレスポンス:");
			System.out.println("UserName: " + output.userName);
			System.out.println("UserId: " + output.userId);
			System.out.println("PhoneNumber: " + output.phoneNumber);
			System.out.println("AccountInfo: " + output.accountInfo);
//			System.out.println("result: " + output.result);
			
			userInfo.value = output;
			accountInfo.value = new accountInfo("dummyaddress", "dumzip", "japan", "num", "array");
			return 0;
		} catch (Exception e) {
	        System.err.println("エラー発生: " + e.getMessage());
	        e.printStackTrace();
	        return 1;
		}
	}
}