package com.naoya.matecari.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Naoya on 16-01-24.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForActivity {
}

