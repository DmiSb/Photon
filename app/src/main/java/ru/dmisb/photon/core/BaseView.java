package ru.dmisb.photon.core;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import javax.inject.Inject;

public abstract class BaseView<P extends BasePresenter, B extends ViewDataBinding>
        extends FrameLayout implements IBaseView {

    protected B viewDataBinding;

    @Inject
    protected P presenter;

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()){
           initComponent();
        }
    }

    protected abstract void initComponent();

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            viewDataBinding = DataBindingUtil.bind(this);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()){
            presenter.takeView(this);
            attachedToWindow();
        }
    }

    protected abstract void attachedToWindow();

    @Override
    protected void onDetachedFromWindow() {
        if (!isInEditMode()){
            detachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    protected abstract void detachedFromWindow();
}
