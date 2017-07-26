package ru.dmisb.photon.core;

import com.birbit.android.jobqueue.JobManager;

import javax.inject.Inject;

import ru.dmisb.photon.data.repo.Repository;

@SuppressWarnings("unused")
public abstract class BaseModel {

    @Inject
    protected Repository repository;
    @Inject
    protected JobManager jobManager;

    protected BaseModel() {
        initComponent();
    }

    protected abstract void initComponent();
}
