package ru.dmisb.photon.data.network;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import retrofit2.Response;
import ru.dmisb.photon.data.network.err.ErrorRes;
import ru.dmisb.photon.data.network.err.Errors;

public class RestCallTransformer<R> implements ObservableTransformer<Response<R>, R> {
    @Override
    public ObservableSource<R> apply(Observable<Response<R>> responseObservable) {
        return NetworkStatusChecker.isInternetAvailable()
                .flatMap(aBoolean -> aBoolean ? responseObservable :
                        Observable.error(new ErrorRes(Errors.NETWORK_NOT_AVAILABLE)))
                .flatMap(rResponse -> {
                    switch (rResponse.code()) {
                        case 200:
                        case 201:
                        case 202:
                            return Observable.just(rResponse.body());
                        case 204:
                            return Observable.empty();
                        case 304:
                            return Observable.empty();
                        case 404:
                            return Observable.error(new ErrorRes(Errors.USER_NOT_FOUND));
                        default:
                            return Observable.error(new ErrorRes(Errors.UNKNOWN_ERROR));
                    }
                });
    }
}
