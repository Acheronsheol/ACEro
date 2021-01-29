package moe.shigure.acero.ui.read;

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
import moe.shigure.acero.network.json.JsonPack;
import moe.shigure.acero.network.retrofit.ApiRetrofit;
import moe.shigure.acero.utils.ToastUtils;

/**
 * Created by Shigure on 2020/11/4
 **/

public class BookReadPresenter extends BasePresenter<BookReadContract.IMainView> implements BookReadContract.IMainPresenter {

    private Disposable disposable;

    public void getBookContent(String url){
        ApiRetrofit.getInstance().getUrlInfo(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }
                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Log.d("MainPresenter",jsonElement.toString());
                        JsonObject jsonObject = (JsonObject)jsonElement;
                        ACEroBean bean = new ACEroBean();
                        bean.fillFromJson(new JsonPack(jsonObject));
                        disposeData(bean);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void disposeData(ACEroBean bean){
        ArrayList<String> al = new ArrayList<>();
        for (JsonElement model : bean.getData().getJsonArray("images")){
            String url = model.getAsString();
            if(url.startsWith("/")){
                url = "https://ero.raxianch.moe" + url;
            }
            al.add(url);
        }
        getView().getPic(al);
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
