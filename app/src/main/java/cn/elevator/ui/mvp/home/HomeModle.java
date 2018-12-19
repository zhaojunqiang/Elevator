package cn.elevator.ui.mvp.home;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.elevator.bean.BannerData;
import cn.elevator.bean.TaskData;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页获取网络数据
 */
public class HomeModle implements HomeContact.Modle{
    /**
     * 获取广告轮播数据
     * @return
     */
    @Override
    public Observable<BannerData> getHttpBannersData() {
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getBannerData();
    }
    /**
     * 获取检验任务列表数据（计算待检验任务个数，显示红点）
     * @return
     */
    @Override
    public Observable<List<TaskData>> getHttpTaskData(String userId, String dataFields) {
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
}
