package dgsw.hs.kr.flow.Activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.R;

public class DBManagerTestActivity extends AppCompatActivity {
    private String shit;
    private int accept = 0;
    private String start_time = "2018-06-25 17:00";
    private String end_time = "2018-06-25 20:00";
    private String reason = "정말 놀고싶다";
    private int class_idx = 2;
    private String student_email = "dolxegod@gmail.com";
    private int server_idx = 555;
    private static final String TAG = "DBTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanager_test);
        final Button btn_select = findViewById(R.id.btn_dbselect);
        final Button btn_insert = findViewById(R.id.btn_insertTest);
        final Button btn_delete = findViewById(R.id.btn_delete);
        final TextView resultTv = findViewById(R.id.tv_resultOfSelect);
        final Button btn_testtokeninsert = findViewById(R.id.btn_test_insert_token);
        final DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
        resultTv.setText("thistextisnotincludeanymeans");

        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor cursor;
                int cnt = dbManager.out_count();

                Log.i("COUNT VALUE", " IS : "+cnt);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.out_insert(accept, start_time, end_time, reason, class_idx, student_email, server_idx);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i = 0;i<20;i++){
                    dbManager.out_delete(i);
                }
                int cnt = dbManager.out_count();
                Log.i("Tag", "COUNT AFTER DELETE : " + cnt);
            }
        });

        btn_testtokeninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*dbManager.token_insert("testtoken1");
                dbManager.token_select();*/

                dbManager.droptable();
            }
        });
    }
}
