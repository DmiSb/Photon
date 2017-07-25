package ru.dmisb.photon.screens.selector.filter;

import android.databinding.BindingAdapter;
import android.widget.Button;

import ru.dmisb.photon.R;

public class FilterBindingAdpter {

    @BindingAdapter("caption")
    public static void filterButtonText(Button button, FilterViewModel model) {
        if (model == null)
            return;

        String text;
        if (model.isActive() && !model.filterChanged()) {
            text = button.getResources().getString(R.string.filter_disable);
        } else {
            text = button.getResources().getString(R.string.find);
        }
        button.setText(text);
    }
}
