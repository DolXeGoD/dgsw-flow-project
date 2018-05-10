package dgsw.hs.kr.flow.Model.request;

/**
 * Created by 조현재 on 2018-04-24.
 */

public class Login {
    private String email;
    private String pw;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
}
