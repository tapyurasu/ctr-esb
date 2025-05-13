package ESB;


/**
* ESB/CTRHelper.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* ESB.idlから
* 2025年5月9日 7時45分48秒 JST
*/

abstract public class CTRHelper
{
  private static String  _id = "IDL:ESB/CTR:1.0";

  public static void insert (org.omg.CORBA.Any a, ESB.CTR that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ESB.CTR extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ESB.CTRHelper.id (), "CTR");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ESB.CTR read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CTRStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ESB.CTR value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ESB.CTR narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ESB.CTR)
      return (ESB.CTR)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ESB._CTRStub stub = new ESB._CTRStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ESB.CTR unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ESB.CTR)
      return (ESB.CTR)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ESB._CTRStub stub = new ESB._CTRStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
