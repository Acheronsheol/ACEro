package moe.shigure.acero.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import moe.shigure.acero.ui.read.BookReadActivity;

/**
 * Created by Shigure on 2020/12/1
 **/

public class GlideImageModelProvider implements ListPreloader.PreloadModelProvider<String> {

    private Context context;
    private ArrayList<String> imageUrlList;

    public GlideImageModelProvider(Context context, ArrayList<String> imageUrlList){
        this.context = context;
        if(imageUrlList!=null) {
            this.imageUrlList = imageUrlList;
        } else {
            this.imageUrlList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {//作用是返回地址
        //imageUrlList是你的图片地址列表
        if(position < imageUrlList.size()){
            //告诉RecyclerViewPreloader每个item项需要加载的图片url集合
            return imageUrlList.subList(position, position+1);
        }else {
            return imageUrlList.subList(imageUrlList.size() - 1, imageUrlList.size());
        }
    }

    @Nullable
    @Override
    public RequestBuilder<Drawable> getPreloadRequestBuilder(@NonNull String url) {
        //返回一个加载图片的RequestBuilder
        return Glide.with(context).load(url);
    }
}

