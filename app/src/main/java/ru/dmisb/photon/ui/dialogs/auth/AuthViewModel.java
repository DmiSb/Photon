package ru.dmisb.photon.ui.dialogs.auth;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import java.util.regex.Pattern;

import ru.dmisb.photon.utils.Constants;

import static android.util.Patterns.EMAIL_ADDRESS;

@SuppressWarnings("unused")
public class AuthViewModel extends BaseObservable {
    private String email;
    private String password;

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public boolean isEmailValid() {
        return !TextUtils.isEmpty(email) && EMAIL_ADDRESS.matcher(email).matches();
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public boolean isPasswordValid() {
        return !TextUtils.isEmpty(password) && Pattern.matches(Constants.PASSWORD_PATTERN, password);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }
}
