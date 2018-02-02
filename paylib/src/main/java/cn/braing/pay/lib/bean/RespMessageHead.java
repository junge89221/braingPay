package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2017/9/22
 * desc：
 * <pre>
 */

public class RespMessageHead {




    private String amId;
    private String lsh;
    private String sign;
    private String rspTime;
    private String returnCode;
    private String returnMessage;

    public String getAmId() {
        return amId;
    }

    public void setAmId(String amId) {
        this.amId = amId;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRspTime() {
        return rspTime;
    }

    public void setRspTime(String rspTime) {
        this.rspTime = rspTime;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
