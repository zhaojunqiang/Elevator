package cn.elevator.ui.mvp.home.check.form;

import java.util.List;
import java.util.Map;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.FormData;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveFormResult;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录页面关系类
 */
public class FormContact {

    interface Modle {
        Observable<FormData> getHttpFormData(String userId,String checkId);
        Observable<SaveResult> getHttpSaveTaskData(String json);
        Observable<SaveFormResult> getHttpSaveFormData(String json);
    }

    interface Presenter extends BasePresenter {
        void getFormData(String userId,String checkId);
        void getFormDataDB(String checkId);
        void getTaskById(long Id);
        void saveTaskData(String json);
        void saveFormData(String json);
    }

    interface View extends BaseView{

        boolean isActive();

        void showFormData(FormData formData);

        void noData();

        void showFormDataDB(List<FormListData> formListData);

        void showTaskData(TaskListData listData);

        void showSaveTaskResult(SaveResult saveResult);

        void showSaveFormResult(SaveFormResult saveFormResult);
    }

}
