package ggikko.me.ggikkoapp.ui.img.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.module.network.ApiModule;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
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
public class SearchModule {

    private Context mContext;
    private SearchViewInterface mSearchViewInterface;

    public SearchModule(SearchViewInterface searchViewInterface, Context context) {
        this.mSearchViewInterface = searchViewInterface;
        this.mContext = context;
    }

    /**
     * Search recycler view를 위한 Adapter 제공
     * Row Save를 위한 Realm Wrapper 객체 제공
     * @param databaseRealm
     * @return
     */
    @Provides
    @PerFragment
    SearchAdapter provideSearchAdapter(DatabaseRealm databaseRealm){
        return new SearchAdapter(mContext, databaseRealm);
    }

    /**
     * Recycler View를 위한 Data Model 제공
     * @param searchAdapter
     * @return
     */
    @Provides
    @PerFragment
    SearchAdapterDataModel provideSearchAdapterDataModel(SearchAdapter searchAdapter){
        return searchAdapter;
    }

    /**
     * Recycler View를 위한 Data View 제공
     * @param searchAdapter
     * @return
     */
    @Provides
    @PerFragment
    SearchAdapterDataView provideSearchAdapterDataView(SearchAdapter searchAdapter){
        return searchAdapter;
    }

    /**
     * Layout manager제공
     * TODO : Paging을 위한 custom Layout manager
     * @return
     */
    @Provides
    @PerFragment
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(mContext);
    }

    /**
     * Presenter 제공
     * @param searchAdapterDataModel
     * @param searchAdapterDataView
     * @return
     */
    @Provides
    @PerFragment
    SearchPresenter provideSearchPresenter(SearchAdapterDataModel searchAdapterDataModel, SearchAdapterDataView searchAdapterDataView){
        return new SearchPresenter(mSearchViewInterface, searchAdapterDataModel, searchAdapterDataView);
    }


}
