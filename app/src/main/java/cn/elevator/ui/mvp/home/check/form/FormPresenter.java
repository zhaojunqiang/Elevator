package cn.elevator.ui.mvp.home.check.form;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.FormData;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.FormListData_;
import cn.elevator.bean.SaveFormResult;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.bean.TaskListData_;
import cn.elevator.ui.mvp.home.verify.VerifyContact;
import cn.elevator.ui.mvp.home.verify.VerifyModle;
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
public class FormPresenter implements FormContact.Presenter {
    private FormContact.Modle mModle;
    private FormContact.View mView;
    private CompositeDisposable compositeDisposable;

    public FormPresenter(FormContact.View view) {
        this.mView = view;
        this.mModle = new FormModle();
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void getFormData(String userId,String checkId) {
        Disposable disposableTask = mModle.getHttpFormData(userId,checkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FormData>() {
                    @Override
                    public void onNext(FormData formData) {
                        if (mView.isActive()){
                            if(formData.getData()!=null && formData.getData().size()>0){
                                mView.showFormData(formData);
                                Box<FormListData> listDataBox = App.getInstance().getBoxStore().boxFor(FormListData.class);
                                listDataBox.put(formData.getData());
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
    public void getFormDataDB(String checkId) {
        Box<FormListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(FormListData.class);
        Query<FormListData> query = null;
        query = listDataBox.query().equal(FormListData_.CheckRecordID,checkId).order(FormListData_.ord).build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
                mView.showFormDataDB(data);
            }
        });
    }

    @Override
    public void getTaskById(long Id) {
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        Query<TaskListData> query = null;
        query = listDataBox.query().equal(TaskListData_.__ID_PROPERTY,Id).build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            if (mView.isActive()){
                if(data!=null && data.size()>0){
                    mView.showTaskData(data.get(0));
                }
            }
        });
    }

    @Override
    public void saveTaskData(String json) {
        Disposable disposableTask = mModle.getHttpSaveTaskData(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SaveResult>() {
                    @Override
                    public void onNext(SaveResult saveResult) {
                        if (mView.isActive()){
                            mView.showSaveTaskResult(saveResult);
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
    public void saveFormData(String json) {
        Disposable disposableTask = mModle.getHttpSaveFormData(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SaveFormResult>() {
                    @Override
                    public void onNext(SaveFormResult saveResult) {
                        if (mView.isActive()){
                            mView.showSaveFormResult(saveResult);
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
