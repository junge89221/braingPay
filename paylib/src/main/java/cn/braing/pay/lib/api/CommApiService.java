package cn.braing.pay.lib.api;

import cn.braing.pay.lib.bean.CommRequest;
import cn.braing.pay.lib.bean.ApiResp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * API
 */
public interface CommApiService {

    /*统一的server*/
    @POST("/bryserver/brypf/unExcuteApp.do")
    Observable<ApiResult<ApiResp>> ApiServer(@Body CommRequest params);
}
