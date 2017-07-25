package ru.dmisb.photon.screens.selector.filter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.databinding.ScreenFilterBinding;
import ru.dmisb.photon.flow.ScreenScoper;

public class FilterView extends BaseView<FilterPresenter, ScreenFilterBinding> {

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void setViewModel(FilterViewModel viewModel) {
        viewDataBinding.setModel(viewModel);
        viewDataBinding.filterParams.setParams(viewModel);
    }

    //region ================= BaseView =================

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    @Override
    protected void initComponent() {
        FilterScreen.Component component = ScreenScoper.getComponent(ScreenScoper.FILTER_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {
        viewDataBinding.filterAction.setOnClickListener(v -> presenter.filterActionClick());
    }

    @Override
    protected void detachedFromWindow() {
        viewDataBinding.filterAction.setOnClickListener(null);
    }

    //endregion
}
