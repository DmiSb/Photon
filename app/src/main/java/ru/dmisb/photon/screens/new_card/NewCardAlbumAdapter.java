package ru.dmisb.photon.screens.new_card;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.dmisb.photon.R;
import ru.dmisb.photon.databinding.ScreenNewCardAlbumBinding;
import ru.dmisb.photon.flow.ScreenScoper;

public class NewCardAlbumAdapter extends RecyclerView.Adapter<NewCardAlbumAdapter.AlbumHolder> {

    private List<NewCardAlbumViewModel> albumList = new ArrayList<>();

    @Inject
    NewCardPresenter presenter;

    NewCardAlbumAdapter() {
        NewCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.NEW_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.screen_new_card_album, parent, false);

        int width = parent.getWidth()/2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
        return new NewCardAlbumAdapter.AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        holder.binding.setModel(albumList.get(position));
        holder.binding.albumPhoto.setOnClickListener(v -> {
            for (NewCardAlbumViewModel album : albumList) {
                album.setSelected(false);
            }

            NewCardAlbumViewModel album = albumList.get(position);
            album.setSelected(true);
            presenter.onAlbumSelected(album.getId());
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    void addAlbum(NewCardAlbumViewModel viewModel) {
        albumList.add(viewModel);
        notifyDataSetChanged();
    }

    class AlbumHolder extends RecyclerView.ViewHolder {

        ScreenNewCardAlbumBinding binding;

        AlbumHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
