package moe.shigure.acero.ui;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainContract.IMainView {

    @InjectPresenter
    private MainPresenter mainPresenter;

    private Items contentItems;
    private MultiTypeAdapter contentAdapter;

    private RecyclerView rv_content;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        rv_content = $(R.id.rv_content);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mainPresenter.getData();

        contentItems = new Items();
        contentAdapter = new MultiTypeAdapter(contentItems);
        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview
        contentAdapter.register(String.class, new MainContentBinder(this));
        rv_content.setAdapter(contentAdapter);
    }

    public void getPic(ArrayList<String> picStr){
        contentItems.addAll(picStr);
        contentAdapter.notifyDataSetChanged();
    }

}