package moe.shigure.acero.base.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import moe.shigure.acero.R;
import moe.shigure.acero.base.proxy.ProxyActivity;
import moe.shigure.acero.utils.ActivityUtils;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProxyActivity mProxyActivity;

    @LayoutRes
    protected abstract int initLayout();

    protected abstract void initViews();

    protected abstract void initData();


    @SuppressWarnings("SameParameterValue")
    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.activity_create_in, R.anim.activity_create_out);
        ActivityUtils.getActivityList().add(this);
        super.onCreate(savedInstanceState);

        setContentView(initLayout());

        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();

        initViews();
        initData();
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
        ActivityUtils.getActivityList().remove(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
