package wby.laowang.monthshopp.model;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import wby.laowang.monthshopp.DeleteBean;
import wby.laowang.monthshopp.bean.ShoppCartBean;


public interface MyService {

    //购物车
    @GET("product/getCarts")
    Observable<ShoppCartBean> getshoppcart(@QueryMap Map<String, String> map);

    //删除购物车
    @GET("product/deleteCart")
    Observable<DeleteBean> getdeletecart(@QueryMap Map<String, String> map);

}
