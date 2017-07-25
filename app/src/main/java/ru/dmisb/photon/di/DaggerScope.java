package ru.dmisb.photon.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@SuppressWarnings("unused")
@Scope
@Retention(RetentionPolicy.SOURCE)
public @interface DaggerScope {
    Class<?> value();
}