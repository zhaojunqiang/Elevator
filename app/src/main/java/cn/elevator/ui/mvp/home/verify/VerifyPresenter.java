package cn.elevator.ui.mvp.home.verify;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.bean.TaskListData_;
import cn.elevator.ui.mvp.home.check.CheckContact;
import cn.elevator.ui.mvp.home.check.CheckModle;
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
public class VerifyPresenter implements VerifyContact.Presenter {
    private VerifyContact.Modle mModle;
    private VerifyContact.View mView;
    private CompositeDisposable compositeDisposable;

    public VerifyPresenter(VerifyContact.View view) {
        this.mView = view;
        this.mModle = new VerifyModle();
        compositeDisposable = new CompositeDisposable();
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
                            if(taskData.getData()!=null && taskData.getData().size()>0){
                                mView.showTaskData(taskData);
                            }else {
                                mView.noData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.isActive()){
                            mView.noData();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableTask);
    }
    @Override
    public void getTaskList(Map<String, String> params) {
        if (mView.isActive()){
            mView.showLoading();
        }
        Disposable disposableTask = mModle.getTaskDataList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TaskData>() {
                    @Override
                    public void onNext(TaskData taskData) {
                        if (mView.isActive()){
                            mView.showTaskData(taskData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.isActive()){
                            mView.noData();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView.isActive()){
                            mView.hideLoading();
                        }
                    }
                });
        compositeDisposable.add(disposableTask);
    }
    @Override
    public void getTaskListMore(Map<String, String> params) {
        Disposable disposableTask = mModle.getTaskDataList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TaskData>() {
                    @Override
                    public void onNext(TaskData taskData) {
                        if (mView.isActive()){
                            mView.showMoreTaskData(taskData);
                        }
                        Box<TaskListData> listDataBox = App.getInstance().getBoxStore().boxFor(TaskListData.class);
                        listDataBox.put(taskData.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.isActive()){
                            mView.noMoreData();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView.isActive()){
                            mView.hideLoadingMore();
                        }
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
