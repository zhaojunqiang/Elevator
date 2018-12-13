package cn.elevator.ui.mvp.account;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.LoginData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录页面关系类
 */
public class LoginContact {

    interface Modle {
        Observable<LoginData> getHttpLoginData(String username, String password, String deviceId);
    }

    interface View extends BaseView {

        boolean isActive();

        void onLoginError(String errorMsg);

        void onLoginSuccess(LoginData loginData);

        void showNetworkError();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password, String deviceId);
    }

}
