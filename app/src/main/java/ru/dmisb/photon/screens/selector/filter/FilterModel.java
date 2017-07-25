package ru.dmisb.photon.screens.selector.filter;

import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.dto.FilterDto;
import ru.dmisb.photon.flow.ScreenScoper;

public class FilterModel extends BaseModel {

    @Override
    protected void initComponent() {
        FilterScreen.Component component = ScreenScoper.getComponent(ScreenScoper.FILTER_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    void setFilter(FilterDto filter) {
        repository.setFilter(filter);
    }

    FilterDto getFilter() {
        return repository.getFilter();
    }

    void clearFilter() {
        repository.clearFilter();
    }
}
