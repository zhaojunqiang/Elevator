package cn.elevator.http;

import java.util.List;

import cn.elevator.bean.BannerData;
import cn.elevator.bean.FormData;
import cn.elevator.bean.LoginData;
import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveResult;
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
    @POST(Api.Banner)
    Observable<BannerData> getBannerData(@Body RequestBody body);

    /**
     *  获取检验任务数据数据
     * @return
     */
    @POST(Api.TASK_LIST)
    Observable<TaskData> getTaskData(@Body RequestBody body);
    /**
     * 获取检验记录项目
     */
    @POST(Api.PROJECT_DATA)
    Observable<FormData> getFormData(@Body RequestBody body);
    /**
     * 获取检验人员列表
     */
    @POST(Api.CHECK_PERSON)
    Observable<PersonData> getPersonData(@Body RequestBody body);
    /**
     * 保存检验数据
     */
    @POST(Api.SAVE_TASK)
    Observable<SaveResult> saveTaskData(@Body RequestBody body);
}
