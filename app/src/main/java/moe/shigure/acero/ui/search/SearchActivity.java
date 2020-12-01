package moe.shigure.acero.ui.search;

import android.content.Context;
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

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;
import moe.shigure.acero.bean.BookSimpleInfo;
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
    private TextView tv_search_go;
    private RadioGroup rg_switch_engine;
    private RecyclerView rv_content;

    private Items contentItems;
    private MultiTypeAdapter contentAdapter;
    private ArrayList<String> thumbImageUrlList;

    private int engineType = 0;
    private String keyWord;

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        ll_search_title = $(R.id.ll_search_title);
        et_search_text = $(R.id.et_search_text);
        tv_search_go = $(R.id.tv_search_go);
        rg_switch_engine = $(R.id.rg_switch_engine);
        rv_content = $(R.id.rv_content);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBarMarginTop(ll_search_title)
                .init();

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

        contentItems = new Items();
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

    }

    @Override
    public void refreshSearchResult(ArrayList<BookSimpleInfo> bookSimpleInfos){
        thumbImageUrlList.clear();
        for (BookSimpleInfo model : bookSimpleInfos){
            thumbImageUrlList.add(model.getCover());
        }

        contentItems.clear();
        contentItems.addAll(bookSimpleInfos);
        contentAdapter.notifyDataSetChanged();
        rv_content.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search_go:

                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                if(engineType==0) {
                    keyWord = et_search_text.getText().toString();
                    if(!keyWord.isEmpty()) {
                        mPresenter.getSearchResult(keyWord, 1);
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
