package ru.dmisb.photon.screens.selector.search;

interface ISearchPresenter {
    void addTagToFilter(String tag);
    void removeTagFromFilter(String tag);

    void onChangeSearch(String searchText);
    void applySearch(String searchText);
}
