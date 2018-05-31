package wby.laowang.monthshopp.util;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的工具类
 */

public class RetrofitUtil {
    //单例模式
    private static RetrofitUtil retrofitUtil;
    private final Retrofit retrofit;

    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    //初始化Retrofit
    public RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiNetWork.Base_Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    //指定服务
    public <T> T createRequest(Class<T> clz) {
        T t = retrofit.create(clz);
        return t;
    }
}
