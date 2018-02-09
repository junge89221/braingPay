package cn.braing.pay.lib.view.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

//import com.netease.nim.uikit.api.NimUIKit;

/**
 * <pre>
 * author：张俊
 * date： 2017/8/9
 * desc：
 * <pre>
 */

public class AndroidtoJsBean {

    private Context mContext;

    public AndroidtoJsBean(Context context) {
        mContext = context;
    }









    @JavascriptInterface
    public void back() {
        final Html5Activity activity = (Html5Activity) mContext;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (activity.getmWebView().canGoBack()) {
                    activity.getmWebView().goBack();
                } else {
                    activity.finish();
                }
            }
        });
    }




   /* @JavascriptInterface
    public String getTel() {
        JSONObject result = new JSONObject();
        try {
            result.put("tel", Data.getUser() != null ? Data.getUser().getUserInfo().getMobile() : "");
            result.put("token", Data.getUser() != null ? Data.getUser().getAccessToken() : "");
            result.put("userId", Data.getUserId());
            return result.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }*/




}
