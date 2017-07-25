package ru.dmisb.photon.core;

import javax.inject.Inject;

import ru.dmisb.photon.data.repo.Repository;

public abstract class BaseModel {

    @Inject
    protected Repository repository;

    public BaseModel() {
        initComponent();
    }

    protected abstract void initComponent();
}
