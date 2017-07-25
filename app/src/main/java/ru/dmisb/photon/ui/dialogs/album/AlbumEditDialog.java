package ru.dmisb.photon.ui.dialogs.album;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.dmisb.photon.R;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.network.req.AlbumReq;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.repo.Repository;
import ru.dmisb.photon.databinding.DialogAlbumBinding;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.di.modules.RootModule;
import ru.dmisb.photon.flow.ScreenScoper;

public class AlbumEditDialog extends AlertDialog {

    @Inject
    Repository repository;

    protected AlbumEditDialog(@NonNull Context context) {
        super(context);
        initComponent();
    }

    public static Observable<AlbumRes> showDialog(Context context, @Nullable AlbumDto album) {
        return Observable.create(e -> {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_album, null);
            DialogAlbumBinding binding = DataBindingUtil.bind(view);
            AlbumViewModel viewModel = new AlbumViewModel();
            viewModel.setName(album.getTitle());
            viewModel.setDescription(album.getDescription());
            binding.setModel(viewModel);

            AlbumEditDialog dialog = new AlbumEditDialog(context);
            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setTitle(context.getResources().getString(R.string.album_edit_title));

            if (album != null) {
                binding.albumNameEd.setText(album.getTitle());
                binding.albumDescriptionEd.setText(album.getDescription());
            }

            binding.albumOk.setOnClickListener(v -> {
                if (viewModel.isNameValid() && viewModel.isDescriptionValid()) {
                    dialog.repository
                            .updateAlbumFromApi(
                                    album.getId(),
                                    new AlbumReq("", viewModel.getName(), "", viewModel.getDescription())
                            )
                            .subscribe(
                                    albumRes -> {
                                        dialog.dismiss();
                                        e.onNext(albumRes);
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
            binding.albumCancel.setOnClickListener(v -> dialog.cancel());
            dialog.show();
        });
    }

    //region ================= DI =================

    private void initComponent() {
        RootComponent rootComponent = ScreenScoper.getComponent(ScreenScoper.ROOT_SCOPE_NAME);
        if (rootComponent != null) {
            Component component = rootComponent.plusAlbumEditComponent();
            if (component != null)
                component.inject(this);
        }
    }

    @dagger.Subcomponent(modules = RootModule.class)
    @DaggerScope(AlbumEditDialog.class)
    public interface Component {
        void inject(AlbumEditDialog dialog);
    }

    //endregion
}
