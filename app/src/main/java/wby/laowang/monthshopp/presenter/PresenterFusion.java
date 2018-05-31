package wby.laowang.monthshopp.presenter;

import java.util.HashMap;
import java.util.Map;

import wby.laowang.monthshopp.DeleteBean;
import wby.laowang.monthshopp.bean.ShoppCartBean;
import wby.laowang.monthshopp.model.IModel;
import wby.laowang.monthshopp.view.IDelete;
import wby.laowang.monthshopp.view.IShoppCart;


/**
 * Presenter的操作类
 */

public class PresenterFusion implements IPresenter {


    private IShoppCart iShoppCart;
    private IDelete iDelete;


    @Override
    public void showShoppCartToView(IModel iModel, IShoppCart iShoppCart) {
        this.iShoppCart = iShoppCart;
        Map<String,String> map = new HashMap<>();
        map.put("uid",iShoppCart.uid());
        iModel.getDataShoppCart(map);
    }

    @Override
    public void getDataShoppCart(ShoppCartBean shoppCartBean) {
        iShoppCart.showShoppCart(shoppCartBean);
    }

    @Override
    public void showDeleteCartToView(IModel iModel, IDelete iDelete) {
        this.iDelete = iDelete;
        Map<String,String> map = new HashMap<>();
        map.put("uid",iDelete.duid());
        map.put("pid",String.valueOf(iDelete.dpid()));
        iModel.getDataDeleteCart(map);
    }

    @Override
    public void getDataDeleteCart(DeleteBean deleteBean) {
        iDelete.showDeleteCart(deleteBean);
    }


}
