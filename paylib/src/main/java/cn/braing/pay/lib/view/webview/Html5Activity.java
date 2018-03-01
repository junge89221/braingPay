package cn.braing.pay.lib.view.webview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.page.BraBaseActivity;


public class Html5Activity extends BraBaseActivity implements Html5WebView.OnHtml5Listener, Html5WebView.OnHtml5Progress, View.OnClickListener {
    public static final String URL = "url";
    public static final String TITLE = "title";
    private Html5WebView mWebView;
    private String mUrl;
    private String mTitle = "";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.braactivity_webview);
        mWebView = (Html5WebView) findViewById(R.id.html5_web_view);
        mWebView.setOnHtml5Listener(this);
         mWebView.setOnHtml5Progress(this);
        mTbLeftView.setOnClickListener(this);
        if (getIntent() != null) {
            mUrl = getIntent().getStringExtra(URL);
            mTitle = getIntent().getStringExtra(TITLE);
            mWebView.loadUrl(mUrl);
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {

            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
         super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK)&&mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onReceivedTitle(String title) {
        if (mTitle == null) {
            setTitleName(title==null?"":title);
        }else {
            setTitleName(mTitle);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.toolbar_left) {
            if (mWebView.canGoBack()) {
                setTitleName(mWebView.getTitle());
                mWebView.goBack();
            } else {
                finish();
            }
        }
    }


    @Override
    public void onProgressStart() {

    }

    @Override
    public void onProgressFinish() {

    }

    @Override
    public void onProgressError() {

    }
    public Html5WebView getmWebView() {
        return mWebView;
    }
}