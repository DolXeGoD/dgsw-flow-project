package dgsw.hs.kr.flow.Model.response;

/**
 * Created by 조현재 on 2018-05-14.
 */

public class ResponseFormat {
    private int status;
    private String message;
    private Data data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() { return data; }

    public void setData(Data data) { this.data = data; }
}
