package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/1/31
 * desc：
 * <pre>
 */

public class AlipayReq {

    public AlipayReq(int amount, String backurl, String body, String ordernumber, String paymenttype, String subpaymenttype) {
        this.amount = amount;
        this.backurl = backurl;
        this.body = body;
        this.ordernumber = ordernumber;
        this.paymenttype = paymenttype;
        this.subpaymenttype = subpaymenttype;
    }

    /**
     * amount : 1
     * backurl : xxxxxxx
     * body : XX-收款
     * ordernumber : BRY1503040122523
     * paymenttype : 12
     * subpaymenttype : 12
     */

    private int amount;
    private String backurl;
    private String body;
    private String ordernumber;
    private String paymenttype;
    private String subpaymenttype;

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

    public String getSubpaymenttype() {
        return subpaymenttype;
    }

    public void setSubpaymenttype(String subpaymenttype) {
        this.subpaymenttype = subpaymenttype;
    }
}
