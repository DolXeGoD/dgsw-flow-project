package dgsw.hs.kr.flow.Activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import dgsw.hs.kr.flow.Adapter.OutListAdapter;
import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.Model.OutListItem;
import dgsw.hs.kr.flow.R;

public class OutListActivity extends AppCompatActivity {
    private ListView lv_out;
    private static final String TAG = "OutListActivity";
    private int countOfRow;
    private int accept;
    private String start_date;
    private String end_date;
    private String reason;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_list);

        final DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
        final ArrayList<OutListItem> data = new ArrayList<>();
        final OutListAdapter adapter = new OutListAdapter(this, R.layout.out_item,data);

        lv_out = findViewById(R.id.lv_out_list);
        countOfRow = dbManager.out_count();
        for(int i = 1;i<countOfRow+1;i++){
            Cursor cursor;
            cursor = dbManager.out_select(i);
            if(cursor!=null){
                cursor.moveToNext();
                accept = cursor.getInt(1);
                start_date = cursor.getString(2);
                end_date = cursor.getString(3);
                reason = cursor.getString(4);
                email = cursor.getString(6);

                OutListItem outListItem= new OutListItem(accept, start_date, end_date, reason, email);
                data.add(outListItem);
            }else{
                Log.i("DAMN", "ERROR OCCURED");
            }
        }
        lv_out.setAdapter(adapter);
    }
}
