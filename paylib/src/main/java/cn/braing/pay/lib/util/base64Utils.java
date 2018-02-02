package cn.braing.pay.lib.util;

import android.util.Base64;

/**
 * <pre>
 * author：张俊
 * date： 2018/1/31
 * desc：
 * <pre>
 */

public class base64Utils {
    /**
     * Base64解码
     *
     * @param source
     * @return
     */
    public static byte[] decodeBase64( String source )
    {
        return Base64.decode(source, Base64.DEFAULT);
    }

    /**
     * base64编码
     *
     * @param source
     * @return
     */
    public static byte[] encodeBase64( byte[] source )
    {
        return Base64.encode(source, 0, source.length, Base64.DEFAULT);
    }
}
