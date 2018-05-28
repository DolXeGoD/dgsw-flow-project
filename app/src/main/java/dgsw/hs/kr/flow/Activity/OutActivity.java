package dgsw.hs.kr.flow.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import dgsw.hs.kr.flow.R; //R 오류가 왜 뜨는지 모르겠네 ㅠㅠ 돌려막기

public class OutActivity extends AppCompatActivity {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog dialog = new DatePickerDialog(this, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
            /*Toast.makeText(getApplicationContext(), thisYear + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();*/
            // DEFAULT : 아침
            year = thisYear;
            month = monthOfYear + 1;
            day = dayOfMonth - 1;
            hour = 7;
            minute = 10;

            Toast.makeText(getApplicationContext(), year +
                    "년" + month + "월" + day+"일" + hour + "시" + minute + "분", Toast.LENGTH_SHORT).show();
            /*mt.setDate(year, month, day, hour, minute);
            Intent mealIntent = new Intent(MainActivity.this, MealActivity.class);
            startActivity(mealIntent);*/
        }
    };
}
