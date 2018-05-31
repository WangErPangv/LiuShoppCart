package wby.laowang.monthshopp.model;

import java.util.Map;

public interface IModel {

    //获取购物车数据
    void getDataShoppCart(Map<String, String> map);

    //获取删除购物车数据
    void getDataDeleteCart(Map<String, String> map);
}
