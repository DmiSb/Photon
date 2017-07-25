package ru.dmisb.photon.ui.helpers;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

@SuppressWarnings("unused")
public class DataBindingAdapter {

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void loadImage(ImageView view, String url, Drawable placeholder) {
        if (TextUtils.isEmpty(url))
            return;

        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .placeholder(placeholder)
                .dontAnimate()
                .into(view);
    }

    @BindingAdapter({"circleImageUrl", "placeholder"})
    public static void loadCircleImage(ImageView view, String url, Drawable placeholder) {
        if (TextUtils.isEmpty(url))
            return;

        Glide.with(view.getContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(placeholder)
                .dontAnimate()
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(view.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
