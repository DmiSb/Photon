package ru.dmisb.photon.screens.album;

import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.flow.ScreenScoper;

public class AlbumModel extends BaseModel {

    AlbumRealm getAlbum(String id) {
        return repository.getAlbumFromStorage(id);
    }

    void updateAlbum(AlbumDto album) {
        repository.updateAlbum(album);
    }

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        AlbumScreen.Component component = ScreenScoper.getComponent(ScreenScoper.ALBUM_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion
}
