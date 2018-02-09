package cn.braing.pay.lib.view.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.util.LogUtils;
import cn.braing.pay.lib.util.Utils;


public class Html5WebView extends WebView {

    private ProgressBar progressbar;
    private OnHtml5Listener onHtml5Listener;

    public Html5WebView(Context context) {
        super(context);
        init();
    }

    public Html5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Html5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        progressbar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));

        Drawable drawable = getContext().getResources().getDrawable(R.drawable.webview_progress_bar);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        WebSettings mWebSettings = getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);
        String ua = mWebSettings.getUserAgentString();
        mWebSettings.setUserAgentString(ua + "; yishengyue");
        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface

        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(true);
        addJavascriptInterface(new AndroidtoJsBean(getContext()), "JsToNative");
        //缓存数据
        saveData(mWebSettings);
        newWin(mWebSettings);
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
    }

    /**
     * 多窗口的问题
     */
    private void newWin(WebSettings mWebSettings) {
        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        //然后 复写 WebChromeClient的onCreateWindow方法
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    /**
     * HTML5数据存储
     */
    private void saveData(WebSettings mWebSettings) {
        //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置

        if (NetStatusUtil.isConnected(getContext())) {
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }

        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        String appCachePath = getContext().getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
    }

    public void setOnHtml5Listener(OnHtml5Listener onHtml5Listener) {
        this.onHtml5Listener = onHtml5Listener;
    }

    WebViewClient webViewClient = new WebViewClient() {
        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("platformapi/startapp")) {
                startAlipayActivity(url);
                // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
                return true;
            } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                    && (url.contains("platformapi") && url.contains("startapp"))) {
                startAlipayActivity(url);
                return true;
            }else if(url.startsWith("weixin://wap/pay?")) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Utils.getContext().startActivity(intent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        private void startAlipayActivity(String url) {
            Intent intent;
            try {
                intent = Intent.parseUri(url,
                        Intent.URI_INTENT_SCHEME);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setComponent(null);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Utils.getContext().startActivity(intent);
             } catch (Exception e) {
                e.printStackTrace();
            }
        }



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (onHtml5Progress != null)
                onHtml5Progress.onProgressStart();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (onHtml5Progress != null)
                onHtml5Progress.onProgressFinish();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            handler.proceed();
            if (onHtml5Progress != null)
                onHtml5Progress.onProgressError();
        }
    };


    WebChromeClient webChromeClient = new WebChromeClient() {

        //=========HTML5定位==========================================================
        //需要先加入权限
        //<uses-permission android:name="android.permission.INTERNET"/>
        //<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
        //<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (onHtml5Listener != null) {
                onHtml5Listener.onReceivedTitle(title);
            }
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        //=========HTML5定位==========================================================


        //=========多窗口的问题==========================================================
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebViewTransport transport = (WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }
        //=========多窗口的问题==========================================================
    };

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnHtml5Listener {
        void onReceivedTitle(String title);
    }

    public void setOnHtml5CallPhoneListener(OnHtml5CallPhoneListener onHtml5CallPhoneListener) {
        mOnHtml5CallPhoneListener = onHtml5CallPhoneListener;
    }

    private OnHtml5CallPhoneListener mOnHtml5CallPhoneListener;
    private OnHtml5ImToP2PListener onHtml5ImToP2PListener;

    public interface OnHtml5ImToP2PListener {
        void imToP2P(String accountId);
    }

    public interface OnHtml5CallPhoneListener {
        void onCallPhone(String Phone);
    }

    public void setOnHtml5ImToP2PListener(OnHtml5ImToP2PListener onHtml5ImToP2PListener) {
        this.onHtml5ImToP2PListener = onHtml5ImToP2PListener;
    }

    private OnHtml5Progress onHtml5Progress;

    public void setOnHtml5Progress(OnHtml5Progress onHtml5Progress) {
        this.onHtml5Progress = onHtml5Progress;
    }

    public interface OnHtml5Progress {
        void onProgressStart();

        void onProgressFinish();

        void onProgressError();
    }

    public void setOntoNativeListener(OntoNativeListener ontoNativeListener) {
        mOntoNativeListener = ontoNativeListener;
    }

    private OntoNativeListener mOntoNativeListener;

    public interface OntoNativeListener {
        void OntoNative();

    }
}
