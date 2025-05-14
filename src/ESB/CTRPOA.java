package ESB;


/**
* ESB/CTRPOA.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月15日 7時10分12秒 JST
*/

public abstract class CTRPOA extends org.omg.PortableServer.Servant
 implements ESB.CTROperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sayHello", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ESB/CTR/sayHello
       {
         ESB.reqObj req = ESB.reqObjHelper.read (in);
         ESB.userInfoHolder userInfo = new ESB.userInfoHolder ();
         ESB.accountInfoHolder accountInfo = new ESB.accountInfoHolder ();
         int $result = (int)0;
         $result = this.sayHello (req, userInfo, accountInfo);
         out = $rh.createReply();
         out.write_long ($result);
         ESB.userInfoHelper.write (out, userInfo.value);
         ESB.accountInfoHelper.write (out, accountInfo.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ESB/CTR:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CTR _this() 
  {
    return CTRHelper.narrow(
    super._this_object());
  }

  public CTR _this(org.omg.CORBA.ORB orb) 
  {
    return CTRHelper.narrow(
    super._this_object(orb));
  }


} // class CTRPOA
