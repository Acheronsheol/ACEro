package moe.shigure.acero.network.retrofit;

import org.jetbrains.annotations.NotNull;

import moe.shigure.acero.app.ShigureApp;
import moe.shigure.acero.network.retrofit.persistentcookiejar.ClearableCookieJar;
import moe.shigure.acero.network.retrofit.persistentcookiejar.PersistentCookieJar;
import moe.shigure.acero.network.retrofit.persistentcookiejar.cache.SetCookieCache;
import moe.shigure.acero.network.retrofit.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import moe.shigure.acero.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @创建者 CSDN_LQR
 * @描述 配置Retrofit（配置网络缓存cache、配置持久cookie免登录）
 */

public class BaseApiRetrofit {

    private final OkHttpClient mClient;

    public OkHttpClient getClient() {
        return mClient;
    }

    public BaseApiRetrofit() {

        //cache
        File httpCacheDir = new File(ShigureApp.getContext().getExternalCacheDir(), "response");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        Cache cache = new Cache(httpCacheDir, cacheSize);

        //cookie
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(ShigureApp.getContext()));

        //OkHttpClient
        mClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(2500, TimeUnit.MILLISECONDS)
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
//                .addInterceptor(rewriteHeaderControlInterceptor)
//                .addInterceptor(rewriteCacheControlInterceptor)
//                .addInterceptor(new LoggingInterceptor())
//                .addInterceptor(loggingInterceptor)//设置 Debug Log 模式
                .cache(cache)
//                .cookieJar(cookieJar)
                .build();
    }

    //header配置

    //重写头部控制拦截器
    Interceptor rewriteHeaderControlInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cookie", "add cookies here")
                    .build();
            return chain.proceed(request);
        }
    };


    //重写缓存控制拦截器
    Interceptor rewriteCacheControlInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {


            //设置拦截器
            Request request = chain.request();
//            if (!NetUtils.isNetworkAvailable(ShigureApp.getContext())) {
//
//                //通过 CacheControl 控制缓存数据
//                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//                cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
//                cacheBuilder.maxStale(365, TimeUnit.DAYS);//这个是控制缓存的过时时间
//
//                CacheControl cacheControl = cacheBuilder.build();
//
//                request = request.newBuilder()
//                        .cacheControl(cacheControl)
//                        .build();
//
//            }

            Response originalResponse = chain.proceed(request);
            if (NetUtils.isNetworkAvailable(ShigureApp.getContext())) {//网络状态正常
                int maxAge = 60 * 5;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {//网络状态异常
                int maxStale = 60 * 60 * 24 * 28;//tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Prama")
                        .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };


    class LoggingInterceptor implements Interceptor {
        @NotNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
//            LogUtils.sf(String.format("发送请求 %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
//            LogUtils.sf(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
//                    response.request().url(),
//                    responseBody.string(),
//                    (t2 - t1) / 1e6d,
//                    response.headers()));
            return response;
        }
    }

}
