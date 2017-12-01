package com.github.wangxuxin.rotation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenActionReceiver extends BroadcastReceiver {
    private String TAG = "ScreenActionReceiver";
    private boolean isRegisterReceiver = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            //Logcat.d(TAG, "屏幕解锁广播...");
        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            //Logcat.d(TAG, "屏幕加锁广播...");
        }
    }

    public void registerScreenActionReceiver(Context mContext) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true;

            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            //Logcat.d(TAG, "注册屏幕解锁、加锁广播接收者...");
            mContext.registerReceiver(ScreenActionReceiver.this, filter);
        }
    }

    public void unRegisterScreenActionReceiver(Context mContext) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false;
            //Logcat.d(TAG, "注销屏幕解锁、加锁广播接收者...");
            mContext.unregisterReceiver(ScreenActionReceiver.this);
        }
    }
    //代码参考：
    //http://blog.csdn.net/a443453087/article/details/8198859
}
