package ru.dmisb.photon.screens.selector.search;

@SuppressWarnings("unused")
interface ISearchPresenter {
    void addTagToFilter(String tag);
    void removeTagFromFilter(String tag);

    void onChangeSearch(String searchText);
    void applySearch(String searchText);
}
