package moe.shigure.acero.ui.read;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;
import moe.shigure.acero.R;

/**
 * Created by wang on 2020/9/15
 **/

public class BookReadBinder extends ItemViewBinder<String, BookReadBinder.ViewHolder> {

    Activity activity;

    public BookReadBinder(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_content, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull String string) {
        Glide.with(activity)
                .load("https://ero.raxianch.moe"+string)
                .placeholder(R.drawable.placeholder_pic_light)
                .fitCenter()
                .into(holder.iv_pic);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull private final ImageView iv_pic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }

}
