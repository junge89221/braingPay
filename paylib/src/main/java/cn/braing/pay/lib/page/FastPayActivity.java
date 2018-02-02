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
import cn.braing.pay.lib.bean.FastPayReq;
import cn.braing.pay.lib.bean.ServerLogEvent;

public class FastPayActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 订单号
     */
    private EditText mEdit1;
    /**
     * 验证码
     */
    private EditText mEdit2;
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
        setContentView(R.layout.activity_fast_pay);
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
            if (!verifyEditText(mEdit1, mEdit2)) return;
            CommApi.instance().FastPay(new FastPayReq(getEditText(mEdit1), getEditText(mEdit2)))
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
