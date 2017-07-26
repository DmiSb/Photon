package ru.dmisb.photon.job;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import ru.dmisb.photon.data.network.req.PhotoCardReq;
import ru.dmisb.photon.utils.AppConfig;

public class SaveImageJob extends BaseJob {

    private final PhotoCardReq photoCardReq;
    private final String photoUri;

    public SaveImageJob(PhotoCardReq photoCardReq, String photoUri) {
        super(new Params(JobPriority.HEIGHT).requireNetwork().persist());
        this.photoCardReq = photoCardReq;
        this.photoUri = photoUri;
    }

    @Override
    public void onAdded() {
        // empty
    }

    @Override
    public void onRun() throws Throwable {
        File imageFile = new File(Uri.parse(photoUri).getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/from-data"), imageFile);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", imageFile.getName(), requestBody);

        repository.uploadImage(body)
                .subscribe(
                        imageRes -> {}
                );
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        // empty
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.createExponentialBackoff(runCount, AppConfig.INITIAL_BACK_OFF_IN_MS);
    }
}
