package ru.dmisb.photon.screens.selector.search;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.TextView;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.databinding.ScreenSearchBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.custom_view.TagTextView;
import ru.dmisb.photon.ui.helpers.SimpleTextWatcher;
import ru.dmisb.photon.utils.AppConfig;

@SuppressWarnings("unused")
public class SearchView
        extends BaseView<SearchPresenter, ScreenSearchBinding>
        implements ISearchView {

    private final Handler handler = new Handler();

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= ISearchView =================

    @Override
    public void addTag(String tag, boolean selected) {
        TagTextView view = new TagTextView(getContext());
        view.setSelected(selected);
        view.setTagText(tag);
        view.setOnClickListener(v -> onTagClick((TagTextView) v));
        viewDataBinding.searchTags.addView(view);
    }

    private void onTagClick(TagTextView view) {
        String tag = view.getTagText();
        if (view.isSelected())
            presenter.removeTagFromFilter(tag);
        else
            presenter.addTagToFilter(tag);
        view.setSelected(!view.isSelected());
    }

    @Override
    public void addSearchHint(String hint) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), R.style.SearchHint);
        TextView view = new TextView(contextThemeWrapper);
        view.setText(hint);
        view.setOnClickListener(v -> {
            setSearchText(((TextView) v).getText().toString());
            clearSearchHints();
        });
        viewDataBinding.searchHints.addView(view);
    }

    @Override
    public void clearSearchHints() {
        viewDataBinding.searchHints.removeAllViews();
    }

    @Override
    public void setSearchText(String searchHint) {
        viewDataBinding.searchText.removeTextChangedListener(searchListener);
        viewDataBinding.searchText.setText(searchHint);
        viewDataBinding.searchText.addTextChangedListener(searchListener);
    }

    //endregion

    //region ================= BaseView =================

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    @Override
    protected void initComponent() {
        SearchScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SEARCH_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {
        viewDataBinding.searchText.removeTextChangedListener(searchListener);
        viewDataBinding.searchText.addTextChangedListener(searchListener);

        viewDataBinding.searchAction.setOnClickListener(
                v -> presenter.applySearch(viewDataBinding.searchText.getText().toString())
        );
        viewDataBinding.searchDelete.setOnClickListener(
                v -> {
                    setSearchText("");
                    clearSearchHints();
                    presenter.applySearch("");
                }
        );
    }

    SimpleTextWatcher searchListener = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString().trim();
            Runnable search = () -> presenter.onChangeSearch(text);

            handler.removeCallbacks(search);
            if (text.isEmpty()) {
                handler.postDelayed(search, 0);
            } else {
                handler.postDelayed(search, AppConfig.SEARCH_DELAY);
            }
        }
    };

    @Override
    protected void detachedFromWindow() {
        viewDataBinding.searchText.removeTextChangedListener(searchListener);
        viewDataBinding.searchAction.setOnClickListener(null);
        viewDataBinding.searchDelete.setOnClickListener(null);
    }

    //endregion
}
