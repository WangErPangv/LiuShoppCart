package wby.laowang.monthshopp.model;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wby.laowang.monthshopp.DeleteBean;
import wby.laowang.monthshopp.bean.ShoppCartBean;
import wby.laowang.monthshopp.presenter.IPresenter;
import wby.laowang.monthshopp.util.RetrofitUtil;


/**
 * model的具体实现
 */

public class ModelFusion implements IModel {

    private IPresenter iPresenter;

    public ModelFusion(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getDataShoppCart(Map<String, String> map) {

        //网络请求
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        MyService myService = retrofitUtil.createRequest(MyService.class);
        Observable<ShoppCartBean> observable = myService.getshoppcart(map);
        //请求执行
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShoppCartBean shoppCartBean) {

                        iPresenter.getDataShoppCart(shoppCartBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDataDeleteCart(Map<String, String> map) {

        //网络请求
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        MyService myService = retrofitUtil.createRequest(MyService.class);
        Observable<DeleteBean> observable = myService.getdeletecart(map);
        //请求执行
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteBean deleteBean) {

                        iPresenter.getDataDeleteCart(deleteBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
