package ru.dmisb.photon.flow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

@SuppressWarnings("unused")
@Scope
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Screen {
    int value();
}
