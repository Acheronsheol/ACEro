package moe.shigure.acero.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wang on 2020/9/28
 **/

public class ActivityUtils {

    private static List<Activity> activityList = new LinkedList<>();

    public static List<Activity> getActivityList(){
        return activityList;
    }

    public static Activity getLastActivity(){
        return activityList.get(activityList.size() - 1);
    }

    /*
    * 完全退出
    * 一般用于“退出程序”功能
    * */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

}
