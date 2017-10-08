package com.qihuan.themeloader;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

/**
 * ColorfulActivityCallbacks
 * Created by Qi on 2017/10/8.
 */

public class ColorfulActivityCallbacks implements Application.ActivityLifecycleCallbacks {

    private String themeString;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.themeString = Colorful.getThemeString();
        activity.setTheme(Colorful.getThemeDelegate().getStyleResBase());
        activity.getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResPrimary(), true);
        activity.getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResAccent(), true);
        if (Build.VERSION.SDK_INT >= 21) {
            if (Colorful.getThemeDelegate().isTranslucent()) {
                activity.getWindow().addFlags(67108864);
            }
            ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(null, null, activity.getResources().getColor(Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
            activity.setTaskDescription(tDesc);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (!Colorful.getThemeString().equals(this.themeString)) {
            activity.recreate();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
