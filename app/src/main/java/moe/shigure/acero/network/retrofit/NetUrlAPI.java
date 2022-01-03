package moe.shigure.acero.network.retrofit;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetUrlAPI {

    String BASE_URL = "https://ero.raxianch.moe/";

    @GET("{url}")
    Observable<JsonElement> getUrlInfo(@Path(value="url",encoded = true) String url);

    @GET("/ero/nh/search")
    Observable<JsonElement> getNHentaiEngine(@Query("q") String keyWord, @Query("page") int page);

    @GET("/ero/nh/random")
    Observable<JsonElement> getRandomBookInfo();

}
