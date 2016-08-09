package ggikko.me.ggikkoapp.di.module.network;


import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.util.log.AppLog;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 16. 6. 23..
 */

@Module
public class NetworkModule {

    private String mBaseUrl;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    /**
     * Rx Factory
     * @return
     */
    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    /**
     * Gson Factory
     * @param gson
     * @return
     */
    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    /**
     * Gson factor, Convert Rx
     * @param gsonConverterFactory
     * @param rxJavaCallAdapterFactory
     * @return
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    //값을 null 가능
    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder().setLenient().serializeNulls().create();
    }


    /**
     * Okhttp logging intercepter
     * @param sharedPreferences
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(SharedPreferences sharedPreferences) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        if (AppLog.SHOULD_LOG) {
            okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return okBuilder.build();
    }
}
