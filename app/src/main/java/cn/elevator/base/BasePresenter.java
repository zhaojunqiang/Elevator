package cn.elevator.base;

/**
 * author: DamonJiang
 * date:  2018/8/13 0013
 * description: mvp 模式的 presenter 基类
 */
public interface BasePresenter {
    /**
     * 需要初始化的地方调用
     */
    void subscribe();

    /**
     * 释放资源/销毁时调用
     */
    void unSubscribe();
}
