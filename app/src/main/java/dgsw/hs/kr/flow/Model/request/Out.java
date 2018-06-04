package dgsw.hs.kr.flow.Model.request;

import java.util.Date;

/**
 * Created by 조현재 on 2018-05-17.
 */

public class Out {
    private String start_time;
    private String end_time;
    private String reason;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
