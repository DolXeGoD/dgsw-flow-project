package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.Model.request.Login;
import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import dgsw.hs.kr.flow.Network.APIUtills;
import dgsw.hs.kr.flow.Network.RetrofitService;
import dgsw.hs.kr.flow.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String pw;
    private String registration_token = "student036";
    private String userToken;
    private RetrofitService mRTService;
    private Call<ResponseFormat> mResponse;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
        final EditText emailEt = findViewById(R.id.login_et_email);
        final EditText pwEt = findViewById(R.id.login_et_pw);
        Button loginBtn = findViewById(R.id.btn_login);

        //LOGIN 버튼 클릭 시
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEt.getText().toString().trim();
                pw = pwEt.getText().toString().trim();

                Login login = new Login();
                login.setEmail(email);
                login.setPw(pw);
                login.setRegistration_token(registration_token);

                //아이디나 비밀번호 칸 미입력 여부 검사
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pw)){
                    mRTService = APIUtills.getAPIService();
                    mResponse = mRTService.loginPost(login);
                    mResponse.enqueue(new Callback<ResponseFormat>() {
                        @Override
                        public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                            if (response.isSuccessful()) {
                                //DEBUG
                                Log.i(TAG, "response msg : " + response.message().toString());
                                Log.i(TAG, "response code : " + response.code());

                                Log.i(TAG, "server msg : " + response.body().getMessage());
                                Log.i(TAG, "server code : " + response.body().getStatus());

                                Log.i(TAG, "USER TOKEN : " + response.body().getData().getToken());

                                userToken = response.body().getData().getToken();

                                //DB에 유저 토큰 저장.
                                dbManager.insert(userToken);

                                //db 조회해서 토큰 값 있는지 확인
                                dbManager.selectTest();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFormat> call, Throwable t) {
                            Log.e(TAG, "Login Failed");
                        }
                    });
                }
            }
        });
    }

}
