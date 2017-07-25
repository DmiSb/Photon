package ru.dmisb.photon.ui.dialogs.register;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.dmisb.photon.R;
import ru.dmisb.photon.data.network.req.RegisterReq;
import ru.dmisb.photon.data.repo.Repository;
import ru.dmisb.photon.databinding.DialogRegisterBinding;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.di.modules.RootModule;
import ru.dmisb.photon.flow.ScreenScoper;

public class RegisterDialog extends AlertDialog {
    @Inject
    Repository repository;

    public RegisterDialog(@NonNull Context context) {
        super(context);
        initComponent();
    }

    private void initComponent() {
        RootComponent rootComponent = ScreenScoper.getComponent(ScreenScoper.ROOT_SCOPE_NAME);
        if (rootComponent != null) {
            Component component = rootComponent.plusRegisterComponent();
            if (component != null)
                component.inject(this);
        }
    }

    public static Observable<Boolean> showDialog(Context context) {
        return Observable.create(e -> {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_register, null);
            DialogRegisterBinding binding = DataBindingUtil.bind(view);
            RegisterViewModel viewModel = new RegisterViewModel();
            binding.setModel(viewModel);

            RegisterDialog dialog = new RegisterDialog(context);
            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setTitle(context.getResources().getString(R.string.register_title));

            binding.registerOk.setOnClickListener(v -> {
                if (viewModel.isModelValid()) {
                    dialog.repository
                            .signUp(new RegisterReq(viewModel.getName(), viewModel.getLogin(),
                                    viewModel.getEmail(), viewModel.getPassword()))
                            .subscribe(
                                    aBoolean -> {
                                        dialog.dismiss();
                                        e.onNext(aBoolean);
                                    },
                                    throwable -> {
                                        if (throwable instanceof SocketTimeoutException)
                                            Toast.makeText(context.getApplicationContext(),
                                                    context.getString(R.string.err_server),
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                        else
                                            Toast.makeText(context.getApplicationContext(),
                                                    throwable.getMessage(),
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                    }
                            );
                }
            });

            binding.registerCancel.setOnClickListener(v -> {
                dialog.cancel();
                e.onNext(false);
            });

            dialog.show();
        });
    }

    //region ================= DI =================

    @dagger.Subcomponent(modules = RootModule.class)
    @DaggerScope(RegisterDialog.class)
    public interface Component {
        void inject(RegisterDialog dialog);
    }

    //endregion
}
