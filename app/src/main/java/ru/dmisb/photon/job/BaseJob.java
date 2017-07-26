package ru.dmisb.photon.job;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;

import javax.inject.Inject;

import ru.dmisb.photon.data.repo.Repository;

public abstract class BaseJob extends Job {

    @Inject
    Repository repository;

    BaseJob(Params params) {
        super(params);
    }
}
