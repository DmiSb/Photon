package ru.dmisb.photon.screens.profile;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.dmisb.photon.R;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.databinding.ScreenProfileAlbumBinding;
import ru.dmisb.photon.flow.ScreenScoper;

@SuppressWarnings("unused")
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.AlbumHolder> {

    private UserRealm user;

    @Inject
    ProfilePresenter presenter;

    ProfileAdapter() {
        ProfileScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.screen_profile_album, parent, false);

        int width = parent.getWidth()/2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        AlbumRealm album = user.getActiveAlbums().get(position);

        holder.binding.setModel(album);

        setAlbumShow(holder, position, album);

        holder.binding.portfolioAlbumPhoto.setOnLongClickListener(v -> {
            holder.binding.portfolioAlbumPhoto.setOnClickListener(null);
            holder.binding.profileAlbumMenu.setVisibility(View.VISIBLE);
            Observable
                    .timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribe(
                            aLong -> {
                                holder.binding.profileAlbumMenu.setVisibility(View.GONE);
                                setAlbumShow(holder, position, album);
                            }
                    );
            return false;
        });

        holder.binding.profileAlbumDelete.setOnClickListener(v ->
                presenter.onAlbumDeleteClick(holder.binding.getModel().getId())
        );
        holder.binding.profileAlbumEdit.setOnClickListener(v ->
                presenter.onAlbumEditClick(album)
        );
    }

    private void setAlbumShow(AlbumHolder holder, int position, AlbumRealm album) {
        holder.binding.portfolioAlbumPhoto.setOnClickListener(
                v -> presenter.onAlbumShowClick(album.getId(), position)
        );
    }

    @Override
    public int getItemCount() {
        return user != null ? user.getAlbumCount() : 0;
    }

    void setUser(UserRealm user) {
        this.user = user;
        notifyDataSetChanged();
    }

    class AlbumHolder extends RecyclerView.ViewHolder {

        ScreenProfileAlbumBinding binding;

        AlbumHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
