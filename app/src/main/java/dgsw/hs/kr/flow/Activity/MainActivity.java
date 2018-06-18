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

public class MainActivity extends AppCompatActivity {

    private Button nextMealBtn;
    private Button choiceMealBtn;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public static MealTime mt = new MealTime();


    protected void onCreate(Bundle savedInstanOverrideceState) {

        super.onCreate(savedInstanOverrideceState);
        setContentView(R.layout.activity_main);

        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog dialog = new DatePickerDialog(this, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        nextMealBtn = findViewById(R.id.btn_next_meal);
        choiceMealBtn = findViewById(R.id.btn_choice_meal);

        //다음 급식 버튼 리스너
        nextMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH) + 1;
                day = cal.get(Calendar.DATE) - 1;

                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute =  cal.get(Calendar.MINUTE);

                mt.setDate(year, month, day, hour, minute);

                Intent mealIntent = new Intent(MainActivity.this, MealActivity.class);
                startActivity(mealIntent);
            }
        });

        //급식 선택하기 버튼 리스너
        choiceMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
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

            mt.setDate(year, month, day, hour, minute);
            Intent mealIntent = new Intent(MainActivity.this, MealActivity.class);
            startActivity(mealIntent);
        }
    };





}
