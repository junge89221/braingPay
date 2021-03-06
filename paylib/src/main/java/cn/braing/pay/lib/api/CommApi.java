package cn.braing.pay.lib.api;

import java.util.UUID;

import cn.braing.pay.lib.bean.AlipayReq;
import cn.braing.pay.lib.bean.ApiResp;
import cn.braing.pay.lib.bean.CommRequest;
import cn.braing.pay.lib.bean.FastPayReq;
import cn.braing.pay.lib.bean.LoginReq;
import cn.braing.pay.lib.bean.OrderDetailReq;
import cn.braing.pay.lib.bean.RegisterReq;
import cn.braing.pay.lib.bean.ReqMessageHead;
import cn.braing.pay.lib.bean.SendMessageReq;
import cn.braing.pay.lib.bean.WxH5PayReq;
import cn.braing.pay.lib.util.Data;
import cn.braing.pay.lib.util.DateUtil;
import cn.braing.pay.lib.util.MD5Util;
import io.reactivex.Observable;

/**
 * 用户模块网络请求Api
 */
public class CommApi extends HttpApi<CommApiService> {

    private static CommApi api = null;
    private String TEST_ID = Data.getSecretKey();

    @Override
    public Class<CommApiService> initService() {
        return CommApiService.class;
    }

    public static CommApi instance() {
        if (api == null) {
            synchronized (CommApi.class) {
                if (api == null) {
                    api = new CommApi();
                }
            }
        }

        return api;
    }


    /**
     *微信
     */
    public Observable<ApiResp> WxH5Pay(WxH5PayReq bean) {
        return dispose(apiService.ApiServer(getReqBean("YCH5Pay", bean)));
    }
    /**
     *支付宝
     */
    public Observable<ApiResp> AliH5Pay(AlipayReq bean) {
        return dispose(apiService.ApiServer2(getReqBean("scancode_pay", bean)));
    }

    /**
     *发送验证码
     */
    public Observable<ApiResp> SendMessage(SendMessageReq bean) {
        return dispose(apiService.ApiServer(getReqBean("send_verCode", bean)));
    }
    /**
     *快捷支付
     */
    public Observable<ApiResp> FastPay(FastPayReq bean) {
        return dispose(apiService.ApiServer(getReqBean("quick_pay", bean)));
    }
    /**
     *订单查询
     */
    public Observable<ApiResp> queryOrder(OrderDetailReq bean) {
        return dispose(apiService.ApiServer(getReqBean("queryOrder", bean)));
    }
    /**
     *登录
     */
    public Observable<ApiResp> login(LoginReq bean) {
        return dispose(apiService.ApiServer(getReqBean("app_login", bean)));
    }
    /**
     *注册
     */
    public Observable<ApiResp> register(RegisterReq bean) {
        return dispose(apiService.ApiServer(getReqBean("app_register", bean)));
    }

    private CommRequest getReqBean(String method, Object bean) {
        ReqMessageHead reqMessageHead = new ReqMessageHead();
        reqMessageHead.setAmCode(Data.getAmCode());
        String lsh = UUID.randomUUID().toString();
        reqMessageHead.setLsh(lsh);
        reqMessageHead.setOpFlag(method);
        reqMessageHead.setReqTime(DateUtil.getStringNow());
        if("YCH5Pay".equals(method)) {
            reqMessageHead.setClientIp(Data.getIp());
        }
        reqMessageHead.setSign(MD5Util.encodeMD5(lsh + TEST_ID));
//        new LogDialog( AppManager.getAppManager().getTopActivity(),new Gson().toJson(new CommRequest(reqMessageHead, bean))).show();
         return new CommRequest(reqMessageHead, bean);

    }


}
