package pay.braing.cn.paysdk;

import android.app.Application;

import cn.braing.pay.lib.PaySdk;


/**
 * <pre>
 * author：张俊
 * date： 2018/1/30
 * desc：
 * <pre>
 */

public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PaySdk.initSDK(this);
    }
}
