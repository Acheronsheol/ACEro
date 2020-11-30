package moe.shigure.acero.ui.search;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_content.setLayoutManager(linearLayoutManager);
        contentAdapter.register(BookSimpleInfo.class, new SearchResultBinder(this));
        rv_content.setAdapter(contentAdapter);

    }

    @Override
    public void refreshSearchResult(ArrayList<BookSimpleInfo> bookSimpleInfos){
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
