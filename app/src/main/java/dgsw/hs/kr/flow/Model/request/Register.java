package dgsw.hs.kr.flow.Model.request;

/**
 * Created by 조현재 on 2018-04-17.
 */

public class Register {
    private String email;
    private String pw;
    private String name;
    private String gender;
    private String mobile;
    private int class_idx;
    private int class_number;

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getPw() { return pw; }
    public void setPw(String value) { this.pw = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public String getMobile() { return mobile; }
    public void setMobile(String value) { this.mobile = value; }

    public long getClassidx() { return class_idx; }
    public void setClassidx(int value) { this.class_idx = value; }

    public long getClassnumber() { return class_number; }
    public void setClassnumber(int value) { this.class_number = value; }
}
