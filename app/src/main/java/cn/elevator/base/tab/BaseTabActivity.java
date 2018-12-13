package cn.elevator.base.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import cn.elevator.base.BaseActivity;

/**
 * author: DamonJiang
 * date:   2018/8/15 0015
 * description: 导航基类 activity
 */
public abstract class BaseTabActivity extends BaseActivity {

    /**
     * 生成导航标题列表
     *
     * @return
     */
    protected abstract String[] generateTitles();

    protected int[] generateIcons() {

        return new int[0];
    }

    /**
     * 生成fragment列表
     *
     * @return
     */
    protected abstract Fragment[] generateFragments();

    @Override
    public void initViews(Bundle savedInstanceState) {
        INavigator navigator = generateNavigator();
        if (navigator == null) {
            return;
        }

        navigator.attachToTab();
        View content = navigator.getContentView();
        if (content == null) {
            return;
        }
        installContentView(content);
    }

    /**
     * 生成导航器
     *
     * @return
     */
    protected abstract INavigator generateNavigator();

    @Override
    public int getLayoutId() {
        return 0;
    }
}
