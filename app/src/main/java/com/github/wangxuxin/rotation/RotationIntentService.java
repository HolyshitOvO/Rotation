package com.github.wangxuxin.rotation;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RotationIntentService extends IntentService {
    static MyOrientoinListener mol=null;

    private static final String SERVICE_NAME = "com.github.wangxuxin.rotation.RotationIntentService";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.github.wangxuxin.rotation.action.FOO";
    private static final String ACTION_BAZ = "com.github.wangxuxin.rotation.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.github.wangxuxin.rotation.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.github.wangxuxin.rotation.extra.PARAM2";

    public RotationIntentService() {
        super("RotationIntentService");
    }

    public static boolean isServiceRunning(Context context) {
        if (!TextUtils.isEmpty(SERVICE_NAME) && context != null) {
            ActivityManager activityManager
                    = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfoList
                    = (ArrayList<ActivityManager.RunningServiceInfo>) activityManager.getRunningServices(100);
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServiceInfoList) {
                if (SERVICE_NAME.equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    protected static void stopService(Context context) {
        Log.d("wxxDebug","stopservice");
        if (mol != null) {
            mol.disable();
            mol=null;
            Log.d("wxxDebug","disablemol");
        }
        Intent intent = new Intent(context, RotationIntentService.class);
        context.stopService(intent);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startRotation(Context context) {
        if (isServiceRunning(context)||mol!=null) {
            throw new UnsupportedOperationException("service already started");
        }
        Intent intent = new Intent(context, RotationIntentService.class);
        intent.setAction(ACTION_FOO);
//        intent.putExtra(EXTRA_PARAM1, param1);
//        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, RotationIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleStartAction();
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleStartAction() {
        // TODO: Handle action Foo
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mol = new MyOrientoinListener(getApplicationContext());
//                mol.enable();
//            }
//        });
//        thread.start();

        mol = new MyOrientoinListener(getApplicationContext());
        mol.enable();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
