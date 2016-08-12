package ggikko.me.ggikkoapp.ui.img.di;

import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

@PerFragment
@Subcomponent(modules = ArchiveModule.class)
public interface ArchiveComponent {
    void inject(ArchiveFragment archiveFragment);
}
