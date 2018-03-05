package com.valeriipopov.android.musicapp.musiclist;

import android.support.v4.app.Fragment;

import com.valeriipopov.android.musicapp.R;
import com.valeriipopov.android.musicapp.SingleFragmentActivity;

public class MusicListActivity extends SingleFragmentActivity {



    @Override
    public int getLayoutRes() {
        return R.layout.activity_music_list;
    }

    @Override
    public Fragment createFragment() {
        return MusicListFragment.newInstance();
    }

}
