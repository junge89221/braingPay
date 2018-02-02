package cn.braing.pay.lib.http.okhttp;

import android.util.Base64;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.nio.charset.Charset;

import cn.braing.pay.lib.bean.ServerLogEvent;
import cn.braing.pay.lib.util.LogUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * OkHttp 日志拦截器
 */
public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "okhttp";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG,"\n");
        Log.e(TAG,"----------Start----------------");
        Log.e(TAG, "| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            LogUtils.e("请求数据"+getReqData(request.body()));
        }


        Log.e(TAG, "| Response:" + new String(Base64.decode(content, Base64.NO_WRAP)));
        EventBus.getDefault().post(new ServerLogEvent(false,new String(Base64.decode(content, Base64.NO_WRAP))));
        Log.e(TAG,"----------End:"+duration+"毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, new String(Base64.decode(content, Base64.NO_WRAP))))
                .build();

    }

    private static final Charset UTF8 = Charset.forName("UTF-8");
    public static String getReqData(RequestBody requestBody) throws IOException {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readString(UTF8);
    }
}

