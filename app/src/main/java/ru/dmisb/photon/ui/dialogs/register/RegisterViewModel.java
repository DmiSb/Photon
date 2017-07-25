package ru.dmisb.photon.ui.dialogs.register;

import android.databinding.Bindable;
import android.text.TextUtils;

import java.util.regex.Pattern;

import ru.dmisb.photon.core.BaseViewModel;
import ru.dmisb.photon.utils.Constants;

import static android.util.Patterns.EMAIL_ADDRESS;


@SuppressWarnings("unused")
public class RegisterViewModel extends BaseViewModel {
    private String login;
    private String email;
    private String name;
    private String password;

    @Bindable
    boolean isModelValid() {
        return isLoginValid() && isEmailValid() && isNameValid() && isPasswordValid();
    }

    //region ================= Login =================

    @Bindable
    public String getLogin() {
        return login;
    }

    @Bindable
    public boolean isLoginValid() {
        return !TextUtils.isEmpty(login) && Pattern.matches(Constants.LOGIN_PATTERN, login);
    }

    public void setLogin(String login) {
        this.login = login;
        notifyChange();
    }

    //endregion

    //region ================= Email =================

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

    //endregion

    //region ================= Name =================

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public boolean isNameValid() {
        return !TextUtils.isEmpty(name) && Pattern.matches(Constants.NAME_PATTERN, name);
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    //endregion

    //region ================= Password =================

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public boolean isPasswordValid() {
        return !TextUtils.isEmpty(password)&& Pattern.matches(Constants.PASSWORD_PATTERN, password);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }

    //endregion
}