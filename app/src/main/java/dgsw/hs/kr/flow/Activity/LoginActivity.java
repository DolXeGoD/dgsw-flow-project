package dgsw.hs.kr.flow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.Model.request.Login;
import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import dgsw.hs.kr.flow.Utils.APIUtills;
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
        final EditText emailEt = findViewById(R.id.et_login_email);
        final EditText pwEt = findViewById(R.id.et_login_pw);
        final Button loginBtn = findViewById(R.id.btn_login);
        final TextView gotoRegisterTv = findViewById(R.id.tv_goto_register);

        //회원가입하기 버튼 눌렀을 때 -> 회원가입 액티비티로 이동
        gotoRegisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //LOGIN 버튼 클릭 시
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEt.getText().toString().trim();
                pw = pwEt.getText().toString().trim();

                Login login = new Login();

                //아이디&비밀번호 칸 미입력 여부 검사
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pw)){
                    login.setEmail(email);
                    login.setPw(pw);
                    login.setRegistration_token(registration_token);

                    mRTService = APIUtills.getAPIService();
                    mResponse = mRTService.loginPost(login);
                    mResponse.enqueue(new Callback<ResponseFormat>() {
                        @Override
                        public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                            //서버 연결 성공 시
                            if (response.isSuccessful()) {
                                //DEBUG
                                Log.i(TAG, "response msg : " + response.message().toString());
                                Log.i(TAG, "response code : " + response.code());
                                Log.i(TAG, "server msg : " + response.body().getMessage());
                                Log.i(TAG, "server code : " + response.body().getStatus());

                                // 로그인 성공 시
                                if(response.body().getStatus() == 200){
                                    Toast.makeText(getApplicationContext(),"로그인 성공 !!!",Toast.LENGTH_LONG).show();
                                    userToken = response.body().getResponseFormatData().getToken();
                                    Log.i(TAG, "USER TOKEN : " + response.body().getResponseFormatData().getToken());
                                    //DB에 유저 토큰 저장.
                                    dbManager.insert(userToken);
                                    Toast.makeText(getApplicationContext(),"토큰을 저장하였습니다.",Toast.LENGTH_LONG).show();
                                    //db 조회해서 토큰 값 있는지 확인
                                    dbManager.select();

                                    //메인으로 이동.
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else{ //로그인 실패 시
                                    Toast.makeText(getApplicationContext(),"입력 양식이나 아이디, 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                                }
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
