package dgsw.hs.kr.flow.Model.request;

/**
 * Created by 조현재 on 2018-04-24.
 */

public class Login {
    private String email;
    private String pw;
    private String registration_token;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {return pw;}
    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRegistration_token() {return registration_token;}
    public void setRegistration_token(String registration_token) {this.registration_token = registration_token;}
}
