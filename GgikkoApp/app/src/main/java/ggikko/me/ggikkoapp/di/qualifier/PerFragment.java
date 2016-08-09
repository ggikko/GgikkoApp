package ggikko.me.ggikkoapp.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Fragment Scope
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}