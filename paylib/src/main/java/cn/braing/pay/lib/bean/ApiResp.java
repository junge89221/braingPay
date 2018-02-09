package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/1/30
 * desc：
 * <pre>
 */

public class ApiResp {
private  String rspcode;
private  String rspmsg;

    public String getRespmsg() {
        return respmsg;
    }

    public void setRespmsg(String respmsg) {
        this.respmsg = respmsg;
    }

    private  String respmsg;
private  String mweb_url;
    /**
     * amount : 1000
     * body : XXX
     * paymenttype : 13
     * ordernumber : BRY1503040122523
     */

    private String amount;
    private String body;
    private String paymenttype;
    private String ordernumber;
    private String qrcodeurl;

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl;
    }

    public String getRspcode() {
        return rspcode;
    }

    public void setRspcode(String rspcode) {
        this.rspcode = rspcode;
    }

    public String getRspmsg() {
        return rspmsg;
    }

    public void setRspmsg(String rspmsg) {
        this.rspmsg = rspmsg;
    }

    public String getMweb_url() {
        return mweb_url;
    }

    public void setMweb_url(String mweb_url) {
        this.mweb_url = mweb_url;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Override
    public String toString() {
        return "ApiResp{" +
                "rspcode='" + rspcode + '\'' +
                ", rspmsg='" + rspmsg + '\'' +
                ", mweb_url='" + mweb_url + '\'' +
                ", amount='" + amount + '\'' +
                ", body='" + body + '\'' +
                ", paymenttype='" + paymenttype + '\'' +
                ", ordernumber='" + ordernumber + '\'' +
                '}';
    }
}
