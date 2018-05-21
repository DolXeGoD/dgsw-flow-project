package dgsw.hs.kr.flow.Network;

import android.os.AsyncTask;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;

/**
 * Created by 조현재 on 2018-03-19.
 */

public class MealParsing extends AsyncTask<Integer, Void, SchoolMenu> {

    @Override
    protected SchoolMenu doInBackground(Integer... integers) {
        School api = new School(School.Type.HIGH, School.Region.DAEGU, "D100000282");

        SchoolMenu menu = null;
        String result = "";

        int year= integers[0];
        int month = integers[1];
        int day = integers[2];

        try {
            menu = api.getMonthlyMenu(year, month).get(day);

           /* switch(integers[3]) {
                case 0:
                    result = menu.breakfast;
                    break;

                case 1:
                    result = menu.lunch;
                    break;

                case 2:
                    result = menu.dinner;
                    break;
            }*/
        } catch (SchoolException e) {
            e.printStackTrace();
        }
        return menu;
    }
}
