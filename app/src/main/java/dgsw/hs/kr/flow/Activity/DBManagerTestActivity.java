package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dgsw.hs.kr.flow.Database.TokenDBManager;
import dgsw.hs.kr.flow.R;

public class DBManagerTestActivity extends AppCompatActivity {
    private String shit;
    private String test = "tokentest2";
    private static final String TAG = "DBTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanager_test);
        final Button btn_select = findViewById(R.id.btn_dbselect);
        final Button btn_insert = findViewById(R.id.btn_insertTest);
        final Button btn_delete = findViewById(R.id.btn_delete);
        final TextView resultTv = findViewById(R.id.tv_resultOfSelect);
        final TokenDBManager tokenDbManager = new TokenDBManager(getApplicationContext(), "FlowUser.db", null, 1);
        resultTv.setText("thistextisnotincludeanymeans");

        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shit = tokenDbManager.select();
                resultTv.setText(shit);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenDbManager.insert(test);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tokenDbManager.delete();
            }
        });
    }
}
