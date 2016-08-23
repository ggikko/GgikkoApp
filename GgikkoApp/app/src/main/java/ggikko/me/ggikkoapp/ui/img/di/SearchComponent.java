package ggikko.me.ggikkoapp.ui.img.di;

import dagger.Component;
import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.ui.img.di.SearchModule;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

@PerFragment
@Subcomponent(modules = SearchModule.class)
public interface SearchComponent {
  void inject(SearchFragment searchFragment);
}
