package ggikko.me.ggikkoapp.di.base.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.injector.ActivityInjector;
import ggikko.me.ggikkoapp.ui.img.ImageSearchActivity;
import lombok.Getter;

/**
 * activity for injection, appcompat Activity
 */
public abstract class InjectionActivity extends AppCompatActivity {

    /** static constant */
    public static final String PARENT_EXTRA = "PARENT_EXTRA";

    /** connectivity connectivityManager */
    private ConnectivityManager connectivityManager;
    private boolean isNetworkOn = false;
    private boolean isInMainActivity = true;

    /** binder */
    private Unbinder baseUnbider;
    protected Unbinder unbinder;

    /** dialog */
    AlertDialog loadingDialog;
    boolean stopped = false;
    boolean dialogVisible;

    @Getter
    private ActivityInjector activityInjector;

    /**
     * interface for subactivity
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();
    protected abstract void onCreate();

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //dagger injection
        inject();
        super.onCreate(savedInstanceState);
        //네트워크 체킹 register 등록
        registerNetworkCheckingReceiver();
        //set layout param
        setContentView(getLayoutRes());
        //call onCreate subActivity
        onCreate();
    }

    /**
     * network receiver registration
     */
    private void registerNetworkCheckingReceiver() {
        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    /**
     * life cycler
     * onResume
     */
    @Override
    protected void onResume() {
        stopped = false;
        super.onResume();
    }

    /**
     * life cycler
     * onDestroy
     */
    @Override
    public void onDestroy() {
        if(unbinder!=null) unbinder.unbind();
        if(baseUnbider!=null) baseUnbider.unbind();
        unregisterReceiver(networkReceiver);
        super.onDestroy();
    }

    /**
     * layout binding
     * view binding - Butter knife
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        baseUnbider = ButterKnife.bind(this);
    }

    /** inject */
    private void inject() {
        final GgikkoApplication application = ((GgikkoApplication) getApplication());
        activityInjector = application.getInjectorCreator().makeActivityInjector(this);
        activityInjector.inject(this);
    }

    /**
     * navi up
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /** dialog */
    public void showLoading() {
        hideLoading();
        dialogVisible = true;

        buildNewLoadingDialog().show();
    }

    /** common progressbar hide */
    public void hideLoading() {
        Log.e("ggikko","hide");
        if (isDialogShowing()) {
            dialogVisible = false;

            try {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
            } catch (IllegalArgumentException ignored) { }
        }
    }

    /** common progressbar checking */
    boolean isDialogShowing() {
        return dialogVisible;
    }

    /** common progressbar show */
    AlertDialog buildNewLoadingDialog() {
        loadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create();

        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return loadingDialog;
    }

    /** common active checking */
    public boolean isActive() {
        return !stopped;
    }


    /**
     * hide keyboard
     */
    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * textview kindof에 따라 text type bold, Italic, BoldItalic, Normal 선택 지정
     * @param kindof
     * @param textViews
     */
    public void setTextTypeBold(String kindof, TextView... textViews){
        if(kindof.equals("bold")) for(TextView textView : textViews) textView.setTypeface(null, Typeface.BOLD);
        if(kindof.equals("italic")) for(TextView textView : textViews) textView.setTypeface(null, Typeface.ITALIC);
        if(kindof.equals("bolditalic")) for(TextView textView : textViews) textView.setTypeface(null, Typeface.BOLD_ITALIC);
        if(kindof.equals("normal")) for(TextView textView : textViews) textView.setTypeface(null, Typeface.NORMAL);
    }

    // TODO : should make common module for navigating in BaseActivity

    /**
     * snack bar
     * @param view
     * @param messageResId
     * @param duration
     */
    protected void snack(View view, @StringRes int messageResId, @Snackbar.Duration int duration) {
        if (view != null) Snackbar.make(view, messageResId, duration).show();
    }

    /**
     * Toast with String ref
     * @param messageResId
     * @param duration
     */
    protected void toast(@StringRes int messageResId, int duration) {
        Toast.makeText(InjectionActivity.this, messageResId, duration).show();
    }

    /**
     * Toast with String
     * @param text
     * @param duration
     *
     */
    protected void toastText(String text, int duration) {
        Toast.makeText(InjectionActivity.this, text, duration).show();
    }

    /**
     * common onkeydown
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }

    /**
     * network checking Receiver
     */
    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (isConnectedToNetwork(activeNetwork)) {
                isNetworkOn = true;

                if (isConnectedToWIFI(activeNetwork)) {
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                } else if (isConnectedToMobile(activeNetwork)) {
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                }
            } else {
                isNetworkOn = false;
            }
        }

        /**
         * 네트워크가 연결되있는지
         * @param activeNetwork
         * @return
         */
        private boolean isConnectedToNetwork(NetworkInfo activeNetwork) {
            return activeNetwork != null;
        }

        /**
         * 모바일 네트워크가 연결되어있는지
         * @param activeNetwork
         * @return
         */
        private boolean isConnectedToMobile(NetworkInfo activeNetwork) {
            return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }

        /**
         *
         * @param activeNetwork
         * @return
         */
        private boolean isConnectedToWIFI(NetworkInfo activeNetwork) {
            return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        }
    };

    /**
     * network checking from subactivity
     * @return
     */
    public boolean isNetworkOn(){
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null) isInMainActivity = false;
        return activeNetworkInfo != null ? true : false;
    }

    /**
     * show notification
     */
    public void popNotification(){
        Intent intent = new Intent(this, ImageSearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("message")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000})
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
