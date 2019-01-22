package cn.elevator.ui.mvp.home;

import java.util.List;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.TaskData;
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
    }

    interface Presenter extends BasePresenter {
        void getBannersData();

        void getTaskData(String userId,String dataFields);
    }

    interface View extends BaseView{

        boolean isActive();

        void initBanners();

        void initTaskCount();

        void showBanners(BannerData bannerData);

        void showTaskCount(TaskData taskData);

        void showNetWorkError();
    }
}
