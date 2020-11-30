package moe.shigure.acero.ui.read;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

public class BookReadActivity extends BaseActivity implements BookReadContract.IMainView {

    @InjectPresenter
    private BookReadPresenter mPresenter;

    private Items contentItems;
    private MultiTypeAdapter contentAdapter;

    private RecyclerView rv_content;
    private TextView tv_page_number;

    @Override
    protected int initLayout() {
        return R.layout.activity_book_read;
    }

    @Override
    protected void initViews() {
        rv_content = $(R.id.rv_content);
        tv_page_number = $(R.id.tv_page_number);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mPresenter.getBookContent(getIntent().getStringExtra("url"));

        contentItems = new Items();
        contentAdapter = new MultiTypeAdapter(contentItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_content.setLayoutManager(linearLayoutManager);//这里用线性显示 类似于listview
        contentAdapter.register(String.class, new BookReadBinder(this));
        rv_content.setAdapter(contentAdapter);

        rv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()+1;
                tv_page_number.setText(firstVisibleItemPosition+"/"+contentItems.size());
                if (!recyclerView.canScrollVertically(1)){
                    tv_page_number.setText(contentItems.size()+"/"+contentItems.size());
                }
            }
        });

    }

    public void getPic(ArrayList<String> picStr){
        contentItems.addAll(picStr);
        contentAdapter.notifyDataSetChanged();
    }

}