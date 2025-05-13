package main;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ESB.CTR;
import ESB.CTRHelper;
import ESB.CTRPOA;
import ESB.accountInfoHolder;
import ESB.reqObj;
import ESB.userInfoHolder;
import main.model.CorbaOutput;
import main.util.CorbaRestBridge;

class CTRImpl extends CTRPOA {
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
			CorbaOutput output = (CorbaOutput) bridge.convertRestResponseToCorbaOutput(
			    "getUserInfo", jsonResponse, CorbaOutput.class
			);
			
			// 結果出力
			System.out.println("\n★ CORBAレスポンス:");
			System.out.println("UserName: " + output.UserName);
			System.out.println("UserId: " + output.UserId);
			System.out.println("PhoneNumber: " + output.PhoneNumber);
			System.out.println("AccountInfo: " + output.AccountInfo);
			System.out.println("result: " + output.result);
		} catch (Exception e) {
        System.err.println("エラー発生: " + e.getMessage());
        e.printStackTrace();
		}
		return 0;
	}
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);
			
			// get reference to rootpoa and activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
			// create servant and register it with the ORB
			CTRImpl helloImpl = new CTRImpl();
			helloImpl.setORB(orb); 
			
			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			CTR href = CTRHelper.narrow(ref);
			    
			// get the root naming context
			org.omg.CORBA.Object objRef =
			    orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// bind the Object Reference in Naming
			String name = "Hello";
			NameComponent path[] = ncRef.to_name( name );
			ncRef.rebind(path, href);
			
			System.out.println("HelloServer ready and waiting ...");
			
			// wait for invocations from clients
			orb.run();
          } catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
          }
        System.out.println("HelloServer Exiting ...");
    }
}
