package ESB;

/**
* ESB/accountInfoHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月15日 7時10分12秒 JST
*/

public final class accountInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public ESB.accountInfo value = null;

  public accountInfoHolder ()
  {
  }

  public accountInfoHolder (ESB.accountInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ESB.accountInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ESB.accountInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ESB.accountInfoHelper.type ();
  }

}
