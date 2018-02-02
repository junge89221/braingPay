package cn.braing.pay.lib.page;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.RegisterReq;
import cn.braing.pay.lib.bean.ServerLogEvent;

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
    private Button mCommit;
    private TextView mResult;
    private TextView mReqData;
    private TextView mRespData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_register);
        initView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void initView() {
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mEdit3 = (EditText) findViewById(R.id.edit3);
        mEdit4 = (EditText) findViewById(R.id.edit4);
        mEdit5 = (EditText) findViewById(R.id.edit5);
        mCommit = (Button) findViewById(R.id.commit);
        mCommit.setOnClickListener(this);
        mResult = (TextView) findViewById(R.id.result);
        mReqData = (TextView) findViewById(R.id.reqData);
        mRespData = (TextView) findViewById(R.id.respData);
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

                            mResult.setText(ex.getMsg() + ex.getErrorCode());
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            mResult.setText(value.toString());
                        }
                    });
        }
    }

    @Override
    public void setServerData(ServerLogEvent serverData) {
        if(serverData.isReq){
            mReqData.setText("请求数据:"+serverData.data);
        }else {
            mRespData.setText("返回数据:"+serverData.data);
        }
    }
}
