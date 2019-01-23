package cn.elevator.ui.mvp.home.check.form;

import java.util.Map;

import cn.elevator.app.App;
import cn.elevator.bean.FormData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.ui.mvp.home.verify.VerifyContact;
import cn.elevator.ui.mvp.home.verify.VerifyModle;
import io.objectbox.Box;
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
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
