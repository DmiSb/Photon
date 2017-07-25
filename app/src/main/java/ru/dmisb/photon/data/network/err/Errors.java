package ru.dmisb.photon.data.network.err;

import ru.dmisb.photon.R;

public enum Errors {
    UNKNOWN_ERROR               ("unknown error",               R.string.err_unknown),
    NETWORK_NOT_AVAILABLE       ("network_not_available",       R.string.err_network_not_available),
    USER_NOT_FOUND              ("user_not_found",              R.string.err_user_not_found);


    private String code;
    private int messageResId;

    Errors(String code, int messageResId) {
        this.code = code;
        this.messageResId = messageResId;
    }

    public String getCode() {
        return code;
    }

    public static Errors getValueOf(String value) {
        Errors[] errors = Errors.values();
        for (Errors error : errors) {
            if (error.getCode().equals(value))
                return error;
        }
        return UNKNOWN_ERROR;
    }

    public static Errors getValueOf(Throwable e) {
        return getValueOf(e.getMessage());
    }

    public int getMessageResId() {
        return messageResId;
    }
}
