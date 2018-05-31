package wby.laowang.monthshopp.presenter;


import wby.laowang.monthshopp.DeleteBean;
import wby.laowang.monthshopp.bean.ShoppCartBean;
import wby.laowang.monthshopp.model.IModel;
import wby.laowang.monthshopp.view.IDelete;
import wby.laowang.monthshopp.view.IShoppCart;

public interface IPresenter {

    //p的购物车请求数据
    void showShoppCartToView(IModel iModel, IShoppCart iShoppCart);


    void getDataShoppCart(ShoppCartBean shoppCartBean);


    //p的删除购物车请求数据
    void showDeleteCartToView(IModel iModel, IDelete iDelete);


    void getDataDeleteCart(DeleteBean deleteBean);

}
