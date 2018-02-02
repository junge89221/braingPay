package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/2
 * desc：
 * <pre>
 */

public class ServerLogEvent {
    public boolean isReq;
    public String data;

    public ServerLogEvent(boolean isReq, String data) {
        this.isReq = isReq;
        this.data = data;
    }
}
