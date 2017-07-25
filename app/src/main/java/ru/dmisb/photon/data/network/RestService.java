package ru.dmisb.photon.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.dmisb.photon.data.network.req.AlbumReq;
import ru.dmisb.photon.data.network.req.LoginReq;
import ru.dmisb.photon.data.network.req.RegisterReq;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.network.res.DeleteRes;
import ru.dmisb.photon.data.network.res.ImageRes;
import ru.dmisb.photon.data.network.res.PhotoCardRes;
import ru.dmisb.photon.data.network.res.UserRes;
import ru.dmisb.photon.utils.Constants;

@SuppressWarnings("unused")
public interface RestService {

    //region ================= PhotoCards =================

    @GET("photocard/list")
    Observable<Response<List<PhotoCardRes>>> getPhotoCardList(
            @Header(Constants.IF_MODIFIED_SINCE_HEADER) String lastEntityUpdate
    );

    @Multipart
    @POST("user/{userId}/image/upload")
    Observable<Response<ImageRes>> uploadImage(
            @Header(Constants.AUTHORIZATION_HEADER) String token,
            @Path("userId") String userId,
            @Part MultipartBody.Part file);

    //endregion

    //region ================= User =================

    @GET("user/{userId}")
    Observable<Response<UserRes>> getUserInfo(
            @Path("userId") String userId,
            @Header(Constants.IF_MODIFIED_SINCE_HEADER) String lastEntityUpdate

    );

    @POST("user/signIn")
    Observable<Response<UserRes>> signIn(
            @NonNull @Body LoginReq loginReq
    );

    @POST("user/signUp")
    Observable<Response<UserRes>> signUp(
            @NonNull @Body RegisterReq registerReq
    );

    @DELETE("user/{userId}")
    Observable<Response<Boolean>> deleteUser(
            @NonNull @Path("userId") String userId,
            @NonNull @Query("token") String token
    );

    //endregion

    //region ================= Album =================

    @POST("user/{userId}/album")
    Observable<Response<AlbumRes>> addAlbum(
            @NonNull @Path("userId") String userId,
            @NonNull @Header(Constants.AUTHORIZATION_HEADER) String token,
            @NonNull @Body AlbumReq album
    );

    @PUT("user/{userId}/album/{albumId}")
    Observable<Response<AlbumRes>> editAlbum(
            @NonNull @Path("userId") String userId,
            @NonNull @Path("albumId") String albumId,
            @NonNull @Header(Constants.AUTHORIZATION_HEADER) String token,
            @NonNull @Body AlbumReq album
    );

    @DELETE("user/{userId}/album/{albumId}")
    Observable<Response<DeleteRes>> deleteAlbum(
            @NonNull @Path("userId") String userId,
            @NonNull @Path("albumId") String albumId,
            @NonNull @Header(Constants.AUTHORIZATION_HEADER) String token
    );

    //endregion

    //region ================= TagCollection =================

    @GET("photocard/tags")
    Observable<Response<List<String>>> getTagCollection(
            @Header(Constants.IF_MODIFIED_SINCE_HEADER) String lastEntityUpdate
    );

    //endregion

}
