package ru.dmisb.photon.screens.selector;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.databinding.ScreenSelectorBinding;
import ru.dmisb.photon.flow.ScreenScoper;

public class SelectorView extends BaseView<SelectorPresenter, ScreenSelectorBinding> {

    private SelectorAdapter adapter = new SelectorAdapter();

    public SelectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= BaseView =================

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    @Override
    protected void initComponent() {
        SelectorScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SELECTOR_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            adapter.addPageTitle(getContext().getString(R.string.search_title));
            adapter.addPageTitle(getContext().getString(R.string.filter_title));
            viewDataBinding.selectorPager.setAdapter(adapter);
        }
    }

    @Override
    protected void attachedToWindow() {

    }

    @Override
    protected void detachedFromWindow() {

    }

    //endregion

    ViewPager getViewPager() {
        return viewDataBinding.selectorPager;
    }
}
