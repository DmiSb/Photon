package ru.dmisb.photon.ui.dialogs.auth;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.RelativeLayout;

import ru.dmisb.photon.R;

public class AuthBindingAdapter {

    @BindingAdapter("canSign")
    public static void getCanSign(Button button, AuthViewModel model) {
        if (model != null && model.isPasswordValid() && model.isEmailValid())
            button.setEnabled(true);
        else
            button.setEnabled(false);
    }

    @BindingAdapter("validEmail")
    public static void getValidEmail(RelativeLayout layout, AuthViewModel model) {
        if (model != null && model.isEmailValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("validPassword")
    public static void getValidPassword(RelativeLayout layout, AuthViewModel model) {
        if (model != null && model.isPasswordValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }
}
