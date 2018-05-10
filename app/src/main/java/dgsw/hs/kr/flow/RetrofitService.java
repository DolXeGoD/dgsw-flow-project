package dgsw.hs.kr.flow;

import dgsw.hs.kr.flow.Model.request.Login;
import dgsw.hs.kr.flow.Model.request.Register;
import dgsw.hs.kr.flow.Model.response.FlowAPIResponse;
import dgsw.hs.kr.flow.Model.response.loginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 조현재 on 2018-04-10.
 */

public interface RetrofitService {
    @POST("auth/signup")
    Call<FlowAPIResponse> registerPost(@Body Register register);

    @POST("auth/signin")
    Call<loginResponse> loginPost(@Body Login login);


}
