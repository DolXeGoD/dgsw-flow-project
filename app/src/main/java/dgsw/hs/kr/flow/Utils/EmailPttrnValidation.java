package dgsw.hs.kr.flow.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 조현재 on 2018-04-24.
 */

public class EmailPttrnValidation {

    private final String EMAIL_REGEX = "^[a-zA-Z0-9]+@dgsw\\.hs\\.kr$";

    public boolean ValidationEmail(String email){
        Pattern p = Pattern.compile(EMAIL_REGEX);
        Matcher m = p.matcher(email);

        if(m.find()){
            return true;
        }
        else{
            return false;
        }
    }
}
