package com.example.firidgeapp.fragment2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter2 extends FragmentStateAdapter {

    public int mCount;

    public MyAdapter2(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

            if(index==0) return new Fragment_6();
            else if(index==1) return new Fragment_5();
            else if(index==2) return new Fragment_7();
            else return new Fragment_8();

    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}
