package dgsw.hs.kr.flow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

import org.hyunjun.school.SchoolMenu;

import dgsw.hs.kr.flow.Network.MealParsing;
import dgsw.hs.kr.flow.R;


public class MealActivity extends AppCompatActivity {

    private SchoolMenu menu;

    private int year;
    private int month;
    private int day;
    private int mealTime;
    private String date;

    private TextView morning_txt;
    private TextView lunch_txt;
    private TextView dinner_txt;
    private TextView date_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        /* =========== getExtras로 MainActivity에서 넘어온 날짜 데이터 세팅 =========== */
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            date = (String)extras.get("STRING_DATE");
            Log.i("test getExtras", "date value : "+date);
        } else{
            Log.i("test getExtras", "CAN'T GET DATE");
        }

        setMeal();
        setLayout();
    }

    protected void setMeal(){
        final MealParsing mealParsing = new MealParsing();
        year = MainActivity.mt.getYear();
        month = MainActivity.mt.getMonth();
        day = MainActivity.mt.getDay();
        mealTime = MainActivity.mt.getMealTime();

        try {
            menu = mealParsing.execute(year, month, day).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setLayout(){
        morning_txt = findViewById(R.id.morning_txt);
        lunch_txt = findViewById(R.id.lunch_txt);
        dinner_txt = findViewById(R.id.dinner_txt);
        date_txt = findViewById(R.id.tv_date_view);

        date_txt.setText(date);

        /* ==================== TabHost 관련 ==================== */
        TabHost tabHost1 = findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("아침") ;
        tabHost1.addTab(ts1);
        morning_txt.setText(menu.breakfast);

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("점심") ;
        tabHost1.addTab(ts2);
        lunch_txt.setText(menu.lunch);

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("저녁") ;
        tabHost1.addTab(ts3) ;
        dinner_txt.setText(menu.dinner);

        if(mealTime == 0){
            tabHost1.setCurrentTab(0);
        }
        else if (mealTime == 1){
            tabHost1.setCurrentTab(1);
        }
        else if (mealTime == 2){
            tabHost1.setCurrentTab(2);
        }
    }
}
