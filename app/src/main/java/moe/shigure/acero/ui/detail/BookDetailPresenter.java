package moe.shigure.acero.ui.detail;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moe.shigure.acero.base.presenter.BasePresenter;
import moe.shigure.acero.bean.ACEroBean;
import moe.shigure.acero.bean.BookDetailInfo;
import moe.shigure.acero.bean.BookSimpleInfo;
import moe.shigure.acero.bean.NHentaiSearchResult;
import moe.shigure.acero.network.json.JsonPack;
import moe.shigure.acero.network.retrofit.ApiRetrofit;
import moe.shigure.acero.utils.ToastUtils;

/**
 * Created by Shigure on 2020/11/4
 **/

public class BookDetailPresenter extends BasePresenter<BookDetailContract.IBookDetailView> implements BookDetailContract.IBookDetailPresenter {

    public void getBookDetailInfo(String url){
        ApiRetrofit.getInstance().getUrlInfo(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Log.d("BookDetailPresenter",jsonElement.toString());
                        JsonObject jsonObject = (JsonObject)jsonElement;
                        ACEroBean bean = new ACEroBean();
                        bean.fillFromJson(new JsonPack(jsonObject));
                        if(bean.isStatusBool()) {
                            getView().refreshBookDetailInfo(new BookDetailInfo().fillFromJson(bean.getData()));
                            ToastUtils.showShortToast("加载完成ヾ(･ω･`｡)");
                        } else {
                            ToastUtils.showLongToast(String.format("加载结果出现了问题\nStatusCode:%d\nMessage:%s\nDate:%s",bean.getStatusCode(),bean.getMessage(),bean.getDate()));
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.fillInStackTrace();
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void preloadBookContent(String url){
        ApiRetrofit.getInstance().getUrlInfo(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Log.d("BookDetailPresenter","预加载完成");
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.fillInStackTrace();
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

}
