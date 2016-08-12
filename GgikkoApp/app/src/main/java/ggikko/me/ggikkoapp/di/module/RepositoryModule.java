package ggikko.me.ggikkoapp.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 12..
 */
@Module
public class RepositoryModule {

    private Context mContext;

    public RepositoryModule(Context context){
        this.mContext = context;
    }
    /**
     * realm database singleton object
     * @return
     */
    @Provides
    @Singleton
    public DatabaseRealm provideDatabaseRealm() {
        return new DatabaseRealm(mContext);
    }


}
