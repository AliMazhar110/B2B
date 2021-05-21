package sample;

public class Users {
    private static String name;
    private static String id;
    private static String passwd;
    private static String email;
    private static String mobileNo;

    public Users(String name, String id, String passwd, String email,
                String mobileNo) {
        this.name = name;
        this.id = id;
        this.passwd = passwd;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public static String getName() { return name; }
    public static String getId() { return id; }
    public static String getPasswd() { return passwd; }
    public static String getEmail() { return email; }
    public static String getMobileNo() { return mobileNo; }
}
