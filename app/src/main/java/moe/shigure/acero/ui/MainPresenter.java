package moe.shigure.acero.ui;

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

/**
 * Created by Shigure on 2020/11/4
 **/

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {

    public void getData(){
        ApiRetrofit.getInstance().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(JsonElement jsonElement) {
                        JsonObject jsonObject = (JsonObject)jsonElement;
                        JsonPack jsonPack = new JsonPack(jsonObject);
                        ACEroBean bean = new ACEroBean();
                        bean.setStatusCode(jsonPack.getInt("status_code"))
                                .setStatusBool(jsonPack.getBoolean("status_bool"))
                                .setVersion(jsonPack.getInt("version"))
                                .setCostTime(jsonPack.getString("cost_time"))
                                .setDate(jsonPack.getString("date"))
                                .setTimeStamp(jsonPack.getDouble("ts"))
                                .setMessage(jsonPack.getString("message"))
                                .setData(jsonPack.getJsonPack("data"));
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
        for (int i = 0;i<50;i++){
            JsonElement model =  bean.getData().getJsonArray("images").get(i);
            al.add(model.getAsString());
        }
        getView().getPic(al);
    }

}
