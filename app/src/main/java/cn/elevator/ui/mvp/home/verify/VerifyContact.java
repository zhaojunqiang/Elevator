package cn.elevator.ui.mvp.home.verify;

import java.util.List;
import java.util.Map;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录页面关系类
 */
public class VerifyContact {

    interface Modle {
        Observable<TaskData> getHttpTaskData(String userId, String dataFields);
        Observable<TaskData> getTaskDataList(Map<String, String> params);
    }

    interface Presenter extends BasePresenter {

        void getTaskData(String userId, String dataFields);
        void getTaskList(Map<String, String> params);
        void getTaskListMore(Map<String, String> params);
    }

    interface View extends BaseView{

        boolean isActive();

        void showTaskData(TaskData taskData);
        void showMoreTaskData(TaskData taskData);

        void noData();

        void noMoreData();

        void hideLoadingMore();

        void showLoading();
        void hideLoading();
    }

}
