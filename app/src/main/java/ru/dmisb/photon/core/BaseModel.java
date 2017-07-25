package ru.dmisb.photon.core;

import javax.inject.Inject;

import ru.dmisb.photon.data.repo.Repository;

@SuppressWarnings("unused")
public abstract class BaseModel {

    @Inject
    protected Repository repository;

    protected BaseModel() {
        initComponent();
    }

    protected abstract void initComponent();
}
