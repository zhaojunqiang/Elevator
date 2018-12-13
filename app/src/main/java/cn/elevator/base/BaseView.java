package cn.elevator.base;

import android.view.View;

/**
 * mvp 模式 view层的基类，所有的 view 都必须实现
 * Created by DamonJiang on 2018/8/13 0013.
 */
public interface BaseView{
    /**
     * 初始化控件
     * @param view
     */
    void initViews(View view);

}