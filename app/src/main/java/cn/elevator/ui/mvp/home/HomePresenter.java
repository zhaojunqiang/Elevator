package cn.elevator.ui.mvp.home;

import cn.elevator.bean.BannerData;
import cn.elevator.bean.TaskData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页 presenter 层
 */
public class HomePresenter implements HomeContact.Presenter {
    private HomeContact.Modle mModle;
    private HomeContact.View mView;
    private CompositeDisposable compositeDisposable;

    public HomePresenter(HomeContact.View view) {
        this.mView = view;
        this.mModle = new HomeModle();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBannersData() {
        Disposable disposable = mModle.getHttpBannersData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BannerData>() {
                    @Override
                    public void onNext(BannerData value) {
                        if(!mView.isActive()) return;
                        // 1.此处判断请求结果码是否为200
                        // 2.确认成功后，调用view层的showBanner（）；
                        mView.showBanners();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView.isActive()){
                            mView.showNetWorkError();
                            mView.showBanners();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void getTaskData(String userId) {
        Disposable disposableTask = mModle.getHttpTaskData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TaskData>() {
                    @Override
                    public void onNext(TaskData value) {

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

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
