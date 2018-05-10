package dgsw.hs.kr.flow.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 조현재 on 2018-04-24.
 */

public class PwPttrnValidation {

    private static String PASSWORD_REGEX="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=\\S+$).{8,16}$";

    public boolean ValidationPw(String pw){
        Pattern p = Pattern.compile(PASSWORD_REGEX);
        Matcher m = p.matcher(pw);

        if(m.find()){
            return true;
        }
        else{
            return false;
        }
    }
}
