package cn.braing.pay.lib.http.okhttp;

import android.util.Log;


import java.io.IOException;

import cn.braing.pay.lib.util.Data;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 为每个请求添加固定的Header
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        /*if (Data.getUser() != null && Data.getUser().getAccessToken() != null) {
            builder.addHeader("accessToken", Data.getUser().getAccessToken());
            Log.e("ysy","accessToken:"+Data.getUser().getAccessToken());
        }*/
        Request requestNew = builder.build();
        return chain.proceed(requestNew);
    }

}
