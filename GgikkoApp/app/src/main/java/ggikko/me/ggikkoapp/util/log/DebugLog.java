package ggikko.me.ggikkoapp.util.log;

import android.util.Log;

import ggikko.me.ggikkoapp.GgikkoApplication;

/**
 * Created by ggikko on 16. 8. 19..
 */
public class DebugLog {

    static final String TAG = "ggikko";

    /**
     * ERROR LOGGING
     * @param message
     */
    public final void e(String message) {
        if (GgikkoApplication.DEBUG) Log.e(TAG, buildLogMsg(message));
    }

    /**
     * DEBUG MODE
     * @param message
     */
    public final void d(String message) {
        if (GgikkoApplication.DEBUG)Log.d(TAG, buildLogMsg(message));
    }

    /**
     * LOG BUILDER
     * @param message
     * @return
     */
    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append(" : ");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }
}
