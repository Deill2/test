package com.example.zz.dynamicfragment;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollect {
    private static   List<Activity>  list    =   new ArrayList<>();
    public static void add(Activity activity){
        list.add(activity);
    }
    public static void remove(Activity  activity){
        list.remove(activity);
    }
    public static void Finsh(){
        for (Activity   activity:   list){
            if (activity    !=  null){
                if (!activity.isFinishing()){
                    activity.finish();
                }
            }
        }
    }
}
