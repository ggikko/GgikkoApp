package ggikko.me.ggikkoapp.ui.img.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapter;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapterDataView;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapter;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataModel;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataView;
import ggikko.me.ggikkoapp.ui.img.listener.SearchViewInterface;
import ggikko.me.ggikkoapp.ui.img.presenter.SearchPresenter;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */

@Module
public class ArchiveModule {

    private Context mContext;

    public ArchiveModule(Context context) {
        this.mContext = context;
    }

    /**
     * Archive recycler view를 위한 Adapter 제공
     * Row Save를 위한 Realm Wrapper 객체 제공
     * @param databaseRealm
     * @return
     */
    @Provides
    @PerFragment
    ArchiveAdapter provideArchiveAdapter(DatabaseRealm databaseRealm){
        return new ArchiveAdapter(mContext, databaseRealm);
    }

    /**
     * Recycler View를 위한 Data View 제공
     * @param archiveAdapter
     * @return
     */
    @Provides
    @PerFragment
    ArchiveAdapterDataView provideArchiveAdapterDataView(ArchiveAdapter archiveAdapter){
        return archiveAdapter;
    }

    /**
     * Layout manager 적용
     * @return
     */
    @Provides
    @PerFragment
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(mContext);
    }

}
