package cn.braing.pay.lib.page;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.AlipayReq;
import cn.braing.pay.lib.bean.ApiResp;

public class BraAliPayH5Activity extends BraBaseActivity implements View.OnClickListener {

    /**
     * 金额（分）
     */
    private EditText mEdit1;
    /**
     * 回调
     */
    private EditText mEdit2;
    /**
     * 备注
     */
    private EditText mEdit3;
    /**
     * 订单号
     */
    private EditText mEdit4;
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
         setContentView(R.layout.braactivity_ali_pay_h5);
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
            if (!verifyEditText(mEdit1, mEdit2, mEdit3, mEdit4)) return;
            CommApi.instance().AliH5Pay(new AlipayReq(Integer.parseInt(getEditText(mEdit1)), getEditText(mEdit2), getEditText(mEdit3), getEditText(mEdit4), "12", "12"))
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


}
