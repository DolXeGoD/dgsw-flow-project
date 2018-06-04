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
    private String S_DATE;
    private String S_TIME;
    private String S_DATETIME;
    private String E_DATE;
    private String E_TIME;
    private String E_DATETIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        Button startdate_btn = findViewById(R.id.btn_setStartDate);
        Button enddate_btn = findViewById(R.id.btn_setEndDate);
        Button starttime_btn = findViewById(R.id.btn_setStartTime);
        Button endtime_btn = findViewById(R.id.btn_setEndTime);

        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog S_Ddialog = new DatePickerDialog(this, sdateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        final TimePickerDialog S_Tdialog = new TimePickerDialog(this, stimeListener, hour, minute, true);
        final DatePickerDialog E_Ddialog = new DatePickerDialog(this, edateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        final TimePickerDialog E_Tdialog = new TimePickerDialog(this, etimeListener, hour, minute, true);

        startdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_Ddialog.show();
            }
        });

        starttime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(S_DATE == null){
                    Toast.makeText(getApplicationContext(), "시간을 설정하기 전에 날짜부터 설정해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    S_Tdialog.show();
                }
            }
        });


        enddate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(S_DATETIME == null){
                    Toast.makeText(getApplicationContext(), "시작 날짜 및 시간부터 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else{
                    E_Ddialog.show();
                }
            }
        });

        endtime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(E_DATE == null){
                    Toast.makeText(getApplicationContext(), "시간을 설정하기 전에 날짜부터 설정해주세요.", Toast.LENGTH_SHORT).show();
                } else{
                    E_Tdialog.show();
                }
            }
        });
    }



    private DatePickerDialog.OnDateSetListener sdateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
            year = thisYear;
            month = monthOfYear + 1;
            day = dayOfMonth;
            Toast.makeText(getApplicationContext(), year +
                    "년" + month + "월" + day+"일", Toast.LENGTH_SHORT).show();
            S_DATE = year+"-"+month+"-"+day;
        }
    };

    private TimePickerDialog.OnTimeSetListener stimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            Toast.makeText(getApplicationContext(), hour + " 시" + minute +
                    " 분 입니다.", Toast.LENGTH_SHORT).show();
            S_TIME = hour+":"+minute;
            S_DATETIME = S_DATE+" "+S_TIME;
            System.out.println(S_DATETIME);
        }
    };

    private DatePickerDialog.OnDateSetListener edateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
            year = thisYear;
            month = monthOfYear + 1;
            day = dayOfMonth;
            Toast.makeText(getApplicationContext(), year +
                    "년" + month + "월" + day+"일", Toast.LENGTH_SHORT).show();
            E_DATE = year+"-"+month+"-"+day;
        }
    };

    private TimePickerDialog.OnTimeSetListener etimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            Toast.makeText(getApplicationContext(), hour + " 시" + minute +
                    " 분 입니다.", Toast.LENGTH_SHORT).show();
            E_TIME = hour+":"+minute;
            E_DATETIME = E_DATE+" "+E_TIME;
        }
    };
}
