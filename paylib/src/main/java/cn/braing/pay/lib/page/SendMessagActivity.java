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
import cn.braing.pay.lib.bean.SendMessageReq;
import cn.braing.pay.lib.bean.ServerLogEvent;

public class SendMessagActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 订单号
     */
    private EditText mEdit1;
    /**
     * 金额（分）
     */
    private EditText mEdit2;
    /**
     * 备注
     */
    private EditText mEdit3;
    /**
     * 持卡人姓名
     */
    private EditText mEdit4;
    /**
     * 银行卡号
     */
    private EditText mEdit5;
    /**
     * 身份证号
     */
    private EditText mEdit6;
    /**
     * 提交
     */
    private Button mCommit;
    private TextView mResult;
    /**
     * 手机号码
     */
    private EditText mEdit7;
    /**
     * 回调地址
     */
    private EditText mEdit8;
    private TextView mReqData;
    private TextView mRespData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_send_messag);
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
        mEdit6 = (EditText) findViewById(R.id.edit6);
        mCommit = (Button) findViewById(R.id.commit);
        mCommit.setOnClickListener(this);
        mResult = (TextView) findViewById(R.id.result);
        mEdit7 = (EditText) findViewById(R.id.edit7);
        mEdit8 = (EditText) findViewById(R.id.edit8);
        mReqData = (TextView) findViewById(R.id.reqData);
        mRespData = (TextView) findViewById(R.id.respData);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.commit) {
            if (!verifyEditText(mEdit1, mEdit2, mEdit3, mEdit4, mEdit5, mEdit6, mEdit7, mEdit8))
                return;
            CommApi.instance().SendMessage(new SendMessageReq(getEditText(mEdit1), Integer.parseInt(getEditText(mEdit2)), getEditText(mEdit3), getEditText(mEdit4), getEditText(mEdit5), "01", getEditText(mEdit6), getEditText(mEdit7), getEditText(mEdit8)))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            mResult.setText(ex.getErrorCode() + ex.getMsg());                        }

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
