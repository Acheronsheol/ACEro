package moe.shigure.acero.ui.read;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;
import moe.shigure.acero.R;

/**
 * Created by wang on 2020/9/15
 **/

public class BookReadBinder extends ItemViewBinder<String, BookReadBinder.ViewHolder> {

    private Activity mActivity;
    private RecyclerView mRecyclerView;

    public BookReadBinder(Activity activity,RecyclerView recyclerView){
        this.mActivity = activity;
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_content, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull String path) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)mRecyclerView.getLayoutManager();
        if(linearLayoutManager.getOrientation()==RecyclerView.HORIZONTAL){
            holder.rl_pic_content.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            holder.rl_pic_content.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        holder.tv_count.setText(holder.getAdapterPosition()+1+"");
        holder.tv_count.setVisibility(View.VISIBLE);
        Glide.with(mActivity)
                .load(path)
                .placeholder(R.drawable.placeholder_read_pic)
                .fitCenter()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.tv_count.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.iv_pic);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull private final RelativeLayout rl_pic_content;
        @NonNull private final ImageView iv_pic;
        @NonNull private final TextView tv_count;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rl_pic_content = itemView.findViewById(R.id.rl_pic_content);
            this.iv_pic = itemView.findViewById(R.id.iv_pic);
            this.tv_count = itemView.findViewById(R.id.tv_count);
        }
    }

}
