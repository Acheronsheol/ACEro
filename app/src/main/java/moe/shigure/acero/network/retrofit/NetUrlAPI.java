package moe.shigure.acero.network.retrofit;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetUrlAPI {

    @GET("ero/nh/id/16")
    Observable<JsonElement> getBanner();

    @GET("/ero/nh/search")
    Observable<JsonElement> getNHentaiEngine(@Query("q") String keyWord, @Query("page") int page);

}
