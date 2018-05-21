package dgsw.hs.kr.flow.Utils;

/**
 * Created by 조현재 on 2018-03-27.
 */

public class MealTime {
    final static int BREAKFAST_TIME = 720;
    final static int LUNCH_TIME = 1240;
    final static int DINNER_TIME = 1840;

    final static int BREAKFAST_CODE = 0;
    final static int LUNCH_CODE = 1;
    final static int DINNER_CODE = 2;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int mealTime;

    /* FOR TEST */
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int  getMealTime() {
        return mealTime;
    }

    public void setDate(int param_year, int param_month, int param_day, int param_hour, int param_minute){
        year = param_year;
        month = param_month;
        day = param_day;
        hour = param_hour;
        minute = param_minute;

        //1830
        String curTime = String.format("%02d%02d", hour, minute);

        int rawTime = Integer.parseInt(curTime);

        setMealTime(rawTime);
    }

    public void setMealTime(int rawTime){
        if(rawTime < BREAKFAST_TIME) {
            mealTime = BREAKFAST_CODE;
        }
        if(rawTime > BREAKFAST_TIME){
            mealTime = LUNCH_CODE;
        }
        if(rawTime > LUNCH_TIME){
            mealTime = DINNER_CODE;
        }
        if(rawTime > DINNER_TIME){
            mealTime = BREAKFAST_CODE;
            day++;
        }
    }

}
