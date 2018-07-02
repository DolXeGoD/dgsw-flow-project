package dgsw.hs.kr.flow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dgsw.hs.kr.flow.Model.NoticeListItem;
import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import dgsw.hs.kr.flow.Network.RetrofitService;
import dgsw.hs.kr.flow.R;
import dgsw.hs.kr.flow.Utils.APIUtills;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailActivity extends AppCompatActivity {
    private Button btn_test_notice_select;
    private TextView tv_notice_content;
    private String USER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW1haWwiOiIwMDB3aGd1c3dvQGRnc3cuaHMua3IiLCJjbGFzc0lkeCI6MiwiYXV0aCI6MiwiaWF0IjoxNTMwMjUwNDExLCJleHAiOjE1MzA4NTUyMTEsImlzcyI6ImplZmZjaG9pLmNvbSIsInN1YiI6InRva2VuIn0.WV94J6aX4AVfunMlwHYcqJjslqx6y_C19W8y8dtMaFw";
    private RetrofitService mRTService;
    private Call<ResponseFormat> mResponse;
    private String TAG = "NoticeDetailActivity";
    private int notice_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        btn_test_notice_select = findViewById(R.id.btn_get_detail);
        tv_notice_content = findViewById(R.id.tv_result_noticeDetail);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            notice_index = (Integer) extras.get("notice_index");
            Log.i("ffff", "" + notice_index);
        }else{
            Log.e(TAG, "Failed to get extras");
        }

        mRTService = APIUtills.getAPIService();
        mResponse = mRTService.noticeDetailGet(notice_index, USER_TOKEN);
        mResponse.enqueue(new Callback<ResponseFormat>() {
            @Override
            public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "response msg : " + response.message().toString());
                    Log.i(TAG, "response code : " + response.code());
                    Log.i(TAG, "server msg : " + response.body().getMessage());
                    Log.i(TAG, "server code : " + response.body().getStatus());
                    if(response.body().getStatus() == 200){
                        Log.i(TAG, "CONTENT OF NOTICE : " + response.body().getResponseFormatData().getList()[notice_index].getContent());
                        Log.i(TAG, "WRITER OF CONTENT : " + response.body().getResponseFormatData().getList()[notice_index].getWriter());
                        tv_notice_content.setText(response.body().getResponseFormatData().getList()[notice_index].getContent());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFormat> call, Throwable t) {
                Log.i(TAG, "onFailure에 걸림");
            }
        });
    }

}
