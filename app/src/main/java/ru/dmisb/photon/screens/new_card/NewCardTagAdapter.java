package ru.dmisb.photon.screens.new_card;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.dmisb.photon.R;
import ru.dmisb.photon.databinding.ScreenNewCardTagBinding;

class NewCardTagAdapter extends RecyclerView.Adapter<NewCardTagAdapter.TagViewHolder> {

    private List<String> tagList = null;

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TagViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.screen_new_card_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        holder.binding.setTag(tagList.get(position));
    }

    @Override
    public int getItemCount() {
        return tagList != null ? tagList.size() : 0;
    }

    void addTags(List<String> tagList) {
        this.tagList = tagList;
        notifyDataSetChanged();
    }

    void updateTags() {
        notifyDataSetChanged();
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        ScreenNewCardTagBinding binding;

        TagViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
