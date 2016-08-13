package ggikko.me.ggikkoapp.di.module;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.GgikkoApplication;

/**
 * Application module
 * TODO : Realm etc
 */
@Module
public class TestApplicationModule {
    private final GgikkoApplication application;

    public TestApplicationModule(GgikkoApplication application) {
        this.application = application;
    }

    /**
     * Provide App Context
     * @return
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    /**
     * Provice resource
     * @return
     */
    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    /**
     * sharedPref
     * @return
     */
    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
    
}
