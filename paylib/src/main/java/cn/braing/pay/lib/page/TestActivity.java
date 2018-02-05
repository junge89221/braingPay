package cn.braing.pay.lib.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.api.CommApi;
import cn.braing.pay.lib.api.exception.ApiException;
import cn.braing.pay.lib.api.subscriber.SimpleSubscriber;
import cn.braing.pay.lib.bean.AlipayReq;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.FastPayReq;
import cn.braing.pay.lib.bean.OrderDetailReq;
import cn.braing.pay.lib.bean.SendMessageReq;
import cn.braing.pay.lib.bean.WxH5PayReq;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请求
     */
    private Button mButton;
    /**
     * 支付宝请求
     */
    private Button mButton2;
     private TextView mText;
    /**
     * 发送验证码
     */
    private Button mButton3;
    /**
     * 快捷支付
     */
    private Button mButton4;
    /**
     * 订单查询
     */
    private Button mButton5;
    /**
     * 登录
     */
    private Button mButton6;
    /**
     * 注册
     */
    private Button mButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            startActivity(new Intent(this, WxH5Activity.class));
        } else if (v.getId() == R.id.button2) {
            startActivity(new Intent(this, AliPayH5Activity.class));
        } else if (v.getId() == R.id.button3) {
            startActivity(new Intent(this, SendMessagActivity.class));
        } else if (v.getId() == R.id.button4) {
            startActivity(new Intent(this, FastPayActivity.class));
        } else if (v.getId() == R.id.button5) {
            startActivity(new Intent(this, OrderDetailActivity.class));
        } else if (v.getId() == R.id.button6) {
            startActivity(new Intent(this, LoginActivity.class));

        } else if (v.getId() == R.id.button7) {
            startActivity(new Intent(this, RegisterActivity.class));

        }

    }
}
