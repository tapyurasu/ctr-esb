package ESB;


/**
* ESB/userInfo.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月15日 7時10分12秒 JST
*/

public final class userInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String userName = null;
  public String userId = null;
  public String phoneNumber = null;
  public String accountInfo = null;

  public userInfo ()
  {
  } // ctor

  public userInfo (String _userName, String _userId, String _phoneNumber, String _accountInfo)
  {
    userName = _userName;
    userId = _userId;
    phoneNumber = _phoneNumber;
    accountInfo = _accountInfo;
  } // ctor

} // class userInfo
