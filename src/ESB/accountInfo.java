package ESB;


/**
* ESB/accountInfo.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月9日 7時45分48秒 JST
*/

public final class accountInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String address = null;
  public String zipCode = null;
  public String country = null;
  public String number = null;
  public String array = null;

  public accountInfo ()
  {
  } // ctor

  public accountInfo (String _address, String _zipCode, String _country, String _number, String _array)
  {
    address = _address;
    zipCode = _zipCode;
    country = _country;
    number = _number;
    array = _array;
  } // ctor

} // class accountInfo
