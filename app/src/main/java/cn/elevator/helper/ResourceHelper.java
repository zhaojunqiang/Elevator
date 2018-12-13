package cn.elevator.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import cn.elevator.app.App;

/**
 * Created by DamonJiang on 2018/8/13 0013.
 */
public class ResourceHelper {

    private static final ResourceHelper ourInstance = new ResourceHelper();


    private ResourceHelper() {
    }

    public static int getColor(@ColorRes int colorId) {
        return getContext().getResources().getColor(colorId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return getContext().getResources().getDrawable(drawableId);
    }

    private static Context getContext() {
        return App.getContext();
    }
}
