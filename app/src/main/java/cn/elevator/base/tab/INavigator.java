package cn.elevator.base.tab;

import android.view.View;

/**
 * @anthor DamonJiang
 * @date 2018/8/15
 * @describe describe
 *  导航器
 **/
public interface INavigator {
    /**
     * 将fragment列表和tab关联
     */
    void attachToTab();

    /**
     * 当前布局
     * @return
     */
    View getContentView();

    /**
     * 销毁
     */
    void destroy();

    /**
     * tab切换监听
     */
    interface OnNavigatorChangedListener {
        void onPageSelected(int position);
    }
}
