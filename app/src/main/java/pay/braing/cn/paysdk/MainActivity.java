package pay.braing.cn.paysdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.braing.pay.lib.BraingSdk;
import cn.braing.pay.lib.bean.ServerLogEvent;
import cn.braing.pay.lib.page.AliPayH5Activity;
import cn.braing.pay.lib.page.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    /**
     * 微信请求
     */
    private Button mButton;
    /**
     * 支付宝请求
     */
    private Button mButton2;
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
    private TextView mText;
    /**
     * 输入商户编码：BraingSdk.init(amCode)
     */
    private EditText mEdit1;
    /**
     * 初始化Sdk
     */
    private Button mButton0;


    private boolean isInit =false;
    private EditText mEdit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
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
        mText = (TextView) findViewById(R.id.text);
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mButton0 = (Button) findViewById(R.id.button0);
        mButton0.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                BraingSdk.WeChatPay(this);
                break;
            case R.id.button2:
                BraingSdk.Alipay(this);
                break;
            case R.id.button3:
                BraingSdk.SendMessage(this);
                break;
            case R.id.button4:
                BraingSdk.FastPay(this);
                break;
            case R.id.button5:
                BraingSdk.QueryOrder(this);
                break;
            case R.id.button6:
                BraingSdk.Login(this);
                break;
            case R.id.button7:
                BraingSdk.Register(this);
                break;
            case R.id.button0:
                if (!verifyEditText(mEdit1, mEdit2)) return;
                BraingSdk.initSDK(this,getEditText(mEdit1),getEditText(mEdit2));
                 break;
        }
    }

    @Override
    public void setServerData(ServerLogEvent serverData) {

    }
}
