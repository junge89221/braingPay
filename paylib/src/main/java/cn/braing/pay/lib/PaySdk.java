package cn.braing.pay.lib;

import android.content.Context;
import android.content.Intent;

import cn.braing.pay.lib.page.AliPayH5Activity;
import cn.braing.pay.lib.page.FastPayActivity;
import cn.braing.pay.lib.page.LoginActivity;
import cn.braing.pay.lib.page.OrderDetailActivity;
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

public class PaySdk {
    public static void initSDK(Context context) {
        Utils.init(context);
        Data.init(context);
    }

    public static void Alipay(Context context) {
        Utils.getContext().startActivity(new Intent(context, AliPayH5Activity.class).addFlags( FLAG_ACTIVITY_NEW_TASK));
    }

    public static void FastPay(Context context) {
        Utils.getContext().startActivity(new Intent(context, FastPayActivity.class));
    }

    public static void Login(Context context) {
        Utils.getContext().startActivity(new Intent(context, LoginActivity.class));
    }

    public static void QueryOrder(Context context) {
        Utils.getContext().startActivity(new Intent(context, OrderDetailActivity.class));
    }
    public static void Register(Context context) {
        Utils.getContext().startActivity(new Intent(context, RegisterActivity.class));
    }
    public static void SendMessage(Context context) {
        Utils.getContext().startActivity(new Intent(context, SendMessagActivity.class));
    }

    public static void WeChatPay(Context context) {
        Utils.getContext().startActivity(new Intent(context, WxH5Activity.class));
    }
}
