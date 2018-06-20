package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import dgsw.hs.kr.flow.Network.RetrofitService;
import dgsw.hs.kr.flow.R;
import dgsw.hs.kr.flow.Utils.APIUtills;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeListActivity extends AppCompatActivity {
    private TextView tv_result;
    private Button btn_getnotice;
    private RetrofitService mRTService;
    private Call<ResponseFormat> mResponse;
    private static final String TAG = "NoticeListActivity";

    private String USER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW1haWwiOiIwMDB3aGd1c3dvQGRnc3cuaHMua3IiLCJjbGFzc0lkeCI6MiwiYXV0aCI6MiwiaWF0IjoxNTI5MzcwNTYxLCJleHAiOjE1Mjk5NzUzNjEsImlzcyI6ImplZmZjaG9pLmNvbSIsInN1YiI6InRva2VuIn0.k-R39u26IjDFCQumuYFEmP9_ZMT7m27NJ3QLnhDCRHs";
    private String RESULT_DATA;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        btn_getnotice = findViewById(R.id.btn_get_notice);
        tv_result = findViewById(R.id.tv_result_notice);

        btn_getnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrofit.
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
                                Log.i(TAG, "DATATHISISHOLYDATA -> " + response.body());
                                RESULT_DATA = response.body().getResponseFormatData().getList()[0].getContent();
                                tv_result.setText(RESULT_DATA);
                                Log.i(TAG, RESULT_DATA);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFormat> call, Throwable t) {
                        Log.i(TAG, "onFailure에 걸림");
                    }
                });
            }
        });
    }
}
