package cn.elevator.ui.mvp.home.check;

import java.util.List;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.LoginData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录页面关系类
 */
public class CheckContact {

    interface Modle {
        Observable<TaskData> getHttpTaskData(String userId, String dataFields);
    }

    interface Presenter extends BasePresenter {

        void getTaskData(String userId,String dataFields);
        void getTaskFromDataBase();
        void getTaskByParam(String param,int type);
    }

    interface View extends BaseView{

        boolean isActive();

        void showTaskData(TaskData taskData);

        void showTaskList(List<TaskListData> taskListData);
        void showSelectList(List<TaskListData> taskListData);

        void showNetWorkError();
    }

}
