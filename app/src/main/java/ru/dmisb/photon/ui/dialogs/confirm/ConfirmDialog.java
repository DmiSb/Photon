package ru.dmisb.photon.ui.dialogs.confirm;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import io.reactivex.Observable;
import ru.dmisb.photon.R;

public class ConfirmDialog {

    public static Observable<Boolean> showDialog(Context context, @StringRes int confirmMessageId) {
        return Observable.create(e -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            AlertDialog confirmDialog = dialogBuilder
                    .setMessage(confirmMessageId)
                    .setCancelable(true)
                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                        e.onNext(true);
                        dialog.dismiss();
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        e.onNext(false);
                        dialog.dismiss();
                    })
                    .create();

            confirmDialog.setCanceledOnTouchOutside(true);
            confirmDialog.show();
        });
    }
}
