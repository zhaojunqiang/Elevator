package cn.elevator.ui.mvp.setting;

import java.util.Map;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.EquipmentData;
import cn.elevator.bean.VersionInfo;
import io.reactivex.Observable;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置的纽带类
 */
public class SettingContact {
    interface Modle {
        Observable<VersionInfo> getVersionInfo(Map<String,String> params);

    }

    interface Presenter extends BasePresenter {
        void getVersionInfo(Map<String,String> params);
    }

    interface View extends BaseView {
        boolean isActive();
        void showVersionInfo(VersionInfo versionInfo);
    }
}
