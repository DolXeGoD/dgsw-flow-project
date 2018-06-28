package dgsw.hs.kr.flow.Model;

/**
 * Created by 조현재 on 2018-06-25.
 */

public class OutListItem {
    private int accept;
    private String start_date;
    private String end_date;
    private String reason;
    private String student_email;

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public OutListItem(int accept, String start_date, String end_date, String reason, String student_email) {
        this.accept = accept;
        this.start_date = start_date;
        this.end_date = end_date;
        this.reason = reason;
        this.student_email = student_email;
    }
}
