package dgsw.hs.kr.flow.Utils;

/**
 * Created by 조현재 on 2018-04-26.
 */

public class RePwValidation {
    public boolean CheckPwd(String pw, String repw){
        if(pw.equals(repw)){
            return true;
        }else {
            return false;
        }
    }
}
