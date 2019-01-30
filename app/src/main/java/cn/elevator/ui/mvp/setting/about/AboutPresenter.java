package cn.elevator.ui.mvp.setting.about;

import java.util.Map;

import cn.elevator.bean.AboutInfo;
import cn.elevator.bean.VersionInfo;
import cn.elevator.ui.mvp.setting.SettingContact;
import cn.elevator.ui.mvp.setting.SettingModle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置的 presenter 层
 */
public class AboutPresenter implements AboutContact.Presenter {
    private AboutContact.Modle mModle;
    private AboutContact.View mView;
    private CompositeDisposable compositeDisposable;
    public AboutPresenter(AboutContact.View view) {
        this.mView = view;
        this.mModle = new AboutModle();
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
    @Override
    public void getAboutInfo(Map<String, String> params) {
        Disposable disposableTask = mModle.getAboutInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AboutInfo>() {
                    @Override
                    public void onNext(AboutInfo aboutInfo) {
                        if (mView.isActive()){
                            mView.showAboutInfo(aboutInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableTask);
    }
}
