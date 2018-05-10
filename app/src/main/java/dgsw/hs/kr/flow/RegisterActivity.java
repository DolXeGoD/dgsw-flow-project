package dgsw.hs.kr.flow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import dgsw.hs.kr.flow.Model.request.Register;
import dgsw.hs.kr.flow.Model.response.FlowAPIResponse;
import dgsw.hs.kr.flow.Utils.EmailPttrnValidation;
import dgsw.hs.kr.flow.Utils.PwPttrnValidation;
import dgsw.hs.kr.flow.Utils.RePwValidation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner_classIdx;
    private Spinner spinner_classNum;

    private String email;
    private String pw;
    private String repw;
    private String name;
    private String gender;
    private String mobile;
    private int class_idx;
    private int class_number;

    private static final String TAG = "RegisterActivity";
    private RetrofitService mRTService;
    private Call<FlowAPIResponse> mResponse;

    //Get Class For Utils
    private RePwValidation repwv = new RePwValidation();
    private PwPttrnValidation pwv = new PwPttrnValidation();
    private EmailPttrnValidation emailv = new EmailPttrnValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText emailEt = findViewById(R.id.et_email);
        final EditText pwEt = findViewById(R.id.et_pw);
        final EditText rePwEt = findViewById(R.id.et_rePw);
        final EditText nameEt = findViewById(R.id.et_name);

        final CheckBox isMen = findViewById(R.id.gender_men);
        final CheckBox isWomen = findViewById(R.id.gender_women);

        final EditText mobileEt = findViewById(R.id.et_mobile);

        spinner_classIdx = findViewById(R.id.spinner_classIdx);
        spinner_classNum = findViewById(R.id.spinner_classNum);

        Button registerBtn = findViewById(R.id.btn_register);

        //SUBMIT 버튼 클릭 시.
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                email = emailEt.getText().toString().trim();
                pw = pwEt.getText().toString().trim();
                repw = rePwEt.getText().toString().trim();
                name = nameEt.getText().toString().trim();
                //성별 선택 Checkbox Event
                if(isMen.isChecked() == true){ gender = "남성"; }
                else if(isWomen.isChecked() == true){ gender = "여성";}
                mobile = mobileEt.getText().toString().trim();
                class_idx = Integer.parseInt(spinner_classIdx.getSelectedItem().toString());
                class_number = Integer.parseInt(spinner_classNum.getSelectedItem().toString());

                //이메일 패턴 확인
                if(emailv.ValidationEmail(email)){

                    //비밀번호 패턴 확인
                    if(pwv.ValidationPw(pw)){

                        //재입력 비밀번호 일치 여부 확인
                        if(repwv.CheckPwd(pw, repw)){
                            //모든 Validation 통과
                            Register register = new Register();
                            register.setEmail(email);
                            register.setPw(pw);
                            register.setName(name);
                            register.setGender(gender);
                            register.setMobile(mobile);
                            register.setClassidx(class_idx);
                            register.setClassnumber(class_number);

                            //(Retrofit) 서버로 전송
                            mRTService = APIUtills.getAPIService();
                            mResponse = mRTService.registerPost(register);
                            mResponse.enqueue(new Callback<FlowAPIResponse>() {
                                @Override
                                public void onResponse(Call<FlowAPIResponse> call, Response<FlowAPIResponse> response) {
                                    if(response.isSuccessful()){
                                        // DEBUG
                                        Log.i(TAG,"response msg : " + response.message().toString());
                                        Log.i(TAG,"response code : " + response.code());

                                        Log.i(TAG, "server msg : " + response.body().getMessage());
                                        Log.i(TAG, "server code : " + response.body().getStatus());
                                    }
                                }

                                @Override
                                public void onFailure(Call<FlowAPIResponse> call, Throwable t) {
                                    Log.e(TAG, "서버가 현재 점검중입니다.");
                                }
                            });

                        } else {
                            //재입력한 비밀번호 불일치 시
                            Toast.makeText(getApplicationContext(),"PW and Re Enter PW is Different. :(", Toast.LENGTH_LONG).show();
                        }
                    } else{
                        //비밀번호 패턴 검사 실패 시
                        Toast.makeText(getApplicationContext(),"PW Pattern Check Failed :(", Toast.LENGTH_LONG).show();
                    }
                } else{
                    //이메일 패턴 검사 실패 시
                    Toast.makeText(getApplicationContext(),"EMAIL Pattern Check Failed :(", Toast.LENGTH_LONG).show();
                }

            }
        });
    }



}
