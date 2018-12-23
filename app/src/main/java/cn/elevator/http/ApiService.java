package cn.elevator.http;

import java.util.List;

import cn.elevator.bean.BannerData;
import cn.elevator.bean.LoginData;
import cn.elevator.bean.TaskData;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 网络请求接口设置
 * Created by DamonJiang on 2018/8/13 0013.
 */
public interface ApiService {
    /**
     * 登录
     * @return
     */
    @POST(Api.LOGIN)
    Observable<LoginData> login(@Body RequestBody body);

    /**
     *  获取广告数据
     * @return
     */
    @GET(Api.Banner)
    Observable<BannerData> getBannerData();

    /**
     *  获取检验任务数据数据
     * @return
     */
    @POST(Api.TESK_LIST)
    Observable<TaskData> getTaskData(@Body RequestBody body);

}
