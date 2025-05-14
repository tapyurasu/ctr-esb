package ESB;

/**
* ESB/reqObjHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月15日 7時10分12秒 JST
*/

public final class reqObjHolder implements org.omg.CORBA.portable.Streamable
{
  public ESB.reqObj value = null;

  public reqObjHolder ()
  {
  }

  public reqObjHolder (ESB.reqObj initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ESB.reqObjHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ESB.reqObjHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ESB.reqObjHelper.type ();
  }

}
