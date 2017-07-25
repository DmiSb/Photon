package ru.dmisb.photon.ui.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;

import ru.dmisb.photon.R;

public class FilterTextView extends android.support.v7.widget.AppCompatTextView {

    private boolean filtered;
    private boolean shade;
    private OnFilteredChangeListener listener;

    public FilterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributeValues = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.FilterTextView, 0, 0);
        try {
            shade = attributeValues.getBoolean(R.styleable.FilterTextView_shade, false);
        } finally {
            attributeValues.recycle();
        }

        setOnClickListener(v -> updateFiltered());
    }

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
        setColor();
        if (listener != null)
            listener.onFilteredChange(this, filtered);
    }

    @SuppressWarnings("unused")
    public boolean isShade() {
        return shade;
    }

    @SuppressWarnings("unused")
    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public void addListener(OnFilteredChangeListener listener) {
        this.listener = listener;
    }

    private void updateFiltered() {
        setFiltered(!filtered);
    }

    private void setColor(){
        int color;

        if (this.filtered)
            color = ResourcesCompat.getColor(this.getResources(), R.color.colorAccent, null);
        else
            color = ResourcesCompat.getColor(this.getResources(), R.color.colorPrimaryDark, null);

        if (!shade) {
            Drawable[] drawables = this.getCompoundDrawables();
            if (drawables[1] != null)
                drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }

        this.setTextColor(color);
    }

    public interface OnFilteredChangeListener {
        void onFilteredChange(FilterTextView view, boolean filtered);
    }
}
