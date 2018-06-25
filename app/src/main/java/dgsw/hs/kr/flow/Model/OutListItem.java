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

    public OutListItem(int accept, String start_date, String end_date, String reason, String student_email) {
        this.accept = accept;
        this.start_date = start_date;
        this.end_date = end_date;
        this.reason = reason;
        this.student_email = student_email;
    }
}
