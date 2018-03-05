package com.valeriipopov.android.musicapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.valeriipopov.android.musicapp.musiclist.MusicListActivity;

/**
 * Created by werk on 01/03/2018.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();
    public static final int RUNTIME_PERMISSION_CODE = 7;
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidRuntimePermission();
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();
        }
    }

    public void AndroidRuntimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(SingleFragmentActivity.this);
                    alert_builder.setMessage("External Storage Permission is Required.");
                    alert_builder.setTitle("Please Grant Permission.");
                    alert_builder.setPositiveButton("OK", (dialogInterface, i) -> ActivityCompat.requestPermissions(
                            SingleFragmentActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            RUNTIME_PERMISSION_CODE
                    ));

                    alert_builder.setNeutralButton("Cancel", null);
                    AlertDialog dialog = alert_builder.create();
                    dialog.show();

                } else {
                    ActivityCompat.requestPermissions(
                            SingleFragmentActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            RUNTIME_PERMISSION_CODE
                    );
                }
            } else {
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RUNTIME_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
            }
        }
    }


}
