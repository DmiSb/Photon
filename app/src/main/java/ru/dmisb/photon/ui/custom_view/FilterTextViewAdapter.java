package ru.dmisb.photon.ui.custom_view;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;

import static ru.dmisb.photon.ui.custom_view.FilterTextView.*;

@SuppressWarnings("unused")
public class FilterTextViewAdapter {

    @InverseBindingAdapter(attribute = "filtered")
    public static boolean isFiltered(FilterTextView view) {
        return view.isFiltered();
    }

    @BindingAdapter("filtered")
    public static void setFiltered(FilterTextView view, boolean filtered) {
        if (view.isFiltered() != filtered)
            view.setFiltered(filtered);
    }

    @BindingAdapter(value = {"onFilteredChange", "filteredAttrChanged"},
            requireAll = false)
    public static void setListeners(FilterTextView view,
                                    final OnFilteredChangeListener onFilteredChangeListener,
                                    final InverseBindingListener inverseBindingListener) {

        OnFilteredChangeListener newListener;
        if (inverseBindingListener == null) {
            newListener = onFilteredChangeListener;
        } else {
            newListener = (view1, filtered) -> {
                if (onFilteredChangeListener != null) {
                    onFilteredChangeListener.onFilteredChange(view1, filtered);
                }
                inverseBindingListener.onChange();
            };
        }

        if (newListener != null)
            view.addListener(newListener);
    }
}
