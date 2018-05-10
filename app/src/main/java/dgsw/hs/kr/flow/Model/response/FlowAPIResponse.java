package dgsw.hs.kr.flow.Model.response;

/**
 * Created by 조현재 on 2018-04-18.
 */

public class FlowAPIResponse {
    private int status;
    private String message;

    public int getStatus() { return status; }
    public void setStatus( int status ) { this.status = status; }


    public String getMessage() { return message; }
    public void setMessage( String message ) { this.message = message; }
}
