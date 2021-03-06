package cn.elevator.ui.mvp.setting;

import java.util.Map;

import cn.elevator.bean.EquipmentData;
import cn.elevator.bean.VersionInfo;
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
public class SettingPresenter implements SettingContact.Presenter {
    private SettingContact.Modle mModle;
    private SettingContact.View mView;
    private CompositeDisposable compositeDisposable;
    public SettingPresenter(SettingContact.View view) {
        this.mView = view;
        this.mModle = new SettingModle();
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
    public void getVersionInfo(Map<String, String> params) {
        Disposable disposableTask = mModle.getVersionInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<VersionInfo>() {
                    @Override
                    public void onNext(VersionInfo versionInfo) {
                        if (mView.isActive()){
                            mView.showVersionInfo(versionInfo);
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
