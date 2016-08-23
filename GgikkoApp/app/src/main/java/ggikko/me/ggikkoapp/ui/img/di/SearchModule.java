package ggikko.me.ggikkoapp.ui.img.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
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

  private Context context;
  private SearchViewInterface searchViewInterface;

  public SearchModule(SearchViewInterface searchViewInterface, Context context) {
    this.searchViewInterface = searchViewInterface;
    this.context = context;
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Search recycler view를 위한 Adapter 제공 Row Save를 위한 Realm Wrapper 객체 제공
   */
  @Provides
  @PerFragment
  SearchAdapter provideSearchAdapter(DatabaseRealm databaseRealm) {
    return new SearchAdapter(context, databaseRealm);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Recycler View를 위한 Data Model 제공
   */
  @Provides
  @PerFragment
  SearchAdapterDataModel provideSearchAdapterDataModel(SearchAdapter searchAdapter) {
    return searchAdapter;
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Recycler View를 위한 Data View 제공
   */
  @Provides
  @PerFragment
  SearchAdapterDataView provideSearchAdapterDataView(SearchAdapter searchAdapter) {
    return searchAdapter;
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Layout manager제공 TODO : Paging을 위한 custom Layout manager
   */
  @Provides
  @PerFragment
  LinearLayoutManager provideLinearLayoutManager() {
    return new LinearLayoutManager(context);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Presenter 제공
   */
  @Provides
  @PerFragment
  SearchPresenter provideSearchPresenter(SearchAdapterDataModel adapterDataModel,
                                         SearchAdapterDataView adapterDataView) {
    return new SearchPresenter(searchViewInterface, adapterDataModel, adapterDataView);
  }


}
