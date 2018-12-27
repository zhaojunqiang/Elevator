package cn.elevator.ui.mvp.home.check;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.bean.TaskListData_;
import cn.elevator.ui.mvp.home.HomeContact;
import cn.elevator.ui.mvp.home.HomeModle;
import io.objectbox.Box;
import io.objectbox.Property;
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
public class CheckPresenter implements CheckContact.Presenter {
    private CheckContact.Modle mModle;
    private CheckContact.View mView;
    private CompositeDisposable compositeDisposable;

    public CheckPresenter(CheckContact.View view) {
        this.mView = view;
        this.mModle = new CheckModle();
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
                            mView.showTaskData(taskData);
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
                        Box<TaskListData> listDataBox = App.getInstance().getBoxStore().boxFor(TaskListData.class);
                        listDataBox.put(taskData.getData());
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
    public void getTaskFromDataBase() {
        /**
         *Query<Task> query = taskBox.query().equal(Task_.complete, false).build();
         * query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> updateUi(data));
         */
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        Query<TaskListData> query = listDataBox.query().build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
            mView.showTaskList(data);
        }
        });
//        if (mView.isActive()){
//            mView.showTaskList(listDataBox.getAll());
//        }
    }

    @Override
    public void getTaskByParam(String param, int type) {
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        Query<TaskListData> query = null;
        if(type==1){//年
            query = listDataBox.query().equal(TaskListData_.CheckYear,Integer.valueOf(param)).build();
        }else if(type==2){//检验类别
            query = listDataBox.query().equal(TaskListData_.CheckType,Integer.valueOf(param)).build();
        }else if(type==3){//使用单位
            query = listDataBox.query().equal(TaskListData_.UseOrganize,param).build();
        }else if(type==4){//编制状态
            query = listDataBox.query().equal(TaskListData_.APPRecordState,Integer.valueOf(param)).build();
        }
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
                mView.showSelectList(data);
            }
        });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
