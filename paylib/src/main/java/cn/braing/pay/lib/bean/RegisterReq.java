package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/2
 * desc：
 * <pre>
 */

public class RegisterReq {

    public RegisterReq(String loginId, String loginPassword, String accNo, String cliIdCard, String cliName) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.accNo = accNo;
        this.cliIdCard = cliIdCard;
        this.cliName = cliName;
    }

    /**
     * loginId : 13890002343
     * loginPassword : 123456
     * accNo : 12345612345654645
     * cliIdCard : 510113199845321122
     * cliName : 张三
     */

    private String loginId;
    private String loginPassword;
    private String accNo;
    private String cliIdCard;
    private String cliName;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getCliIdCard() {
        return cliIdCard;
    }

    public void setCliIdCard(String cliIdCard) {
        this.cliIdCard = cliIdCard;
    }

    public String getCliName() {
        return cliName;
    }

    public void setCliName(String cliName) {
        this.cliName = cliName;
    }
}
