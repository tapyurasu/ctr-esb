package ESB;

/**
* ESB/userInfoHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月15日 7時10分12秒 JST
*/

public final class userInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public ESB.userInfo value = null;

  public userInfoHolder ()
  {
  }

  public userInfoHolder (ESB.userInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ESB.userInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ESB.userInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ESB.userInfoHelper.type ();
  }

}
