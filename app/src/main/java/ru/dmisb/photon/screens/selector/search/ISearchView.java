package ru.dmisb.photon.screens.selector.search;

@SuppressWarnings("unused")
interface ISearchView {
    void addTag(String tag, boolean selected);
    void addSearchHint(String hint);
    void setSearchText(String searchHint);
    void clearSearchHints();
}
