package wby.laowang.monthshopp.view;

import wby.laowang.monthshopp.DeleteBean;


public interface IDelete {

    //删除购物车
    void showDeleteCart(DeleteBean deleteBean);

    //购物车的uid
    String duid();

    //购物车的pid
    int dpid();

}
