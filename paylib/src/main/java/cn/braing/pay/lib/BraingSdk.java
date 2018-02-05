package cn.braing.pay.lib;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.braing.pay.lib.page.AliPayH5Activity;
import cn.braing.pay.lib.page.FastPayActivity;
import cn.braing.pay.lib.page.LoginActivity;
import cn.braing.pay.lib.page.OrderDetailActivity;
import cn.braing.pay.lib.page.RegisterActivity;
import cn.braing.pay.lib.page.SendMessagActivity;
import cn.braing.pay.lib.page.WxH5Activity;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.Utils;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/2
 * desc：
 * <pre>
 */

public class BraingSdk {
    private static boolean isInit = false;

    public static void initSDK(Context context,String amCode,String secretKey) {
        Utils.init(context);
        Data.init(context,amCode,  secretKey);
        isInit = true;
    }

    public static void Alipay(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, AliPayH5Activity.class));
    }

    public static void FastPay(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, FastPayActivity.class));
    }

    public static void Login(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void QueryOrder(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, OrderDetailActivity.class));
    }

    public static void Register(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    public static void SendMessage(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, SendMessagActivity.class));
    }

    public static void WeChatPay(Context context) {
        if (!isInit) {
            Toast.makeText(context, "请先输入商户编码初始化", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, WxH5Activity.class));
    }
}
