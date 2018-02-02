package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/2
 * desc：
 * <pre>
 */

public class LoginReq {

    public LoginReq(String loginId, String loginPassword) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    /**
     * loginId : 13890002343
     * loginPassword : 123456
     */

    private String loginId;
    private String loginPassword;

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
}
