package cn.elevator.ui.mvp.setting.about;

import org.json.JSONObject;

import java.util.Map;

import cn.elevator.bean.AboutInfo;
import cn.elevator.bean.VersionInfo;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import cn.elevator.ui.mvp.setting.SettingContact;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置获取网络数据
 */
public class AboutModle implements AboutContact.Modle {

    @Override
    public Observable<AboutInfo> getAboutInfo(Map<String, String> params) {
        JSONObject result = new JSONObject(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getAboutInfo(body);
    }
}
