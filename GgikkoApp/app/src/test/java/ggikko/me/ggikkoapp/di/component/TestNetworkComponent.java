package ggikko.me.ggikkoapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ggikko.me.ggikkoapp.di.module.TestNetworkModule;
import ggikko.me.ggikkoapp.di.module.network.NetworkModule;
import retrofit2.Retrofit;

/**
 * Created by ggikko on 16. 5. 25..
 */

/**
 * network module for API component
 */
@Singleton
@Component(modules = TestNetworkModule.class)
public interface TestNetworkComponent {

    Retrofit retrofit();
}
