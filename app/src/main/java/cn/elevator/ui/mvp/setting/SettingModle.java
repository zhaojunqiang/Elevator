package cn.elevator.ui.mvp.setting;

import org.json.JSONObject;

import java.util.Map;

import cn.elevator.bean.VersionInfo;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置获取网络数据
 */
public class SettingModle implements SettingContact.Modle {
    @Override
    public Observable<VersionInfo> getVersionInfo(Map<String, String> params) {
        JSONObject result = new JSONObject(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getVersionInfo(body);
    }
}
