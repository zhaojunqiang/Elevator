package cn.elevator.ui.mvp.home.check;

import cn.elevator.app.App;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.ui.mvp.home.HomeContact;
import cn.elevator.ui.mvp.home.HomeModle;
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
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
