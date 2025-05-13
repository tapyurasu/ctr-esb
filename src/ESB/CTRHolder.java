package ESB;

/**
* ESB/CTRHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月9日 7時45分48秒 JST
*/

public final class CTRHolder implements org.omg.CORBA.portable.Streamable
{
  public ESB.CTR value = null;

  public CTRHolder ()
  {
  }

  public CTRHolder (ESB.CTR initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ESB.CTRHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ESB.CTRHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ESB.CTRHelper.type ();
  }

}
