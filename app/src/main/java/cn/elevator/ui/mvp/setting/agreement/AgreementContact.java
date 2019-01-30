package cn.elevator.ui.mvp.setting.agreement;

import java.util.Map;

import cn.elevator.base.BasePresenter;
import cn.elevator.base.BaseView;
import cn.elevator.bean.AboutInfo;
import io.reactivex.Observable;

/**
 * Created by Yangzb on 2019/1/30 11:06
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class AgreementContact {
    interface Modle {
        Observable<AboutInfo> getAboutInfo(Map<String, String> params);

    }

    interface Presenter extends BasePresenter {
        void getAboutInfo(Map<String, String> params);
    }

    interface View extends BaseView {
        boolean isActive();
        void showAboutInfo(AboutInfo aboutInfo);
    }
}
