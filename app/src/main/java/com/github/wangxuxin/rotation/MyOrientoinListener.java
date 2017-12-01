package com.github.wangxuxin.rotation;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.OrientationEventListener;

import static android.content.ContentValues.TAG;

public class MyOrientoinListener extends OrientationEventListener {
    boolean isDebug=false;
    int changeType = 1;//横屏的检测方式
    Context context;
    public MyOrientoinListener(Context context) {
        super(context);
        this.context=context;
    }

    public MyOrientoinListener(Context context, int rate) {
        super(context, rate);
        this.context=context;
    }

    @Override
    public void onOrientationChanged(int orientation) {
        logcat(TAG, "orention" + orientation);
        int screenOrientation = context.getResources().getConfiguration().orientation;
        if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {//设置竖屏
            if(changeType==0){
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT && orientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                    logcat(TAG, "设置竖屏");
                    Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, 0);
                }
            }else {
                logcat(TAG, "设置竖屏");
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, 0);
            }
        } else if (orientation > 225 && orientation < 315) { //设置横屏
            logcat(TAG, "设置横屏");
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, 1);
            }
        } else if (orientation > 45 && orientation < 135) {// 设置反向横屏
            logcat(TAG, "反向横屏");
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, 3);
            }
        } else if (orientation > 135 && orientation < 225) {
            logcat(TAG, "反向竖屏");
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, 2);
            }
        }
    }

    void logcat(String tag,String str){
        if(isDebug){
            Log.d(tag,str);
        }
    }
//    代码参考：
//    作者：吾若成疯
//    链接：http://www.jianshu.com/p/06713cbbd5fe
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}

