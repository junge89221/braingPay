package cn.braing.pay.lib.util;

import android.content.Context;

import cn.braing.pay.lib.bean.User;
import cn.braing.pay.lib.db.UserManager;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : 初始化内存相关数据
 * </pre>
 */
public class Data {


    public static String getIp() {
        return ip;
    }

    private static String ip;

    public static String getBackUrl() {
        return backUrl;
    }

    private static String backUrl;

    public static String getSecretKey() {
        return secretKey;
    }

    private static String secretKey;

    public static String getAmCode() {
        return amCode;
    }

    private static String amCode;

    public static void setUser(User mUser) {
        Data.User = mUser;
    }


    public static User getUser() {
        return User;
    }

    private static User User;


    public static boolean isLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        Data.isLogin = isLogin;
    }

    private static boolean isLogin = false;

    private Data() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context,String amCode,String secretKey,String backUrl,String ip) {
         User = UserManager.getInstance(context).getUser();
        isLogin = (User != null);
        Data.amCode = amCode;
        Data.secretKey = secretKey;
        Data.backUrl = backUrl;
        Data.ip = ip;
    }

    public static void exitAccount(Context context) {
        UserManager.getInstance(context).deleteUser();
        Utils.getSpUtils().remove("User");
        User = null;
        isLogin = false;
    }


}