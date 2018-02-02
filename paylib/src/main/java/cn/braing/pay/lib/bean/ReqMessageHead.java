package cn.braing.pay.lib.bean;

import java.io.Serializable;


public class ReqMessageHead implements Serializable {



	private String amCode;
	private String lsh;
	private String opFlag;
	private String sign;
	private String reqTime;
	private String clientIp;


	public String getAmCode() {
		return amCode;
	}

	public void setAmCode(String amCode) {
		this.amCode = amCode;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
