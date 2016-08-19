package ggikko.me.ggikkoapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.tsengvn.typekit.Typekit;

import javax.inject.Inject;

import ggikko.me.ggikkoapp.di.component.ApplicationComponent;
import ggikko.me.ggikkoapp.di.injector.ApplicationInjector;
import ggikko.me.ggikkoapp.di.injector.InjectorCreator;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;
import lombok.Getter;

/**
 * Created by ggikko on 16. 8. 9..
 */
public class GgikkoApplication extends Application {

    @Getter private static Context context;

    public static boolean DEBUG;

    /**
     * Application를 반환한다.
     * Application Context는 Activity Context와 중복되어 duplicate버그를 발생시킨다.
     * 따라서 Singletone Method로 Application을 반환한다.
     * @param application
     */
    private static void setContext(GgikkoApplication application) {
        context = application;
    }

    /**
     * Application module과 bridge역할을 하며 injector를 통해 inject시킨다.
     */
    @Getter ApplicationComponent applicationComponent;

    /**
     * Database Realm 객체를 해제할 때 사용한다.
     */
//    @Inject DatabaseRealm databaseRealm;

    /**
     * Application module과 bridge역할을 하며 injector를 통해 inject시킨다.
     */
    @Getter protected InjectorCreator injectorCreator;

    /**
     * Font를 적용시킨다. - Typekit
     * 앱의 Injector 클래스를 생성하고 Application componet를 생성하여 주입한다. 각 Activity, Fragment는 Injection class를 상속받으며
     * 이를 통해 각 컴포넌트가 생성될 때 injector를 통해 주입하게 된다.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        this.DEBUG = isDebuggable(this);
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "Roboto-Medium.ttf"));
        setContext(this);
        injectorCreator = makeInjectorCreator();
        inject();
    }

    /**
     * Injector을 만든다
     * @return
     */
    protected InjectorCreator makeInjectorCreator() {
        return new InjectorCreator();
    }

    /**
     * Injector를 통해 Application component를 생성하고 inject 시킨다.
     */
    protected final void inject() {
        final ApplicationInjector applicationInjector = injectorCreator.makeApplicationInjector(this);
        applicationComponent = applicationInjector.getApplicationComponent();
        applicationInjector.inject(this);
    }

    /**
     * APP의 상태가 Debug모드인지 판별
     * @param context
     * @return
     */
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {}
        return debuggable;
    }

    /**
     * 앱이 종료 될 떼 realm객체를 반환한다.
     */
//    @Override
//    public void onTerminate() {
//        databaseRealm.close();
//        super.onTerminate();
//    }
}
