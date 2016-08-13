package ggikko.me.ggikkoapp.di.module;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.qualifier.PerActivity;

/**
 * Activity module
 * TODO : formatter etc..
 */
@Module
public class TestActivityModule {
    private final Activity activity;

    public TestActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}
