package dgsw.hs.kr.flow.Utils;

import android.content.Intent;

import java.util.Calendar;

/**
 * Created by 조현재 on 2018-06-18.
 */

public class DayOfTheWeek {
    private String returnDate = "";
    public String GetDay(int year, int month, int day){

        Calendar cal= Calendar.getInstance ();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, day+1);

        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 1:
                returnDate = "일";
                break;
            case 2:
                returnDate = "월";
                break;
            case 3:
                returnDate = "화";
                break;
            case 4:
                returnDate = "수";
                break;
            case 5:
                returnDate = "목";
                break;
            case 6:
                returnDate = "금";
                break;
            case 7:
                returnDate = "토";
                break;
        }
        return returnDate;
    }
}
