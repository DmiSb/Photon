package ru.dmisb.photon.ui.dialogs.register;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.RelativeLayout;

import ru.dmisb.photon.R;

public class RegisterBindingAdapter {
    @BindingAdapter("canSign")
    public static void getCanSign(Button button, RegisterViewModel model) {
        if (model != null && model.isModelValid())
            button.setEnabled(true);
        else
            button.setEnabled(false);
    }

    @BindingAdapter("validLogin")
    public static void getValidLogin(RelativeLayout layout, RegisterViewModel model) {
        if (model != null && model.isLoginValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("validEmail")
    public static void getValidEmail(RelativeLayout layout, RegisterViewModel model) {
        if (model != null && model.isEmailValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("validName")
    public static void getValidName(RelativeLayout layout, RegisterViewModel model) {
        if (model != null && model.isNameValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("validPassword")
    public static void getValidPassword(RelativeLayout layout, RegisterViewModel model) {
        if (model != null && model.isPasswordValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }
}
