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

    @Provides
    @PerFragment
    SearchAdapter provideSearchAdapter(){
        return new SearchAdapter(mContext);
    }

    @Provides
    @PerFragment
    SearchAdapterDataModel provideSearchAdapterDataModel(SearchAdapter searchAdapter){
        return searchAdapter;
    }

    @Provides
    @PerFragment
    SearchAdapterDataView provideSearchAdapterDataView(SearchAdapter searchAdapter){
        return searchAdapter;
    }

    @Provides
    @PerFragment
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(mContext);
    }

    @Provides
    @PerFragment
    SearchPresenter provideSearchPresenter(SearchAdapterDataModel searchAdapterDataModel, SearchAdapterDataView searchAdapterDataView){
        return new SearchPresenter(mSearchViewInterface, searchAdapterDataModel, searchAdapterDataView);
    }


}
