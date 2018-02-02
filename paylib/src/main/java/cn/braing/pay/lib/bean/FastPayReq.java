package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/1
 * desc：
 * <pre>
 */

public class FastPayReq {

    public FastPayReq(String ordernumber, String verCode) {
        this.ordernumber = ordernumber;
        this.verCode = verCode;
    }

    /**
     * ordernumber : “201701022565485”
     * verCode : 085451
     */

    private String ordernumber;
    private String verCode;

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
}
