package cn.braing.pay.lib.page;

import android.os.Bundle;
import android.widget.TextView;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.OrderDetailReq;

public class OrderDetailActivity extends BraBaseActivity {


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
     * 订单编号
     */
    private TextView mOrderPayType;
    /**
     * 订单编号
     */
    private TextView mOrderPayresult;
    private String OrderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        OrderNo = getIntent().getStringExtra("orderNo");
        initView();
        initData();
    }

    private void initData() {
        CommApi.instance().queryOrder(new OrderDetailReq(OrderNo))
                .subscribe(new SimpleSubscriber<ApiResp>(this, true) {
                    @Override
                    protected void onError(ApiException ex) {

                    }
                    @Override
                    public void onNext(ApiResp value) {
                        if(value!=null){
                            mOrderNo.setText(value.getOrdernumber());
                            mOrderMoney.setText(value.getAmount());
                            mOrdermarky.setText(value.getBody());
                            if("12".equals(value.getPaymenttype())){
                                mOrderPayType.setText("支付宝支付");
                            }else if("13".equals(value.getPaymenttype())){
                                mOrderPayType.setText("微信支付");
                            }else if("31".equals(value.getPaymenttype())){
                                mOrderPayType.setText("QQ钱包");
                            }else if("15".equals(value.getPaymenttype())){
                                mOrderPayType.setText("微信扫码支付");
                            }else {
                                mOrderPayType.setText("其他");
                            }
                            mOrderPayresult.setText(value.getRespmsg()  );
                        }

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {

        mOrderNo = (TextView) findViewById(R.id.orderNo);
        mOrderMoney = (TextView) findViewById(R.id.orderMoney);
        mOrdermarky = (TextView) findViewById(R.id.ordermarky);
        mOrderPayType = (TextView) findViewById(R.id.orderPayType);
        mOrderPayresult = (TextView) findViewById(R.id.orderPayresult);
    }



}
