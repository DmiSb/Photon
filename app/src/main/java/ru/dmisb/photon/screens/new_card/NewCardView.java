package ru.dmisb.photon.screens.new_card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.databinding.ScreenNewCardBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.filter.FilterViewModel;

public class NewCardView extends BaseView<NewCardPresenter, ScreenNewCardBinding> {

    private final NewCardTagAdapter tagAdapter = new NewCardTagAdapter();
    private final NewCardAlbumAdapter albumAdapter = new NewCardAlbumAdapter();

    public NewCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void setViewModel(NewCardViewModel viewModel, FilterViewModel filterViewModel) {
        viewDataBinding.setModel(viewModel);
        viewDataBinding.newCardParams.setParams(filterViewModel);
        tagAdapter.addTags(viewModel.getTags());
    }

    void addAlbim(NewCardAlbum album) {
        albumAdapter.addAlbum(album);
    }

    //region ================= BaseView =================

    @Override
    protected void initComponent() {
        NewCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.NEW_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            initRecyclerViews();
        }
    }

    private void initRecyclerViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewDataBinding.newCardTagList.setLayoutManager(layoutManager);
        viewDataBinding.newCardTagList.setAdapter(tagAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        RecyclerView recyclerView = viewDataBinding.newCardAlbumList;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(albumAdapter);
    }

    @Override
    protected void attachedToWindow() {
        viewDataBinding.newCardTitleDeleteImg.setOnClickListener(v -> presenter.onTitleClearClick());
        viewDataBinding.newCardAddTagImg.setOnClickListener(v -> {
                presenter.onTagAddClick();
                tagAdapter.updateTags();
        });
        viewDataBinding.newCardTagDeleteImg.setOnClickListener(v -> presenter.onTagClearClick());

        viewDataBinding.newCardOk.setOnClickListener(v -> presenter.onOkClick());
        viewDataBinding.newCardCancel.setOnClickListener(v -> presenter.onCancelClick());
    }

    @Override
    protected void detachedFromWindow() {
        viewDataBinding.newCardTitleDeleteImg.setOnClickListener(null);
        viewDataBinding.newCardAddTagImg.setOnClickListener(null);
        viewDataBinding.newCardTagDeleteImg.setOnClickListener(null);

        viewDataBinding.newCardOk.setOnClickListener(null);
        viewDataBinding.newCardCancel.setOnClickListener(null);
    }

    @Override
    public boolean viewOnBackPressed() {
        presenter.onCancelClick();
        return true;
    }

    //endregion
}
