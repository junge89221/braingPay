package cn.braing.pay.lib.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.AlipayReq;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.WxH5PayReq;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.MoneyUtils;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {

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
    private RelativeLayout mWxH5Pay;
    private RelativeLayout mAliH5Pay;
    private RelativeLayout mYinlianPay;
    private RelativeLayout mWxScanPay;
    private String OrderNo;
    private int OrderMoney;
    private String OrderMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initView();
        OrderNo = getIntent().getStringExtra("orderNo");
        OrderMoney = getIntent().getIntExtra("orderMoney", 0);
        OrderMark = getIntent().getStringExtra("orderMark");
        mOrderNo.setText(OrderNo);
        mOrderMoney.setText(MoneyUtils.getMoney((OrderMoney * 1.0 / 100)));
        mOrdermarky.setText(OrderMark);
    }

    private void initView() {
        mOrderNo = (TextView) findViewById(R.id.orderNo);
        mOrderMoney = (TextView) findViewById(R.id.orderMoney);
        mOrdermarky = (TextView) findViewById(R.id.ordermarky);
        mWxH5Pay = (RelativeLayout) findViewById(R.id.wxH5Pay);
        mWxH5Pay.setOnClickListener(this);
        mAliH5Pay = (RelativeLayout) findViewById(R.id.AliH5Pay);
        mAliH5Pay.setOnClickListener(this);
        mYinlianPay = (RelativeLayout) findViewById(R.id.yinlianPay);
        mYinlianPay.setOnClickListener(this);
        mWxScanPay = (RelativeLayout) findViewById(R.id.wxScanPay);
        mWxScanPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.wxH5Pay) {
            CommApi.instance().WxH5Pay(new WxH5PayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "14"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                        }

                        @Override
                        public void onNext(ApiResp value) {

                        }
                    });
        } else if (i == R.id.AliH5Pay) {
            CommApi.instance().AliH5Pay(new AlipayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "12", "12"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                        }

                        @Override
                        public void onNext(ApiResp value) {

                        }
                    });
        } else if (i == R.id.yinlianPay) {
            Intent intent = new Intent(this, FastPayActivity.class);
            intent.putExtra("orderNo",OrderNo);
            intent.putExtra("orderMoney",OrderMoney);
            intent.putExtra("orderMark",OrderMark);
            startActivity(intent);
        } else if (i == R.id.wxScanPay) {
            CommApi.instance().AliH5Pay(new AlipayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "13", "13"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                        }

                        @Override
                        public void onNext(ApiResp value) {

                        }
                    });

        }
    }
}
