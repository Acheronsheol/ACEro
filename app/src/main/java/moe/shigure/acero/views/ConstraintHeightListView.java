package moe.shigure.acero.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by wang on 2020/9/8
 * 约束最大高度的ListView
 **/

public class ConstraintHeightListView extends ListView {
    private float mMaxHeight = -1;

    public ConstraintHeightListView(Context context) {
        this(context, null);
    }

    public ConstraintHeightListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConstraintHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取lv本身高度
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        //限制高度小于lv高度,设置为限制高度
        if (mMaxHeight <= specSize && mMaxHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Float.valueOf(mMaxHeight).intValue(),
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setConstraintHeight(int dimenRes){
        mMaxHeight = getResources().getDimension(dimenRes);
//        invalidate();
    }

}
