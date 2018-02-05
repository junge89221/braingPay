package pay.braing.cn.paysdk;

import android.support.multidex.MultiDexApplication;

/**
 * <pre>
 * author：张俊
 * date： 2018/1/30
 * desc：
 * <pre>
 */

public class myApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
 //        BraingSdk.initSDK(this);
    }
}
