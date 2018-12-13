package cn.elevator.ui.mvp.account;

import cn.elevator.bean.LoginData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录的 presenter 层
 */
public class LoginPresenter implements LoginContact.Presenter {
    private LoginContact.View loginView;
    private LoginContact.Modle loginModle;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(LoginContact.View loginView) {
        this.loginView = loginView;
        this.loginModle = new LoginModle();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(String username, String password, String deviceId) {
        Disposable disposable = loginModle.getHttpLoginData(username,password,deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<LoginData>() {
                    @Override
                    public void onNext(LoginData value) {
                        if (!loginView.isActive()) return;

                        if (Integer.parseInt(value.getCode()) == 200){
                            loginView.onLoginSuccess(value);
                        }else {
                            loginView.onLoginError(value.getData().getLoginState());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(loginView.isActive()){
                            // 网络错误
                            loginView.showNetworkError();
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
