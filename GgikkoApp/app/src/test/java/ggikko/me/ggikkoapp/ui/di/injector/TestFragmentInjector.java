package ggikko.me.ggikkoapp.ui.di.injector;

import ggikko.me.ggikkoapp.ui.di.component.TestFragmentComponent;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * fragment injector
 */
public class TestFragmentInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private final TestFragmentComponent fragmentComponent;

    public TestFragmentInjector(TestFragmentComponent fragmentComponent) {
        this.fragmentComponent = fragmentComponent;
    }


//    public void inject(BaseFragment fragment) {}

}
