package ggikko.me.ggikkoapp.di.injector;


import android.content.Context;

import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.di.component.ActivityComponent;
import ggikko.me.ggikkoapp.di.component.ApplicationComponent;
import ggikko.me.ggikkoapp.di.component.DaggerApplicationComponent;
import ggikko.me.ggikkoapp.di.component.FragmentComponent;
import ggikko.me.ggikkoapp.di.module.ActivityModule;
import ggikko.me.ggikkoapp.di.module.ApplicationModule;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.module.RepositoryModule;
import ggikko.me.ggikkoapp.di.module.network.NetworkModule;
import ggikko.me.ggikkoapp.ui.img.di.SearchComponent;
import ggikko.me.ggikkoapp.ui.img.di.SearchModule;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;
import ggikko.me.ggikkoapp.util.api.NetworkConfig;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * injector creator for application, activity, fragment
 */
public class InjectorCreator {

    private ApplicationComponent applicationComponent;

    public ApplicationInjector makeApplicationInjector(GgikkoApplication application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .networkModule(new NetworkModule(NetworkConfig.DEV_URL))
                .repositoryModule(new RepositoryModule(application))
                .build();
        return new ApplicationInjector(applicationComponent);
    }

    public ActivityInjector makeActivityInjector(InjectionActivity activity) {
        final ActivityComponent activityComponent = applicationComponent.plusActivityComponent(new ActivityModule(activity));
        return new ActivityInjector(activityComponent);
    }

    public FragmentInjector makeFragmentInjector(InjectionFragment fragment, Context context) {
        final ActivityInjector activityInjector = ((InjectionActivity) fragment.getActivity()).getActivityInjector();
        final FragmentComponent fragmentComponent = activityInjector.getActivityComponent().plusFragmentComponent(new FragmentModule(fragment));
        if(fragment instanceof SearchFragment) {
            final SearchComponent searchComponent = activityInjector.getActivityComponent().plusSearchComponent(new SearchModule((SearchFragment) fragment, fragment.getContext()));
            return new FragmentInjector(searchComponent);
        }
        return new FragmentInjector(fragmentComponent);
    }
}
