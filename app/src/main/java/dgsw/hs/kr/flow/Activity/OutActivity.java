package dgsw.hs.kr.flow.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
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

        Button startdate_btn = findViewById(R.id.btn_setStartDate);
        Button enddate_btn = findViewById(R.id.btn_setEndDate);
        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog Ddialog = new DatePickerDialog(this, dateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        final TimePickerDialog Tdialog = new TimePickerDialog(this, timeListener, hour, minute, true);

        startdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ddialog.show();
            }
        });

        enddate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Ddialog.show();
            }
        });
    }



    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
            /*Toast.makeText(getApplicationContext(), thisYear + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();*/
            year = thisYear;
            month = monthOfYear + 1;
            day = dayOfMonth - 1;
            Toast.makeText(getApplicationContext(), year +
                    "년" + month + "월" + day+"일", Toast.LENGTH_SHORT).show();
            /*mt.setDate(year, month, day, hour, minute);
            Intent mealIntent = new Intent(MainActivity.this, MealActivity.class);
            startActivity(mealIntent);*/
        }
    };

    private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            Toast.makeText(getApplicationContext(), hour + " 시" + minute +
                    " 분 입니다.", Toast.LENGTH_SHORT).show();
        }
    };
}
