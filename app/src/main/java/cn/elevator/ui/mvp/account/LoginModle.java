package cn.elevator.ui.mvp.account;

import org.json.JSONException;
import org.json.JSONObject;
import cn.elevator.bean.LoginData;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录 取网络数据
 */
public class LoginModle implements LoginContact.Modle {

    @Override
    public Observable<LoginData> getHttpLoginData(String username,String password,String deviceId) {
        JSONObject result = new JSONObject();
        try {
            result.put("LoginName", username);
            result.put("LoginPwd", password);
            result.put("LoginKey", deviceId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());

        return HttpClient.getInstance()
                .create(ApiService.class)
                .login(body);
    }
}
