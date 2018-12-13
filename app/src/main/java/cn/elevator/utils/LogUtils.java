package cn.elevator.utils;

import android.util.Log;

/**
 * 日志打印管理工具
 * Created by DamonJiang on 2018/6/10 0010.
 */

public class LogUtils {

    // 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean isShowLog = true;
    // 默认 TAG
    private static final String TAG = "DamonJiang ==>";

    private LogUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (isShowLog)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (isShowLog)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isShowLog)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (isShowLog)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isShowLog)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isShowLog)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isShowLog)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isShowLog)
            Log.i(tag, msg);
    }
}
