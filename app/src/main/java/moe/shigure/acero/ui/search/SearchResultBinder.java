package moe.shigure.acero.ui.search;

import android.app.Activity;
import android.content.Intent;
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
import moe.shigure.acero.bean.BookSimpleInfo;
import moe.shigure.acero.ui.detail.BookDetailActivity;

/**
 * Created by wang on 2020/9/15
 **/

public class SearchResultBinder extends ItemViewBinder<BookSimpleInfo, SearchResultBinder.ViewHolder> {

    Activity mActivity;

    public SearchResultBinder(Activity activity){
        this.mActivity = activity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookSimpleInfo bookSimpleInfo) {

        Glide.with(mActivity)
                .load(bookSimpleInfo.getCover())
                .placeholder(R.drawable.placeholder_cover_pic)
                .into(holder.iv_book_thumb);
        holder.tv_book_name.setText(bookSimpleInfo.getBookName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, BookDetailActivity.class);
                intent.putExtra("url",bookSimpleInfo.getUrl());
                intent.putExtra("thumb",bookSimpleInfo.getCover());
                mActivity.startActivity(intent);
            }
        });

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
