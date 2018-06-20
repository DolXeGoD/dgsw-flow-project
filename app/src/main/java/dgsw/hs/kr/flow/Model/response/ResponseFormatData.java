package dgsw.hs.kr.flow.Model.response;

import java.util.List;

/**
 * Created by 조현재 on 2018-05-14.
 */
//responseformat - data
public class ResponseFormatData {
    private String token;
    private GoOutResponseFormat go_out;
    private SleepOutResponseFormat sleep_out;
    private NoticeResponseFormat[] list;

    public NoticeResponseFormat[] getList() {return list;}
    public void setList(NoticeResponseFormat[] list) {this.list = list;}

    public GoOutResponseFormat getGo_out() {return go_out;}
    public void setGo_out(GoOutResponseFormat go_out) {
        this.go_out = go_out;
    }

    public SleepOutResponseFormat getSleep_out() {
        return sleep_out;
    }
    public void setSleep_out(SleepOutResponseFormat sleep_out) {
        this.sleep_out = sleep_out;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
