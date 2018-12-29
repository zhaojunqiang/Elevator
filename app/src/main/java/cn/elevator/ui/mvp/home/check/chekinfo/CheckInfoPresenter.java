package cn.elevator.ui.mvp.home.check.chekinfo;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.PersonData;
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
public class CheckInfoPresenter implements CheckInfoContact.Presenter {
    private CheckInfoContact.Modle mModle;
    private CheckInfoContact.View mView;
    private CompositeDisposable compositeDisposable;

    public CheckInfoPresenter(CheckInfoContact.View view) {
        this.mView = view;
        this.mModle = new CheckInfoModle();
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
    public void getTaskById(long Id) {
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        Query<TaskListData> query = null;
        query = listDataBox.query().equal(TaskListData_.__ID_PROPERTY,Id).build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
                mView.showTaskData(data.get(0));
            }
        });
    }

    @Override
    public void getCheckPersonList(Map<String, String> params) {
        Disposable disposableTask = mModle.getHttpPersonData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PersonData>() {
                    @Override
                    public void onNext(PersonData personData) {
                        if (mView.isActive()){
                            mView.showCheckPersonData(personData);
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
    public void getVerifyList(Map<String, String> params) {
        Disposable disposableTask = mModle.getHttpPersonData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PersonData>() {
                    @Override
                    public void onNext(PersonData personData) {
                        if (mView.isActive()){
                            mView.showVerifyData(personData);
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