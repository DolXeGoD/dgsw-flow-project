package dgsw.hs.kr.flow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import dgsw.hs.kr.flow.Adapter.NoticeListAdapter;
import dgsw.hs.kr.flow.Model.NoticeListItem;
import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import dgsw.hs.kr.flow.Network.RetrofitService;
import dgsw.hs.kr.flow.R;
import dgsw.hs.kr.flow.Utils.APIUtills;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeListActivity extends AppCompatActivity {
    private ListView lv_notice;
    private RetrofitService mRTService;
    private Call<ResponseFormat> mResponse;
    private static final String TAG = "NoticeListActivity";
    private String USER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW1haWwiOiIwMDB3aGd1c3dvQGRnc3cuaHMua3IiLCJjbGFzc0lkeCI6MiwiYXV0aCI6MiwiaWF0IjoxNTI5NjMwNzYxLCJleHAiOjE1MzAyMzU1NjEsImlzcyI6ImplZmZjaG9pLmNvbSIsInN1YiI6InRva2VuIn0.3_bcgVb0bSyQXA4qQx_7qH1wANS13G69lmCx8s3GL8o";
    private String result_data;
    private int NOTICE_COUNT;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        lv_notice = findViewById(R.id.lv_notice_list);
        final ArrayList<NoticeListItem> data = new ArrayList<>();
        final NoticeListAdapter adapter = new NoticeListAdapter(this, R.layout.notice_item,data);

        mRTService = APIUtills.getAPIService();
        mResponse = mRTService.noticeGet(USER_TOKEN);
        mResponse.enqueue(new Callback<ResponseFormat>() {
            @Override
            public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "response msg : " + response.message().toString());
                    Log.i(TAG, "response code : " + response.code());
                    Log.i(TAG, "server msg : " + response.body().getMessage());
                    Log.i(TAG, "server code : " + response.body().getStatus());
                    if(response.body().getStatus() == 200){
                        NOTICE_COUNT = response.body().getResponseFormatData().getList().length;
                        for(int i=0; i < NOTICE_COUNT;i++) { //최근 공지 10개
                            result_data = response.body().getResponseFormatData().getList()[i].getContent();
                            Log.i(TAG, "CONTENT OF NOTICE : " + response.body().getResponseFormatData().getList()[i].getContent());
                            Log.i(TAG, "NOTICE COUNT VALUE : " + NOTICE_COUNT);
                            NoticeListItem noticeListItem = new NoticeListItem(result_data);
                            data.add(noticeListItem);
                        }
                        lv_notice.setAdapter(adapter);
                        lv_notice.setOnItemClickListener(mItemClickListener);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFormat> call, Throwable t) {
                Log.i(TAG, "onFailure에 걸림");
            }
        });
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i(TAG, "POSITION VALUE : " + position);
            Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
            intent.putExtra("notice_index", position);
            startActivity(intent);
        }
    };
}
