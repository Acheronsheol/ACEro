package moe.shigure.acero.ui.read;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.MultiTypeAdapter;
import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;
import moe.shigure.acero.utils.glide.GlideImageModelProvider;
import moe.shigure.acero.views.zoomlayout.ZoomLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

public class BookReadActivity extends BaseActivity implements BookReadContract.IMainView {

    @InjectPresenter
    private BookReadPresenter mPresenter;

    private boolean isHorizontal;

    private int currentItemPosition;

    private ArrayList<String> contentItems;
    private MultiTypeAdapter contentAdapter;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewPreloader<String> preLoader;
    private RecyclerView.OnScrollListener onScrollListener;

    private ZoomLayout zl_content;
    private RecyclerView rv_content;
    private TextView tv_page_number;

    @Override
    protected int initLayout() {
        return R.layout.activity_book_read;
    }

    @Override
    protected void initViews() {
        zl_content = $(R.id.zl_content);
        rv_content = $(R.id.rv_content);
        tv_page_number = $(R.id.tv_page_number);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mPresenter.getBookContent(getIntent().getStringExtra("url"));

        isHorizontal = getIntent().getBooleanExtra("orientation",false);

        tv_page_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zl_content.resetZoom();
                if (isHorizontal) {
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    rv_content.scrollToPosition(currentItemPosition-1);
                    new PagerSnapHelper().attachToRecyclerView(rv_content);
                } else {
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    if (rv_content.getOnFlingListener() != null) {
                        rv_content.setOnFlingListener(null);
                        rv_content.clearOnScrollListeners();
                        rv_content.addOnScrollListener(preLoader);
                        rv_content.addOnScrollListener(onScrollListener);
                    }
                }
                rv_content.setLayoutManager(linearLayoutManager);
                contentAdapter.notifyDataSetChanged();
                isHorizontal = !isHorizontal;
            }
        });

        contentItems = new ArrayList<>();
        contentAdapter = new MultiTypeAdapter(contentItems);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_content.setLayoutManager(linearLayoutManager);
        ListPreloader.PreloadSizeProvider sizeProvider = new FixedPreloadSizeProvider(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        ListPreloader.PreloadModelProvider modelProvider = new GlideImageModelProvider(this,contentItems);
        preLoader = new RecyclerViewPreloader<String>(Glide.with(this), modelProvider, sizeProvider, 6);//这里的6就是预加载的数量
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(linearLayoutManager.getOrientation()==RecyclerView.VERTICAL){
                    currentItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                } else {
                    currentItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                }
                if(currentItemPosition<0) return;
                currentItemPosition++;
                tv_page_number.setText(currentItemPosition+"/"+contentItems.size());
            }
        };
        rv_content.addOnScrollListener(preLoader);
        rv_content.addOnScrollListener(onScrollListener);
        new PagerSnapHelper().attachToRecyclerView(rv_content);
        contentAdapter.register(String.class, new BookReadBinder(this,rv_content));
        rv_content.setAdapter(contentAdapter);

        zl_content.setOnCurrentZoomChangedListener(new ZoomLayout.OnCurrentZoomChangedListener() {
            @Override
            public void onChangedZoomValue(float zoomValue) {
            }
            @Override
            public void OnZooming(boolean status) {
                if(linearLayoutManager.getOrientation()==RecyclerView.VERTICAL){
                    if(rv_content.getOnFlingListener()!=null){
                        rv_content.setOnFlingListener(null);
                        rv_content.clearOnScrollListeners();
                        rv_content.addOnScrollListener(preLoader);
                        rv_content.addOnScrollListener(onScrollListener);
                    }
                    return;
                }

                rv_content.setOnFlingListener(null);
                if (status) {
                    rv_content.clearOnScrollListeners();
                    rv_content.addOnScrollListener(preLoader);
                    rv_content.addOnScrollListener(onScrollListener);
                }else {
                    new PagerSnapHelper().attachToRecyclerView(rv_content);
                }
            }
        });

    }

    public void getPic(ArrayList<String> picStr){
        contentItems.addAll(picStr);
        contentAdapter.notifyDataSetChanged();
    }

}