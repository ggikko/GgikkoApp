package ggikko.me.ggikkoapp.di.injector;

import ggikko.me.ggikkoapp.di.component.TestFragmentComponent;
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
