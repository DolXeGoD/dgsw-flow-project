package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.R;

public class DBManagerTestActivity extends AppCompatActivity {
    private String shit;
    private String test = "hello, token!";
    private static final String TAG = "DBTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanager_test);
        final Button btn_select = findViewById(R.id.btn_dbselect);
        final Button btn_insert = findViewById(R.id.btn_insertTest);
        final Button btn_delete = findViewById(R.id.btn_delete);
        final TextView resultTv = findViewById(R.id.tv_resultOfSelect);
        final DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
        resultTv.setText("thistextisnotincludeanymeans");

        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shit = dbManager.select();
                resultTv.setText(shit);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insert(test);
                shit = dbManager.select();
                resultTv.setText(shit);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbManager.delete();
            }
        });
    }
}
