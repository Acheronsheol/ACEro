package moe.shigure.acero.ui.search;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;
import moe.shigure.acero.R;
import moe.shigure.acero.bean.BookInfo;

/**
 * Created by wang on 2020/9/15
 **/

public class SearchResultBinder extends ItemViewBinder<BookInfo, SearchResultBinder.ViewHolder> {

    Activity activity;

    public SearchResultBinder(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookInfo bookInfo) {
        Glide.with(activity)
                .load("https://ero.raxianch.moe"+ bookInfo.getCover())
                .placeholder(R.drawable.placeholder_pic_light)
                .fitCenter()
                .into(holder.iv_book_thumb);
        holder.tv_book_name.setText(bookInfo.getBookName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull private final ImageView iv_book_thumb;
        @NonNull private final TextView tv_book_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_book_thumb = itemView.findViewById(R.id.iv_book_thumb);
            this.tv_book_name = itemView.findViewById(R.id.tv_book_name);
        }
    }

}
