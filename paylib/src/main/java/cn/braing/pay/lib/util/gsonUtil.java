package cn.braing.pay.lib.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class gsonUtil {

    /**
     * 实现单例
     */
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 隐藏默认的构造方法
     */
    private gsonUtil() {

    }

    /**
     * 将对象转换成json格式
     *
     * @param ts
     * @return
     */
    public static String toJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * 返回cla 类型的list数组
     *
     * @param s
     * @param cla
     * @return
     */
    public static <T extends Object> T jsonToBeanList(String s, Class<?> cla) {

        List<Object> ls = new ArrayList<Object>();
        JSONArray ss;
        try {
            ss = new JSONArray(s);
            for (int i = 0; i < ss.length(); i++) {
                String str = ss.getString(i);
                Object a = jsonToBean(str, cla);
                ls.add(a);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return (T) ls;
    }

    /**
     * 返回cla 类型的list数组
     *
     * @param s
     * @param cla
     * @return
     */
    public static <T extends Object> T jsonToBeanList(String s, String cla) {

        List<Object> ls = new ArrayList<Object>();
        JSONArray ss;
        try {
            ss = new JSONArray(s);
            for (int i = 0; i < ss.length(); i++) {
                String str = ss.getString(i);
                Object a = jsonToBean(str, cla);
                ls.add(a);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return (T) ls;
    }

    /**
     * 将jsonStr转换成cl对象
     *
     * @param jsonStr
     * @return
     */
    public static <T extends Object> T jsonToBean(String jsonStr, Class<?> cl) {
        Object obj = null;
        if (gson != null) {
            if (!TextUtils.isEmpty(jsonStr))
                obj = gson.fromJson(jsonStr, cl);
        }
        return (T) obj;
    }


    /**
     * 根据
     *
     * @param jsonStr
     * @param classType
     * @param <T>
     * @return
     */
    public static <T extends Object> T jsonToBean(String jsonStr, String classType) {

        Class c = null;
        try {
            c = Class.forName(classType);
        } catch (ClassNotFoundException e) {
            c = Object.class;
            e.printStackTrace();
        }
        return (T) jsonToBean(jsonStr, c);
    }


    public static <T extends Object> T json2b(String jsonStr, String classType) {


        if (jsonStr.trim().startsWith("[")) {
            return jsonToBeanList(jsonStr, classType);

        } else {
            return jsonToBean(jsonStr, classType);
        }


    }

    /**
     * 根据 给的 jsonStr 自动的生成对象或者数组.
     *
     * @param jsonStr
     * @param classType
     * @param <T>
     * @return
     */
    public static <T extends Object> T fromJson(String jsonStr, Class classType) {
        if (jsonStr.trim().startsWith("[")) {
            return (T) jsonToBeanList(jsonStr, classType);

        } else {
            return (T) jsonToBean(jsonStr, classType);
        }
    }
}