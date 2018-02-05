package cn.braing.pay.lib.page;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.LoginReq;

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    /**
     * 登录账号
     */
    private EditText mEdit1;
    /**
     * 登录密码
     */
    private EditText mEdit2;
    /**
     * 提交
     */
    private TextView mCommit;
    private LinearLayout mToRegister;
    private ImageView mMineLoginClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mCommit = (TextView) findViewById(R.id.login_commit);
        mCommit.setOnClickListener(this);

        mToRegister = (LinearLayout) findViewById(R.id.to_register);
        mToRegister.setOnClickListener(this);
        mEdit1.addTextChangedListener(this);
        mEdit2.addTextChangedListener(this);
        mMineLoginClose = (ImageView) findViewById(R.id.mine_login_close);
        mMineLoginClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.commit) {
            if (!verifyEditText(mEdit1, mEdit2)) return;
            CommApi.instance().login(new LoginReq(getEditText(mEdit1), getEditText(mEdit2)))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {

                        }

                        @Override
                        public void onNext(ApiResp value) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

        } else if (i == R.id.to_register) {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }else if (i == R.id.mine_login_close) {
           finish();
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mCommit.setEnabled(!TextUtils.isEmpty(getEditText(mEdit1)) && !TextUtils.isEmpty(getEditText(mEdit1)));
    }
}
