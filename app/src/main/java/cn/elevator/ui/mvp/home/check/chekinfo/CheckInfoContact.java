package cn.elevator.ui.mvp.home.check.chekinfo;

import java.util.List;
import java.util.Map;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.PersonData;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录页面关系类
 */
public class CheckInfoContact {

    interface Modle {
        Observable<PersonData> getHttpPersonData(Map<String,String> params);
        Observable<SaveResult> getHttpSaveData(String json);
    }

    interface Presenter extends BasePresenter {
        void getTaskById(long Id);
        void getCheckPersonList(Map<String,String> params);
        void getVerifyList(Map<String,String> params);
        void saveCheckData(String json);
    }

    interface View extends BaseView{

        boolean isActive();

        void showTaskData(TaskListData listData);

        void showCheckPersonData(PersonData personData);

        void showVerifyData(PersonData personData);

        void showSaveResult(SaveResult saveResult);
    }

}
