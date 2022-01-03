package moe.shigure.acero.network.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit extends BaseApiRetrofit {

    public NetUrlAPI netUrlAPI;
    public static ApiRetrofit mInstance;

    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        netUrlAPI = new Retrofit.Builder()
                .baseUrl("https://ero.raxianch.moe/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetUrlAPI.class);
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private RequestBody getRequestBody(Object obj) {
        String route = new Gson().toJson(obj);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
    }

    //获取详情信息
    public Observable<JsonElement> getUrlInfo(String url) {
        return netUrlAPI.getUrlInfo(url);
    }

    //随机漫画详情信息
    public Observable<JsonElement> getRandomBookInfo() {
        return netUrlAPI.getRandomBookInfo();
    }

    //N-Hentai搜索引擎
    public Observable<JsonElement> getNHentaiEngine(String keyWord, int page) {
        return netUrlAPI.getNHentaiEngine(keyWord, page);
    }


}
