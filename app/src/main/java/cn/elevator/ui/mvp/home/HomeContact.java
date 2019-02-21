package cn.elevator.ui.mvp.home;

import java.util.List;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.FormData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description:
 */
public class HomeContact {
    interface Modle {
        Observable<BannerData> getHttpBannersData();

        Observable<TaskData> getHttpTaskData(String userId, String dataFields);

        Observable<FormData> getHttpFormData(String userId, String checkId);
    }

    interface Presenter extends BasePresenter {
        void getBannersData();

        void getTaskData(String userId,String dataFields);

        void getTaskCount();

        void getFormData(String userId,String checkId);
    }

    interface View extends BaseView{

        boolean isActive();

        void initBanners();

        void initTaskData();

        void initTaskCount();

        void initFormData();

        void showBanners(BannerData bannerData);

        void showTaskCount(List<TaskListData> dataList);

        void showNetWorkError();
    }
}
