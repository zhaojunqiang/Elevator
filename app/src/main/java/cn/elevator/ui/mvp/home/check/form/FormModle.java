package cn.elevator.ui.mvp.home.check.form;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.elevator.bean.FormData;
import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.http.ApiService;
import cn.elevator.http.HttpClient;
import cn.elevator.ui.mvp.home.verify.verifyInfo.VerifyInfoContact;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页获取网络数据
 */
public class FormModle implements FormContact.Modle{


    @Override
    public Observable<FormData> getHttpFormData(String ID) {
        JSONObject result = new JSONObject();
        try {
            result.put("CheckRecordID", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
        return HttpClient.getInstance()
                .create(ApiService.class)
                .getFormData(body);
    }
}
