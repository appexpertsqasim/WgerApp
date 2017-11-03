package com.example.tae.wger.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.tae.wger.R;
import com.example.tae.wger.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class VideoFragment extends BaseFragment  {
    @BindView(R.id.videoview)
    VideoView video;
    private static int aux=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.video_fragment,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(this, view);
        Uri uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.test);
        video.setVideoURI(uri);
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        video.start();
        video.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioManager mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


                if(aux % 2 == 0){

                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);
                    aux++;


                } else {

                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    aux++;

                }


            }
        });
        super.onViewCreated(view, savedInstanceState);


    }

}
