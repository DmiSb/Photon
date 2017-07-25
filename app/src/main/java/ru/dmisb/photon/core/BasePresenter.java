package ru.dmisb.photon.core;

import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mortar.MortarScope;
import mortar.ViewPresenter;
import ru.dmisb.photon.screens.root.IRootPresenter;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel>
        extends ViewPresenter<V> {

    private CompositeDisposable compSubs = new CompositeDisposable();

    @Inject
    protected M model;
    @Inject
    protected IRootPresenter rootPresenter;

    @Override
    protected void onEnterScope(MortarScope scope) {
        super.onEnterScope(scope);
        initComponent();
    }

    protected abstract void initComponent();

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        initView();
        initActionBar();
    }

    protected abstract void initView();

    protected abstract void initActionBar();

    protected void addDisposable(Disposable disposable) {
        compSubs.add(disposable);
    }

    @Override
    public void dropView(V view) {
        clearDisposable();
        super.dropView(view);
    }

    protected void clearDisposable() {
        compSubs.clear();
    }
}
