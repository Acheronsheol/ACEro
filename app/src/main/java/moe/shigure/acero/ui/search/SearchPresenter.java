package moe.shigure.acero.ui.search;

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

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {

    private Disposable disposable;

    public void getSearchResult(String keyWord, int page){
        ApiRetrofit.getInstance().getNHentaiEngine(keyWord,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }
                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Log.d("SearchPresenter",jsonElement.toString());
                        JsonObject jsonObject = (JsonObject)jsonElement;
                        ACEroBean bean = new ACEroBean();
                        bean.fillFromJson(new JsonPack(jsonObject));
                        if(bean.isStatusBool()) {
                            NHentaiSearchResult searchResult = new NHentaiSearchResult().fillFromJson(bean.getData());
                            ArrayList<BookSimpleInfo> bookSimpleInfos = new ArrayList<>();
                            for (JsonPack model : searchResult.getBookList()) {
                                bookSimpleInfos.add(new BookSimpleInfo().fillFromJson(model));
                            }
                            getView().refreshSearchResult(searchResult, bookSimpleInfos);
                            if(searchResult.getPage()<=1) {
                                ToastUtils.showShortToast("搜索完成ヾ(･ω･`｡)");
                            } else {
                                ToastUtils.showShortToast("加载成功ヾ(･ω･`｡)");
                            }
                        } else {
                            ToastUtils.showLongToast(String.format("结果出现了问题\nStatusCode:%d\nMessage:%s\nDate:%s",bean.getStatusCode(),bean.getMessage(),bean.getDate()));
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

    @Override
    public void detach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            ToastUtils.showShortToast("取消了一个请求");
        }
        super.detach();
    }

}
