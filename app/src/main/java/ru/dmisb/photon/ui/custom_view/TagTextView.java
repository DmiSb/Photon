package ru.dmisb.photon.ui.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;

import ru.dmisb.photon.R;

@SuppressWarnings("unused")
public class TagTextView extends android.support.v7.widget.AppCompatTextView {

    private static final @StyleRes int defaultStyle = R.style.PhotoCardTag;

    private static final @DrawableRes int defaultBackground = R.drawable.border_black;
    private static final @DrawableRes int selectedBackground = R.drawable.border_accent;

    private boolean selected;
    private String tagText;

    public TagTextView(Context context) {
        this(new ContextThemeWrapper(context, defaultStyle), null);
    }

    public TagTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributeValues = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TagTextView, 0, 0);
        try {
            setSelected(attributeValues.getBoolean(R.styleable.TagTextView_selected, false));
        } finally {
            attributeValues.recycle();
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        setBackgroundResource(selected ? selectedBackground : defaultBackground);
        invalidate();
    }

    public String getTagText() {
        return tagText;
    }

    @SuppressLint("SetTextI18n")
    public void setTagText(String tagText) {
        this.tagText = tagText;
        setText("#" + tagText);
        invalidate();
    }
}
