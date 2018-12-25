package cn.elevator.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

import cn.elevator.BuildConfig;
import cn.elevator.bean.MyObjectBox;
import cn.elevator.utils.SharedPrefUtils;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by DamonJiang on 2018/8/13 0013.
 */
public class App extends Application {
    private static Context context;
    private static List<Activity> activities = new LinkedList<>();
    private static Activity activeActivity;
    //ObjectBox 数据库
    private static BoxStore mBoxStore;
    private static App _instance;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        _instance = this;
        activities.clear();
        // 初始化 sp 工具类
        SharedPrefUtils.init(this);
        initDataBase();
    }
    public static App getInstance() {
        return _instance;
    }
    private void initDataBase() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(mBoxStore).start(this);//打开数据库调试信息
        }
    }
    public BoxStore getBoxStore() {
        return mBoxStore;
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
