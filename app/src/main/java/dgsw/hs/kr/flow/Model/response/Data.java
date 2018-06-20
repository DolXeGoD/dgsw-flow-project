package dgsw.hs.kr.flow.Model.response;

import java.util.List;

/**
 * Created by 조현재 on 2018-05-14.
 */
//responseformat - data
public class Data {
    private String token;
    private GoOut go_out;
    private SleepOut sleep_out;
    private List<NoticeResponseFormat> list;

    public GoOut getGo_out() {return go_out;}
    public void setGo_out(GoOut go_out) {
        this.go_out = go_out;
    }

    public SleepOut getSleep_out() {
        return sleep_out;
    }
    public void setSleep_out(SleepOut sleep_out) {
        this.sleep_out = sleep_out;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
