package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.R;

public class DBManagerTestActivity extends AppCompatActivity {

    final Button btn_select = findViewById(R.id.btn_dbselect);
    final TextView resultTv = findViewById(R.id.tv_resultOfSelect);
    final DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
    private String shit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanager_test);
        resultTv.setText("thistextisnotincludeanymeans");

        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shit = dbManager.select();
                resultTv.setText(shit);
            }
        });
    }
}
