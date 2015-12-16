package org.expresshelp.expresshelp.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by rajib on 12/12/15.
 */
public class ExpressHelpApp extends Application {


    public static String _rescueURL = "http://bluestartkitservice.mybluemix.net/api/service";
    public static String _reliefURL = "http://bluestartkitservice.mybluemix.net/api/service";
    public static String _confirmVolunteerURL = "http://bluestartkitservice.mybluemix.net/api/service";
    public static String _materialHelpURL = "http://bluestartkitservice.mybluemix.net/api/service";

    private static String _emailPhone = null;


    public static String _locationData = "location";

    private static final String CLASS_NAME = ExpressHelpApp.class.getSimpleName();

    public static String _aboutPropertiesFile    = "about.properties";
    public static String _responsePropertiesFile = "response.properties";
    public static String _supportPropertiesFile  = "support.properties";


    public ExpressHelpApp() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {
                Log.d(CLASS_NAME,
                        "Activity created: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(CLASS_NAME,
                        "Activity started: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(CLASS_NAME,
                        "Activity resumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity,
                                                    Bundle outState) {
                Log.d(CLASS_NAME,
                        "Activity saved instance state: "
                                + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(CLASS_NAME,
                        "Activity paused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(CLASS_NAME,
                        "Activity stopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(CLASS_NAME,
                        "Activity destroyed: " + activity.getLocalClassName());
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    public static void setEmailPhone( String emailPhone ){
        _emailPhone = emailPhone;
    }

    public static String getEmailPhone(){
        return _emailPhone;
    }

}
