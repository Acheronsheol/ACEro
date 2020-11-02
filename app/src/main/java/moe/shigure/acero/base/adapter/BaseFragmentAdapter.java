package moe.shigure.acero.base.adapter;

import moe.shigure.acero.base.view.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    ArrayList<BaseFragment> mFragments;

    public BaseFragmentAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> mFragments) {
        super(fragmentManager);
        this.mFragments = mFragments;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return Math.max(mFragments.size(), 0);
    }
}
