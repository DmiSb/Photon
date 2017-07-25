package ru.dmisb.photon.ui.helpers;

import android.support.annotation.StringRes;
import android.view.View;

public class ActionHolder {
    private @StringRes int messageResId;
    private @StringRes int actionTitleResId;
    private View.OnClickListener listener;

    public ActionHolder(@StringRes int messageResId, @StringRes int actionTitleResId, View.OnClickListener listener) {
        this.messageResId = messageResId;
        this.actionTitleResId = actionTitleResId;
        this.listener = listener;
    }

    public @StringRes int getMessageResId() {
        return messageResId;
    }

    public @StringRes int getActionTitleResId() {
        return actionTitleResId;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
