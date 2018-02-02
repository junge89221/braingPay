package cn.braing.pay.lib.bean;

/**
 * <pre>
 * author：张俊
 * date： 2017/9/22
 * desc：
 * <pre>
 */

public class CommRequest {
    private ReqMessageHead reqMessageHead ;
    private Object reqMessageBody;

    public ReqMessageHead getReqMessageHead() {
        return reqMessageHead;
    }

    public void setReqMessageHead(ReqMessageHead reqMessageHead) {
        this.reqMessageHead = reqMessageHead;
    }

    public Object getReqMessageBody() {
        return reqMessageBody;
    }

    public void setReqMessageBody(Object reqMessageBody) {
        this.reqMessageBody = reqMessageBody;
    }

    public CommRequest(ReqMessageHead reqMessageHead, Object reqMessageBody) {
        this.reqMessageHead = reqMessageHead;
        this.reqMessageBody = reqMessageBody;
    }
}
