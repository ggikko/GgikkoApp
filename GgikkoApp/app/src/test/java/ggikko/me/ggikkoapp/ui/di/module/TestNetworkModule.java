package ggikko.me.ggikkoapp.ui.di.module;


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
 * Created by ggikko on 16. 8. 6..
 */

@Module
public class TestNetworkModule {

    private String mBaseUrl;

    public TestNetworkModule(String baseUrl) {
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
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
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
     * @param
     * @return okHttpClient
     */
    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        if (AppLog.SHOULD_LOG) {
            okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return okBuilder.build();
    }
}
