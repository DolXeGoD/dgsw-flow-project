package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dgsw.hs.kr.flow.Database.OutDBManager;
import dgsw.hs.kr.flow.Database.TokenDBManager;
import dgsw.hs.kr.flow.R;

public class DBManagerTestActivity extends AppCompatActivity {
    private String shit;
    private int accept = 0;
    private String start_time = "2018-06-25 17:00";
    private String end_time = "2018-06-25 20:00";
    private String reason = "정말 놀고싶다";
    private int class_idx = 2;
    private String student_email = "dolxegod@gmail.com";
    private static final String TAG = "DBTestActivity";
    private String[][] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanager_test);
        final Button btn_select = findViewById(R.id.btn_dbselect);
        final Button btn_insert = findViewById(R.id.btn_insertTest);
        final Button btn_delete = findViewById(R.id.btn_delete);
        final TextView resultTv = findViewById(R.id.tv_resultOfSelect);
        final OutDBManager outDbManager = new OutDBManager(getApplicationContext(), "FlowUser.db", null, 1);
        resultTv.setText("thistextisnotincludeanymeans");

        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                array = outDbManager.select();
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDbManager.insert(accept, start_time, end_time, reason, class_idx, student_email);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                outDbManager.delete(1);
            }
        });
    }
}
