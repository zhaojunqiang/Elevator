package cn.elevator.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

import cn.elevator.utils.SharedPrefUtils;

/**
 * Created by DamonJiang on 2018/8/13 0013.
 */
public class App extends Application {
    private static Context context;
    private static List<Activity> activities = new LinkedList<>();
    private static Activity activeActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        activities.clear();
        // 初始化 sp 工具类
        SharedPrefUtils.init(this);
    }

    /**
     * 获取全局的 context
     * @return
     */
    public static Context getContext(){
        return context;
    }
    public static Activity getActiveActivity() {
        return activeActivity;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
}
