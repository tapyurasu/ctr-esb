package main.model;

public class CorbaOutput {
    public String UserName;
    public String UserId;
    public String PhoneNumber;
    public String AccountInfo;  // JSON配列を文字列化して格納

    public int result;  // ステータスコード: 0=OK, 1=ERROR
}
