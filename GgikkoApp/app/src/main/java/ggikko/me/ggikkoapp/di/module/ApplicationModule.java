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
public class ApplicationModule {
    private final GgikkoApplication application;

    public ApplicationModule(GgikkoApplication application) {
        this.application = application;
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
