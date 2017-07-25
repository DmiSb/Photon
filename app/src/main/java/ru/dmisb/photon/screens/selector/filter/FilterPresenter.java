package ru.dmisb.photon.screens.selector.filter;

import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.dto.FilterDto;
import ru.dmisb.photon.flow.ScreenScoper;

public class FilterPresenter
        extends BasePresenter<FilterView, FilterModel>
        implements IFilterPresenter {

    private FilterViewModel viewModel = new FilterViewModel();

    //region ================= IFilterPresenter =================

    @Override
    public void filterActionClick() {
        if (viewModel.filterChanged())
            model.setFilter(new FilterDto(viewModel));
        else
            model.clearFilter();
        rootPresenter.showMainScreen();
    }

    //endregion

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        FilterScreen.Component component = ScreenScoper.getComponent(ScreenScoper.FILTER_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        FilterDto filter = model.getFilter();
        if (filter != null)
            viewModel.setFilter(filter);

        if (getView() != null)
            getView().setViewModel(viewModel);
    }

    @Override
    protected void initActionBar() {

    }

    //endregion
}
