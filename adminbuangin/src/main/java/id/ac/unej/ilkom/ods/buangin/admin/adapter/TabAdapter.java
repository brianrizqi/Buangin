package id.ac.unej.ilkom.ods.buangin.admin.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) stringList.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    public void tambahFragment(Fragment fragment, String judul) {
        fragmentList.add(fragment);
        stringList.add(judul);
    }
}
