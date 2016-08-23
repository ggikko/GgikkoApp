package ggikko.me.ggikkoapp.util.log;

import android.util.Log;

import ggikko.me.ggikkoapp.GgikkoApplication;

/**
 * Created by ggikko on 16. 8. 9..
 */
public class NetworkLog {

    public static final boolean SHOULD_LOG = true;//BuildConfig.DEBUG;

    /**
     * Network Logging + Level : Header, Body...기타 등등
     * @param message
     */
    public static void d(String message) {
        if (SHOULD_LOG) {
            Log.d(GgikkoApplication.class.getPackage().getName(), message);
        }
    }

    /**
     * Network error Logging
     * @param error
     */
    public static void e(Throwable error) {
        if (SHOULD_LOG) {
            Log.e(GgikkoApplication.class.getPackage().getName(), "Error", error);
        }
    }
}
