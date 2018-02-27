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


    public static void Login() {
        if (!isInit) {
//            Toast.makeText(Utils.getContext(), "请初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Utils.getContext().startActivity(new Intent(Utils.getContext(), LoginActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK)  );
    }

    public static void QueryOrder( String orderNo) {
        if (!isInit) {
//            Toast.makeText(Utils.getContext(), "请初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Utils.getContext(), OrderDetailActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK) ;
        intent.putExtra("orderNo", orderNo);
        Utils.getContext().startActivity(intent);
    }

    public static void Register() {
        if (!isInit) {
//            Toast.makeText(Utils.getContext(), "请初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Utils.getContext().startActivity(new Intent(Utils.getContext(), RegisterActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK)  );
    }


    public static void Payment( String orderNo, int orderMoney, String orderMark) {
        if (!isInit) {
//            Toast.makeText(Utils.getContext(), "请初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Utils.getContext(), PaymentActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("orderMoney", orderMoney);
        intent.putExtra("orderMark", orderMark);
        Utils.getContext().startActivity(intent);
    }
}
