package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/1/30
 * desc：
 * <pre>
 */

public class WxH5PayReq {
    public WxH5PayReq(int amount, String backurl, String body, String ordernumber, String paymenttype) {
        this.amount = amount;
        this.backurl = backurl;
        this.body = body;
        this.ordernumber = ordernumber;
        this.paymenttype = paymenttype;
     }

    /**
     * amount : 1
     * backurl : xxxxxxx
     * body : 微信H5
     * ordernumber : BRY1504514784911
     * paymenttype : 14
     *  clientIp : 118.80.78.24
     */

    private int amount;
    private String backurl;
    private String body;
    private String ordernumber;
    private String paymenttype;



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }
}
