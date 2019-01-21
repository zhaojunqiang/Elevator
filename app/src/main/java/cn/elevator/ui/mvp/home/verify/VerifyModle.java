package cn.elevator.ui.mvp.home.verify;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.elevator.bean.TaskData;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import cn.elevator.ui.mvp.home.check.CheckContact;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页获取网络数据
 */
public class VerifyModle implements VerifyContact.Modle{
    /**
     * 获取广告轮播数据
     * @return
     */

    /**
     * 获取检验任务列表数据（计算待检验任务个数，显示红点）
     * @return
     */
    @Override
    public Observable<TaskData> getHttpTaskData(String userId, String dataFields) {
        JSONObject result = new JSONObject();
        try {
            result.put("UserId", userId);
            result.put("DataFields",dataFields);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getTaskData(body);
    }
    /**
     * 获取检验任务列表数据 分页（计算待检验任务个数，显示红点）
     * @return
     */
    @Override
    public Observable<TaskData> getTaskDataList(Map<String,String> params) {
        JSONObject result = new JSONObject(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getTaskData(body);
    }
}
