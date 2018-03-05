package com.valeriipopov.android.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by werk on 05/03/2018.
 */

public class MusicAdapter extends ArrayAdapter<Music> {

    @BindView(R.id.music_performer)
    TextView musicPerformerTextView;

    @BindView(R.id.music_title)
    TextView musicTitleTextView;

    private Context context;
    private List<Music> musicList;

    public MusicAdapter(@NonNull Context context, List<Music> musicList) {
        super(context, 0, musicList);
        this.context = context;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemMusicView = convertView;
        if (itemMusicView == null) {
            itemMusicView = LayoutInflater.from(context).inflate(R.layout.item_music_list, parent, false);
        }
        ButterKnife.bind(this, itemMusicView);
        bindView(position);
        return itemMusicView;
    }

    private void bindView(int position) {
        Music music = musicList.get(position);
        musicPerformerTextView.setText(music.getPerformer());
        musicTitleTextView.setText(music.getTitle());
    }
}
