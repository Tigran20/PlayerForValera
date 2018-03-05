package com.valeriipopov.android.musicapp.musiclist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.valeriipopov.android.musicapp.Music;
import com.valeriipopov.android.musicapp.MusicAdapter;
import com.valeriipopov.android.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by werk on 01/03/2018.
 */

public class MusicListFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView listView;

    private Unbinder unbinder;
    private MusicAdapter adapter;
    private List<Music> musicList;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_music_list, container, false);
        context = getContext();
        unbinder = ButterKnife.bind(this, rootView);

        musicList = new ArrayList<>();

        GetAllMediaMp3Files();
        adapter = new MusicAdapter(getActivity(), musicList);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    public void GetAllMediaMp3Files() {
        ContentResolver contentResolver;
        Uri uri;
        Cursor cursor;


        contentResolver = context.getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentResolver.query(
                uri, // Uri
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            Toast.makeText(getContext(), "Something Went Wrong.", Toast.LENGTH_LONG).show();
        } else if (!cursor.moveToFirst()) {
            Toast.makeText(getContext(), "No Music Found on SD Card.", Toast.LENGTH_LONG).show();
        } else {

            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int Artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

            do {

                int SongID = cursor.getInt(id);
                Uri finalSuccessfulUri = Uri.withAppendedPath(uri, "" + SongID);

                String SongTitle = cursor.getString(Title);
                String SongArtist = cursor.getString(Artist);


                musicList.add(new Music(SongTitle, SongArtist, finalSuccessfulUri));


            } while (cursor.moveToNext());
        }
    }
}
