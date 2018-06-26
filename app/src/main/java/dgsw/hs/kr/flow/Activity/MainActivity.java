package dgsw.hs.kr.flow.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import dgsw.hs.kr.flow.Model.MealTime;
import dgsw.hs.kr.flow.R;
import dgsw.hs.kr.flow.Utils.DayOfTheWeek;

public class MainActivity extends AppCompatActivity {

    private Button nextMealBtn;
    private Button choiceMealBtn;
    private Button requestOutBtn;
    private Button getNoticeBtn;
    private Button outRequestListBtn;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String date;
    private String dayOfTheWeek;
    private String dateForExtra;

    public static MealTime mt = new MealTime();


    protected void onCreate(Bundle savedInstanOverrideceState) {

        super.onCreate(savedInstanOverrideceState);
        setContentView(R.layout.activity_main);

        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog dialog = new DatePickerDialog(this, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        nextMealBtn = findViewById(R.id.btn_next_meal);
        choiceMealBtn = findViewById(R.id.btn_choice_meal);
        requestOutBtn = findViewById(R.id.btn_request_out);
        getNoticeBtn = findViewById(R.id.btn_get_notice);
        outRequestListBtn = findViewById(R.id.btn_out_list);

        //다음 급식 조회 버튼 리스너 -> MealActivity로 이동
        nextMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayOfTheWeek dotw = new DayOfTheWeek();
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH) + 1;
                day = cal.get(Calendar.DATE) - 1;
                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute =  cal.get(Calendar.MINUTE);
                mt.setDate(year, month, day, hour, minute);
                Intent mealIntent = new Intent(MainActivity.this, MealActivity.class);
                dayOfTheWeek = dotw.GetDay(year, month, day);
                dateForExtra = year+"년 "+month+"월 "+(day+1)+"일 ("+dayOfTheWeek+")";
                mealIntent.putExtra("STRING_DATE", dateForExtra);
                startActivity(mealIntent);
            }
        });

        //원하는 날짜의 급식 조회 버튼 리스너
        choiceMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //외출/외박 신청 버튼 리스너.
        requestOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OutActivity.class);
                startActivity(intent);
            }
        });

        outRequestListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OutListActivity.class);
                startActivity(intent);
            }
        });

        getNoticeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoticeListActivity.class);
                startActivity(intent);
            }
        });


    }

    //원하는 날짜 선택 후 -> MealActivity로 이동
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
            DayOfTheWeek dotw = new DayOfTheWeek();
            // DEFAULT : 아침
            year = thisYear;
            month = monthOfYear + 1;
            day = dayOfMonth - 1;
            hour = 7;
            minute = 10;

            Toast.makeText(getApplicationContext(), year +
                    "년" + month + "월" + day+"일" + hour + "시" + minute + "분", Toast.LENGTH_SHORT).show();

            mt.setDate(year, month, day, hour, minute);
            date = year + "년 " + month + "월 " + day + "일 ";

            Intent mealIntent = new Intent(getApplicationContext(), MealActivity.class);
            dayOfTheWeek = dotw.GetDay(year, month, day);
            dateForExtra = year+"년 "+month+"월 "+(day+1)+"일 ("+dayOfTheWeek+")";
            mealIntent.putExtra("STRING_DATE", dateForExtra);
            startActivity(mealIntent);
        }
    };

}
