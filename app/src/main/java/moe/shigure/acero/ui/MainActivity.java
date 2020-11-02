package moe.shigure.acero.ui;

import moe.shigure.acero.R;
import moe.shigure.acero.base.view.BaseActivity;

import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

}