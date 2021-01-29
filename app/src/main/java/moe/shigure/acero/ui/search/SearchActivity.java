package moe.shigure.acero.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;
import moe.shigure.acero.bean.BookSimpleInfo;
import moe.shigure.acero.bean.NHentaiSearchResult;
import moe.shigure.acero.ui.detail.BookDetailActivity;
import moe.shigure.acero.ui.read.BookReadActivity;
import moe.shigure.acero.utils.ToastUtils;
import moe.shigure.acero.utils.glide.GlideImageModelProvider;

/**
 * Created by Shigure on 2020/11/4
 **/

public class SearchActivity extends BaseActivity implements SearchContract.ISearchView, View.OnClickListener {

    @InjectPresenter
    private SearchPresenter mPresenter;

    private LinearLayout ll_search_title;
    private EditText et_search_text;
    private TextView tv_search_random;
    private TextView tv_search_go;
    private RadioGroup rg_switch_engine;
    private SmartRefreshLayout srl_content_load;
    private RecyclerView rv_content;

    private ArrayList<BookSimpleInfo> contentItems;
    private MultiTypeAdapter contentAdapter;
    private ArrayList<String> thumbImageUrlList;

    private int engineType = 0;
    private String keyWord;
    private int searchPage = 1;

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        ll_search_title = $(R.id.ll_search_title);
        et_search_text = $(R.id.et_search_text);
        tv_search_random = $(R.id.tv_search_random);
        tv_search_go = $(R.id.tv_search_go);
        rg_switch_engine = $(R.id.rg_switch_engine);
        srl_content_load = $(R.id.srl_content_load);
        rv_content = $(R.id.rv_content);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBarMarginTop(ll_search_title)
                .init();

        tv_search_random.setOnClickListener(this);
        tv_search_go.setOnClickListener(this);

        rg_switch_engine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_nhentai:
                        engineType = 0;
                        break;
                    case R.id.rb_exhentai:
                        engineType = 1;
                        break;
                }
            }
        });

        contentItems = new ArrayList<>();
        contentAdapter = new MultiTypeAdapter(contentItems);
        thumbImageUrlList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_content.setLayoutManager(linearLayoutManager);
        contentAdapter.register(BookSimpleInfo.class, new SearchResultBinder(this));
        rv_content.setAdapter(contentAdapter);

        //然后把RecyclerViewPreloader添加到recyclerview里就可以了
        ListPreloader.PreloadSizeProvider sizeProvider = new FixedPreloadSizeProvider(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        ListPreloader.PreloadModelProvider modelProvider = new GlideImageModelProvider(this,thumbImageUrlList);
        RecyclerViewPreloader<String> preLoader = new RecyclerViewPreloader<>(
                Glide.with(this), modelProvider, sizeProvider, 3);//这里的3就是预加载的数量
        rv_content.addOnScrollListener(preLoader);

        srl_content_load.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(contentItems.size()%25 == 0) {
                    mPresenter.getSearchResult(keyWord, searchPage++);
                } else {
                    srl_content_load.finishLoadMore();
                }
            }
        });

    }

    @Override
    public void refreshSearchResult(NHentaiSearchResult searchResult, ArrayList<BookSimpleInfo> bookSimpleInfos){
        if(searchResult.getKeyWord().equals(keyWord)){
            for (BookSimpleInfo model : bookSimpleInfos){
                thumbImageUrlList.add(model.getCover());
            }
            contentItems.addAll(bookSimpleInfos);
            contentAdapter.notifyDataSetChanged();
            if(searchResult.getPage()<=1) {
                rv_content.smoothScrollToPosition(0);
            }
        }
        srl_content_load.finishLoadMore();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search_random:
                Intent intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("random", true);
                startActivity(intent);
                break;
            case R.id.tv_search_go:
                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                if(engineType==0) {
                    thumbImageUrlList.clear();
                    contentItems.clear();
                    keyWord = et_search_text.getText().toString();
                    if(!keyWord.isEmpty()) {
                        searchPage = 1;
                        mPresenter.getSearchResult(keyWord, searchPage++);
                        ToastUtils.showShortToast("正在搜索...");
                    } else {
                        ToastUtils.showShortToast("关键词不能为空ヾ(･ω･`｡)");
                    }
                } else {
                    ToastUtils.showShortToast("这个引擎暂时不能用ヾ(･ω･`｡)");
                }
                break;

        }
    }
}
