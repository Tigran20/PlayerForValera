package com.valeriipopov.android.musicapp;

import android.net.Uri;

/**
 * Created by werk on 05/03/2018.
 */

public class Music {

    private String performer;
    private String title;
    private Uri audioResourceId;

    public Music (String performer, String title, Uri audioResourceId){
        this.performer = performer;
        this.title = title;
        this.audioResourceId = audioResourceId;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getAudioResourceId() {
        return audioResourceId;
    }
}
