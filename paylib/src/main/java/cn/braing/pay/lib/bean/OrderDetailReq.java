package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/1
 * desc：
 * <pre>
 */

public class OrderDetailReq {
    public OrderDetailReq(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * ordernumber : BRY1503040122523
     */

    private String ordernumber;

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
