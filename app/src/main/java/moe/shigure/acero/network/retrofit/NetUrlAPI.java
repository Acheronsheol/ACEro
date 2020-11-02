package moe.shigure.acero.network.retrofit;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetUrlAPI {

    @GET("http://test.cnsltx.com/mmm/buy99Gold?playerid=20015")
    Observable<ResponseBody> getCall(@Query("name") String name, @Query("name") String psd);

    @GET("httpServer/json/ms_chatlist_banner.json")
    Observable<JsonElement> getBanner();

}
