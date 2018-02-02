package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/1
 * desc：
 * <pre>
 */

public class SendMessageReq {

    /**
     * ordernumber : 201701022565485
     * amount : 100
     * body : XX-快捷支付
     * cardName : 张三
     * cardNo : 6221700286545XXXXXXX
     * cardKind : 01
     * cardId : 500XXXXXXXXXXXXXXX
     * phone : 159XXXXXXX
     * backUrl : http: //XXXXXXXX
     */

    private String ordernumber;
    private int amount;
    private String body;
    private String cardName;
    private String cardNo;
    private String cardKind;
    private String cardId;
    private String phone;
    private String backUrl;

    public SendMessageReq(String ordernumber, int amount, String body, String cardName, String cardNo, String cardKind, String cardId, String phone, String backUrl) {
        this.ordernumber = ordernumber;
        this.amount = amount;
        this.body = body;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.cardKind = cardKind;
        this.cardId = cardId;
        this.phone = phone;
        this.backUrl = backUrl;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardKind() {
        return cardKind;
    }

    public void setCardKind(String cardKind) {
        this.cardKind = cardKind;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
}
