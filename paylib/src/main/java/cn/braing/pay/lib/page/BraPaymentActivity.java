package cn.braing.pay.lib.page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.AlipayReq;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.WxH5PayReq;
import cn.braing.pay.lib.util.AppManager;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.MoneyUtils;
import cn.braing.pay.lib.view.ZxingDialog;
import cn.braing.pay.lib.view.webview.Html5Activity;

public class BraPaymentActivity extends BraBaseActivity implements View.OnClickListener {

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
        setContentView(R.layout.braactivity_payment);
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

//            startActivity(new Intent(BraPaymentActivity.this, Html5Activity.class).putExtra(Html5Activity.URL,"http://wxpay.wxutil.com/mch/pay/h5.v2.php").putExtra(Html5Activity.TITLE,"微信支付"));

            CommApi.instance().WxH5Pay(new WxH5PayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "14"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            Toast.makeText(BraPaymentActivity.this, ex.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            if(!TextUtils.isEmpty(value.getMweb_url()))
                            startActivity(new Intent(BraPaymentActivity.this, Html5Activity.class).putExtra(Html5Activity.URL,value.getMweb_url()).putExtra(Html5Activity.TITLE,"微信支付"));

                        }
                    });
        } else if (i == R.id.AliH5Pay) {
            CommApi.instance().AliH5Pay(new AlipayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "12", "12"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            Toast.makeText(BraPaymentActivity.this, ex.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            if(!TextUtils.isEmpty(value.getQrcodeurl()))
                                startActivity(new Intent(BraPaymentActivity.this, Html5Activity.class).putExtra(Html5Activity.URL,value.getQrcodeurl()).putExtra(Html5Activity.TITLE,"支付宝支付"));
//                            new ZxingDialog(AppManager.getAppManager().getTopActivity(),value.getQrcodeurl(),"支付宝扫码支付").show();
                        }
                    });
        } else if (i == R.id.yinlianPay) {
            Intent intent = new Intent(this, BraFastPayActivity.class);
            intent.putExtra("orderNo",OrderNo);
            intent.putExtra("orderMoney",OrderMoney);
            intent.putExtra("orderMark",OrderMark);
            startActivity(intent);
        } else if (i == R.id.wxScanPay) {
//            new ZxingDialog(AppManager.getAppManager().getTopActivity(),"www.baidu.com").show();
            CommApi.instance().AliH5Pay(new AlipayReq(OrderMoney, Data.getBackUrl(), OrderMark, OrderNo, "13", "13"))
                    .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                        @Override
                        protected void onError(ApiException ex) {
                            Toast.makeText(BraPaymentActivity.this, ex.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ApiResp value) {
                            if(!TextUtils.isEmpty(value.getQrcodeurl()))
                            new ZxingDialog(AppManager.getAppManager().getTopActivity(),value.getQrcodeurl(),"微信扫码支付").show();
                        }
                    });

        }
    }
}
