package pay.braing.cn.paysdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.braing.pay.lib.BraingSdk;
import cn.braing.pay.lib.page.BraBaseActivity;
import cn.braing.pay.lib.util.DateUtil;

public class MainActivity extends BraBaseActivity implements View.OnClickListener {


    /**
     * 微信请求
     */
    private Button mButton;
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
    private EditText mEdit3;
    private EditText mEdit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text);
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mEdit3 = (EditText) findViewById(R.id.edit3);
        mEdit4 = (EditText) findViewById(R.id.edit4);
        mButton0 = (Button) findViewById(R.id.button0);
        mButton0.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                BraingSdk.Payment(this, DateUtil.getStringNow2(),500,"测试订单");
                 break;
            case R.id.button5:
                BraingSdk.QueryOrder(this, "20180208214210");
                break;
            case R.id.button6:
                BraingSdk.Login(this );
                break;
            case R.id.button7:
                BraingSdk.Register(this );
                break;
            case R.id.button0:
//                BraingSdk.initSDK(this,"231231","4423423","baidu.com","666");
                if (!verifyEditText(mEdit1, mEdit2,mEdit3,mEdit4)) return;
                BraingSdk.initSDK(this,getEditText(mEdit1),getEditText(mEdit2),getEditText(mEdit3),getEditText(mEdit4));
                 break;
        }
    }


}
