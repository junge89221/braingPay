package cn.braing.pay.lib.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.RegisterReq;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 登录账号
     */
    private EditText mEdit1;
    /**
     * 登录密码
     */
    private EditText mEdit2;
    /**
     * 用户银行卡号
     */
    private EditText mEdit3;
    /**
     * 用户身份证号
     */
    private EditText mEdit4;
    /**
     * 用户姓名
     */
    private EditText mEdit5;
    /**
     * 提交
     */
    private TextView mCommit;
    private ImageView mMineLoginClose;
    private LinearLayout mToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mEdit3 = (EditText) findViewById(R.id.edit3);
        mEdit4 = (EditText) findViewById(R.id.edit4);
        mEdit5 = (EditText) findViewById(R.id.edit5);
        mCommit = (TextView) findViewById(R.id.login_commit);
        mCommit.setOnClickListener(this);

        mMineLoginClose = (ImageView) findViewById(R.id.mine_login_close);
        mToLogin = (LinearLayout) findViewById(R.id.to_login);
        mToLogin.setOnClickListener(this);
        mMineLoginClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.commit) {
            if (!verifyEditText(mEdit1, mEdit2, mEdit3, mEdit4, mEdit5)) return;
            CommApi.instance().register(new RegisterReq(getEditText(mEdit1), getEditText(mEdit2), getEditText(mEdit3), getEditText(mEdit4), getEditText(mEdit5)))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {

                        }

                        @Override
                        public void onNext(ApiResp value) {

                        }
                    });
        }else if (i == R.id.to_login) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (i == R.id.mine_login_close) {
            finish();
        }
    }


}
