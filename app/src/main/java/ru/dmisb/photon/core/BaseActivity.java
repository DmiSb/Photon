package ru.dmisb.photon.core;

import android.app.ProgressDialog;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import ru.dmisb.photon.R;

@SuppressWarnings("unused")
public abstract class BaseActivity<B extends ViewDataBinding>
        extends AppCompatActivity implements IBarView {

    protected B viewDataBinding;
    private ProgressDialog progressDialog;
    protected int actionBarSize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(@StringRes int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.CustomDialog);
            progressDialog.setCancelable(false);
            Window window = progressDialog.getWindow();
            if (window != null)
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.show();
        progressDialog.setContentView(R.layout.dialog_progress);
    }

    public void hideProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.hide();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
