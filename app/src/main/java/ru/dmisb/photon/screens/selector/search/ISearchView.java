package ru.dmisb.photon.screens.selector.search;

public interface ISearchView {
    void addTag(String tag, boolean selected);
    void addSearchHint(String hint);
    void setSearchText(String searchHint);
    void clearSearchHints();
}
