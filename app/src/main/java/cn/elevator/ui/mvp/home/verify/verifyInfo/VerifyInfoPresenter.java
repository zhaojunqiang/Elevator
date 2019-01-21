package cn.elevator.ui.mvp.home.verify.verifyInfo;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.bean.TaskListData_;
import cn.elevator.ui.mvp.home.check.chekinfo.CheckInfoContact;
import cn.elevator.ui.mvp.home.check.chekinfo.CheckInfoModle;
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
public class VerifyInfoPresenter implements VerifyInfoContact.Presenter {
    private VerifyInfoContact.Modle mModle;
    private VerifyInfoContact.View mView;
    private CompositeDisposable compositeDisposable;

    public VerifyInfoPresenter(VerifyInfoContact.View view) {
        this.mView = view;
        this.mModle = new VerifyInfoModle();
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
    public void getTaskData(String userId, String dataFields) {
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

    @Override
    public void saveCheckData(String json) {
        Disposable disposableTask = mModle.getHttpSaveData(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SaveResult>() {
                    @Override
                    public void onNext(SaveResult saveResult) {
                        if (mView.isActive()){
                            mView.showSaveResult(saveResult);
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
