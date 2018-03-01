package cn.braing.pay.lib.page;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.FastPayReq;
import cn.braing.pay.lib.bean.SendMessageReq;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.MoneyUtils;

public class BraFastPayActivity extends BraBaseActivity implements View.OnClickListener {


    /**
     * 订单编号
     */
    private TextView mOrderNo;
    /**
     * 订单编号
     */
    private TextView mOrderMoney;
    /**
     * 订单编号
     */
    private TextView mOrdermarky;
    /**
     * 请输入持卡人姓名
     */
    private EditText mCardUserName;
    /**
     * 请输入身份证号码
     */
    private EditText mCardUserID;
    /**
     * 请输入银行卡号
     */
    private EditText mCardNo;
    /**
     * 请输入手机号码
     */
    private EditText mCardMobile;
    /**
     * 获取验证码
     */
    private TextView mMineUpdatePayPwdVercodeGet;
    /**
     * 支付
     */
    private TextView mMineUpdatePayPwdNext;
    private CountDown countDown;

    private String OrderNo;
    private int OrderMoney;
    private String OrderMark;
    private EditText mMsgCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.braactivity_fast_pay);
        initView();
        OrderNo = getIntent().getStringExtra("orderNo");
        OrderMoney = getIntent().getIntExtra("orderMoney", 0);
        OrderMark = getIntent().getStringExtra("orderMark");
        mOrderNo.setText(OrderNo);
        mOrderMoney.setText(MoneyUtils.getMoney((OrderMoney * 1.0 / 100)));
        mOrdermarky.setText(OrderMark);
        countDown = new CountDown(60 * 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDown != null) {
            countDown.cancel();
            countDown = null;
        }
    }

    private void initView() {

        mOrderNo = (TextView) findViewById(R.id.orderNo);
        mOrderMoney = (TextView) findViewById(R.id.orderMoney);
        mOrdermarky = (TextView) findViewById(R.id.ordermarky);
        mCardUserName = (EditText) findViewById(R.id.card_userName);
        mCardUserID = (EditText) findViewById(R.id.card_userID);
        mCardNo = (EditText) findViewById(R.id.card_No);
        mCardMobile = (EditText) findViewById(R.id.card_mobile);
        mMsgCode = (EditText) findViewById(R.id.msg_code);
        mMineUpdatePayPwdVercodeGet = (TextView) findViewById(R.id.mine_update_pay_pwd_vercode_get);
        mMineUpdatePayPwdVercodeGet.setOnClickListener(this);
        mMineUpdatePayPwdNext = (TextView) findViewById(R.id.mine_update_pay_pwd_next);
        mMineUpdatePayPwdNext.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mine_update_pay_pwd_vercode_get) {
            mMineUpdatePayPwdVercodeGet.setEnabled(false);
            CommApi.instance().SendMessage(new SendMessageReq(OrderNo, OrderMoney, OrderMark, getEditText(mCardUserName), getEditText(mCardNo), "01", getEditText(mCardUserID), getEditText(mCardMobile), Data.getBackUrl()))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            mMineUpdatePayPwdVercodeGet.setEnabled(true);
                            Toast.makeText(BraFastPayActivity.this, ex.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            countDown.start();
                        }
                    });


        } else if (i == R.id.mine_update_pay_pwd_next) {
            CommApi.instance().FastPay(new FastPayReq(OrderNo, getEditText(mMsgCode)))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            Toast.makeText(BraFastPayActivity.this, ex.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            Toast.makeText(BraFastPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

        }
    }


    private class CountDown extends CountDownTimer {

        CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mMineUpdatePayPwdVercodeGet.setText(String.format(Locale.getDefault(), "%dS", millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            mMineUpdatePayPwdVercodeGet.setText("获取验证码");
            mMineUpdatePayPwdVercodeGet.setEnabled(true);
        }
    }
}
