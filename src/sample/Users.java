package sample;

public class Users {
    private String name;
    private String id;
    private String passwd;
    private String email;
    private String mobileNo;

    public Users(String name, String id, String passwd, String email,
                String mobileNo) {
        this.name = name;
        this.id = id;
        this.passwd = passwd;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public String getName() { return this.name; }
    public String getId() { return this.id; }
    public String getPasswd() { return this.passwd; }
    public String getEmail() { return this.email; }
    public String getMobileNo() { return this.mobileNo; }
}
