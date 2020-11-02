package moe.shigure.acero.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moe.shigure.acero.base.proxy.ProxyFragment;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment extends Fragment implements IBaseView {

    private ProxyFragment mProxyFragment;

    @LayoutRes
    protected abstract int initLayout();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    public abstract void initData();

    @SuppressWarnings("ConstantConditions")
    protected <T extends View> T $(@IdRes int viewId) {
        return this.getView().findViewById(viewId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);

        mProxyFragment = createProxyFragment();
        mProxyFragment.bindPresenter();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(savedInstanceState);
        initData();
    }

    private ProxyFragment createProxyFragment() {
        if (mProxyFragment == null) {
            return new ProxyFragment<>(this);
        }
        return mProxyFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxyFragment.unbindPresenter();
    }

    public void refreshData(){

    }

}