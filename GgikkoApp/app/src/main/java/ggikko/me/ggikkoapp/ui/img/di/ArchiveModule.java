package ggikko.me.ggikkoapp.ui.img.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapter;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapterDataView;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */

@Module
public class ArchiveModule {

  private Context context;

  public ArchiveModule(Context context) {
    this.context = context;
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Archive recycler view를 위한 Adapter 제공 Row Save를 위한 Realm Wrapper 객체 제공
   */
  @Provides
  @PerFragment
  ArchiveAdapter provideArchiveAdapter(DatabaseRealm databaseRealm) {
    return new ArchiveAdapter(context, databaseRealm);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Recycler View를 위한 Data View 제공
   */
  @Provides
  @PerFragment
  ArchiveAdapterDataView provideArchiveAdapterDataView(ArchiveAdapter archiveAdapter) {
    return archiveAdapter;
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Layout manager 적용
   */
  @Provides
  @PerFragment
  LinearLayoutManager provideLinearLayoutManager() {
    return new LinearLayoutManager(context);
  }

}
