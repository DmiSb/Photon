package ru.dmisb.photon.core;

import android.content.Intent;

public interface IActivityResultView {
    void onActivityResult(int requestCode, Intent data);
}
