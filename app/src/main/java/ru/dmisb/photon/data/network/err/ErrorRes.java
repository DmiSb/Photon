package ru.dmisb.photon.data.network.err;

public class ErrorRes extends Throwable {
    private Errors errorType;

    public ErrorRes(Errors errorType) {
        super(errorType.getCode());
        this.errorType = errorType;
    }

    public Errors getType() {
        return errorType;
    }
}
