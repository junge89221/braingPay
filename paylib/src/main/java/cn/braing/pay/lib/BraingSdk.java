package cn.braing.pay.lib;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.braing.pay.lib.page.AliPayH5Activity;
import cn.braing.pay.lib.page.FastPayActivity;
import cn.braing.pay.lib.page.LoginActivity;
import cn.braing.pay.lib.page.OrderDetailActivity;
import cn.braing.pay.lib.page.PaymentActivity;
import cn.braing.pay.lib.page.RegisterActivity;
import cn.braing.pay.lib.page.SendMessagActivity;
import cn.braing.pay.lib.page.WxH5Activity;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.Utils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/2
 * desc：
 * <pre>
 */

public class BraingSdk {
    private PayCallback mPayCallback;

    public  interface PayCallback{
        void   payResultCallback(boolean result);
    }
    private static boolean isInit = false;

    public static void initSDK(Context context, String amCode, String secretKey, String backUrl, String phoneIp) {
        Utils.init(context);
        Data.init(context, amCode, secretKey, backUrl, phoneIp);
        isInit = true;
        ZXingLibrary.initDisplayOpinion(context);
    }


    public static void Login(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, LoginActivity.class) );
    }

    public static void QueryOrder(Context context, String orderNo) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, OrderDetailActivity.class) ;
        intent.putExtra("orderNo", orderNo);
        context.startActivity(intent);
    }

    public static void Register(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, RegisterActivity.class) );
    }


    public static void Payment(Context context,  String orderNo, int orderMoney, String orderMark) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, PaymentActivity.class) ;
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("orderMoney", orderMoney);
        intent.putExtra("orderMark", orderMark);
        context.startActivity(intent);
    }
}
