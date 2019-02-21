package cn.elevator.ui.mvp.home;

import java.util.List;

import cn.elevator.app.App;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.FormData;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
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
                        mView.showBanners(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView.isActive()){
                            mView.showNetWorkError();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void getTaskData(String userId,String dataFields) {
        Disposable disposableTask = mModle.getHttpTaskData(userId,dataFields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TaskData>() {
                    @Override
                    public void onNext(TaskData taskData) {
                        if (mView.isActive()){
                            //mView.showTaskCount(taskData);
                            Box<TaskListData> listDataBox = App.getInstance().getBoxStore().boxFor(TaskListData.class);
                            listDataBox.put(taskData.getData());
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

    @Override
    public void getTaskCount() {
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        Query<TaskListData> query = listDataBox.query().build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
                mView.showTaskCount(data);
            }
        });
    }

    @Override
    public void getFormData(String userId, String checkId) {
        Disposable disposableTask = mModle.getHttpFormData(userId,checkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FormData>() {
                    @Override
                    public void onNext(FormData formData) {
                        if (mView.isActive()){
                            if(formData.getData()!=null && formData.getData().size()>0){
                                Box<FormListData> listDataBox = App.getInstance().getBoxStore().boxFor(FormListData.class);
                                listDataBox.put(formData.getData());
                            }
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

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
