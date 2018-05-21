package dgsw.hs.kr.flow.Utils;

import dgsw.hs.kr.flow.Network.RetrofitService;

/**
 * Created by 조현재 on 2018-04-16.
 */

public class APIUtills {
    private APIUtills() {}

    public static final String BASE_URL = "http://flow.cafe24app.com/";

    public static RetrofitService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitService.class);
    }


}
