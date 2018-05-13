package dgsw.hs.kr.flow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dgsw.hs.kr.flow.APIUtills;
import dgsw.hs.kr.flow.Model.request.Login;
import dgsw.hs.kr.flow.Model.response.loginResponse;
import dgsw.hs.kr.flow.R;
import dgsw.hs.kr.flow.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String pw;
    private RetrofitService mRTService;
    private Call<loginResponse> mResonse;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                //아이디나 비밀번호 칸 미입력 여부 검사
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pw)){
                    mRTService = APIUtills.getAPIService();
                    mResonse = mRTService.loginPost(login);
                    mResonse.enqueue(new Callback<loginResponse>() {
                        @Override
                        public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                            if (response.isSuccessful()) {
                                //DEBUG
                                Log.i(TAG, "response msg : " + response.message().toString());
                                Log.i(TAG, "response code : " + response.code());

                                Log.i(TAG, "server msg : " + response.body().getMessage());
                                Log.i(TAG, "server code : " + response.body().getStatus());

                                Log.i(TAG, "USER EMAIL : " + response.body().getEmail());
                                Log.i(TAG, "USER NAME : " + response.body().getName());
                                Log.i(TAG, "USER TOKEN : " + response.body().getToken());

                            }
                        }

                        @Override
                        public void onFailure(Call<loginResponse> call, Throwable t) {
                            Log.e(TAG, "Login Failed");
                        }
                    });
                }
            }
        });
    }

}
