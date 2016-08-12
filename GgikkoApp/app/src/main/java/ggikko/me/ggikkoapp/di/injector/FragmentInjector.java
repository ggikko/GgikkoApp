package ggikko.me.ggikkoapp.di.injector;


import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.di.component.FragmentComponent;
import ggikko.me.ggikkoapp.ui.img.di.ArchiveComponent;
import ggikko.me.ggikkoapp.ui.img.di.SearchComponent;
import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * fragment injector
 */
public class FragmentInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private FragmentComponent fragmentComponent;

    @Getter(value = AccessLevel.PACKAGE)
    private SearchComponent searchComponent;

    @Getter(value = AccessLevel.PACKAGE)
    private ArchiveComponent archiveComponent;

    public FragmentInjector(FragmentComponent fragmentComponent) {
        this.fragmentComponent = fragmentComponent;
    }

    public FragmentInjector(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    public FragmentInjector(ArchiveComponent archiveComponent) {
        this.archiveComponent = archiveComponent;
    }

    public void inject(InjectionFragment fragment) {
        if (fragment instanceof SearchFragment) searchComponent.inject((SearchFragment) fragment);
        if (fragment instanceof ArchiveFragment) archiveComponent.inject((ArchiveFragment) fragment);
    }
}
