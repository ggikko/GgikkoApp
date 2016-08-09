package ggikko.me.ggikkoapp;

import android.app.Application;
import android.content.Context;

import ggikko.me.ggikkoapp.di.component.ApplicationComponent;
import ggikko.me.ggikkoapp.di.injector.ApplicationInjector;
import ggikko.me.ggikkoapp.di.injector.InjectorCreator;
import lombok.Getter;

/**
 * Created by ggikko on 16. 8. 9..
 */
public class GgikkoApplication extends Application {

    @Getter
    private static Context context;

    private static void setContext(GgikkoApplication application) {
        context = application;
    }

    @Getter
    ApplicationComponent applicationComponent;

    @Getter
    protected InjectorCreator injectorCreator;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        injectorCreator = makeInjectorCreator();
        inject();
    }

    protected InjectorCreator makeInjectorCreator() {
        return new InjectorCreator();
    }

    protected final void inject() {
        final ApplicationInjector applicationInjector = injectorCreator.makeApplicationInjector(this);
        applicationComponent = applicationInjector.getApplicationComponent();
        applicationInjector.inject(this);
    }
}
