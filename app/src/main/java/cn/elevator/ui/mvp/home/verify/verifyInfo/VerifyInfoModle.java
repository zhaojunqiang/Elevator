package cn.elevator.ui.mvp.home.verify.verifyInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import cn.elevator.ui.mvp.home.check.chekinfo.CheckInfoContact;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页获取网络数据
 */
public class VerifyInfoModle implements VerifyInfoContact.Modle{

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

    @Override
    public Observable<PersonData> getHttpPersonData(Map<String, String> params) {
        JSONObject result = new JSONObject(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getPersonData(body);
    }

    @Override
    public Observable<SaveResult> getHttpSaveData(String json) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        return HttpClient.getInstance()
                .create(ApiService.class)
                .saveTaskData(body);
    }
}
