package ggikko.me.ggikkoapp.di.injector;


import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.di.component.FragmentComponent;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * fragment injector
 */
public class FragmentInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private final FragmentComponent fragmentComponent;

    public FragmentInjector(FragmentComponent fragmentComponent) {
        this.fragmentComponent = fragmentComponent;
    }

    public void inject(InjectionFragment fragment) {

    }
}
