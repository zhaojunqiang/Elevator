package cn.elevator.http;


import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.elevator.BuildConfig;
import cn.elevator.app.App;
import cn.elevator.config.Constant;
import cn.elevator.http.Cookies.CookieManger;
import cn.elevator.utils.SharedPrefUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static cn.elevator.config.Constant.TOKEN;


/**
 * Created by Administrator on 2017/4/10.
 */

public class HttpClient {
    private static final int CONNECT_TIME = 10;
    private static final int READ_TIME = 30;
    private static final int WRITE_TIME = 30;
    private HttpClient() {
    }

    private static class ClientHolder {

        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加对Cookies的管理
                .cookieJar(new CookieManger(App.getContext()))
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    if (TextUtils.isEmpty(SharedPrefUtils.getObj(Constant.TOKEN))) {
                        return chain.proceed(originalRequest);
                    }
                    Request request = originalRequest.newBuilder().addHeader("X-CSRF-TOKEN", SharedPrefUtils.getObj(Constant.TOKEN)).build();
                    return chain.proceed(request);
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getInstance() {
        return ClientHolder.retrofit;
    }
}
