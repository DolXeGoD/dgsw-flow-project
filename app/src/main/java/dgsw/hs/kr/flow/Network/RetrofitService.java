package dgsw.hs.kr.flow.Network;

import dgsw.hs.kr.flow.Model.request.Login;
import dgsw.hs.kr.flow.Model.request.Out;
import dgsw.hs.kr.flow.Model.request.Register;
import dgsw.hs.kr.flow.Model.response.ResponseFormat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by 조현재 on 2018-04-10.
 */

public interface RetrofitService {
    @POST("auth/signup")
    Call<ResponseFormat> registerPost(@Body Register register);

    @POST("auth/signin")
    Call<ResponseFormat> loginPost(@Body Login login);

    @POST("out/go")
    Call<ResponseFormat> goOutPost(@Body Out out, @Header("x-access-token") String token);

    @POST("out/sleep")
    Call<ResponseFormat> sleepOutPost(@Body Out out, @Header("x-access-token") String token);

    @GET("notice")
    Call<ResponseFormat> noticeGet(@Header("x-access-token") String token);

    @GET("notice/{idx}")
    Call<ResponseFormat> noticeDetailGet(@Body int idx,@Header("x-access-token") String token);
}
