package ru.dmisb.photon.screens.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.dmisb.photon.R;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.databinding.ScreenMainItemBinding;
import ru.dmisb.photon.flow.ScreenScoper;

@SuppressWarnings("unused")
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<PhotoCardRealm> photoCardList = new ArrayList<>();

    @Inject
    MainPresenter presenter;

    MainAdapter() {
        MainScreen.Component component = ScreenScoper.getComponent(ScreenScoper.MAIN_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.screen_main_item, parent, false);

        int width = parent.getWidth()/2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.binding.setModel(photoCardList.get(position));
        holder.binding.mainPhoto.setOnClickListener(
                v -> presenter.onPhotoCardClick(photoCardList.get(position).getId())
        );
    }

    @Override
    public int getItemCount() {
        return photoCardList.size();
    }

    void addPhotoCard(PhotoCardRealm photoCard) {
        photoCardList.add(photoCard);
        notifyDataSetChanged();
    }

    void clearList() {
        photoCardList.clear();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        ScreenMainItemBinding binding;

        public MainViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
