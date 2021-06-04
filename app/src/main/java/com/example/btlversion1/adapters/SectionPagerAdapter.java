package com.example.btlversion1.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btlversion1.fragments.AmthucFragment;
import com.example.btlversion1.fragments.CongngheFragment;
import com.example.btlversion1.fragments.ThethaoFragment;
import com.example.btlversion1.fragments.TrangchuFragment;

public class SectionPagerAdapter extends FragmentStatePagerAdapter {

    public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case 0:
                fragment = new TrangchuFragment();
                break;
            case 1:
                fragment = new CongngheFragment();
                break;
            case 2:
                fragment = new ThethaoFragment();
                break;
            case 3:
                fragment = new AmthucFragment();
                break;
                //Repla
            default: fragment = new TrangchuFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title= "Trang Chủ";
                break;
            case 1:
                title= "Công nghệ";
                break;
            case 2:
                title= "Thế Thao";
                break;
            case 3:
                title= "Ẩm Thực";
                break;
            default: title="Trang Chủ";
        }
        return title;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}