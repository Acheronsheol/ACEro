package moe.shigure.acero.views.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;//声明间距 //使用构造函数定义间距

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //获得当前item的位置
        int position = parent.getChildAdapterPosition(view);
        //根据position确定item需要留出的位置
        switch (position % 2) {
            case 0:
                //位于左侧的item
                outRect.right = this.space;
                break;
            case 1:
                //位于右侧的item
                outRect.left = this.space;
                break;
            default:
                break;
        }
        //设置底部边距
        outRect.bottom = this.space;
    }
}