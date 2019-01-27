/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.pictureinpicture;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.android.pictureinpicture.widget.MovieView;

/**
 * Demonstrates usage of Picture-in-Picture when using {@link
 * android.support.v4.media.session.MediaSessionCompat}.
 */
public class MediaSessionPlaybackActivity extends AppCompatActivity {

    private static final String TAG = "MediaSessionPlaybackActivity";

    public static final long MEDIA_ACTIONS_PLAY_PAUSE =
            PlaybackStateCompat.ACTION_PLAY
                    | PlaybackStateCompat.ACTION_PAUSE
                    | PlaybackStateCompat.ACTION_PLAY_PAUSE;

    public static final long MEDIA_ACTIONS_ALL =
            MEDIA_ACTIONS_PLAY_PAUSE
                    | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                    | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS;

    private MediaSessionCompat mSession;

    /** The arguments to be used for Picture-in-Picture mode. */
    private final PictureInPictureParams.Builder mPictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();

    /** This shows the video. */
    private MovieView mMovieView;

    /** The bottom half of the screen; hidden on landscape */
    private ScrollView mScrollView;

    private final View.OnClickListener mOnClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    L.d();
                    switch (view.getId()) {
                        case R.id.pip:
                            L.d();
                            minimize();
                            L.d();
                            break;
                    }
                }
            };

    /** Callbacks from the {@link MovieView} showing the video playback. */
    private MovieView.MovieListener mMovieListener =
            new MovieView.MovieListener() {

                @Override
                public void onMovieStarted() {
                    L.d();
                    // We are playing the video now. Update the media session state and the PiP
                    // window will
                    // update the actions.
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PLAYING,
                            mMovieView.getCurrentPosition(),
                            mMovieView.getVideoResourceId());
                    L.d();
                }

                @Override
                public void onMovieStopped() {
                    L.d();
                    // The video stopped or reached its end. Update the media session state and the
                    // PiP window will
                    // update the actions.
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PAUSED,
                            mMovieView.getCurrentPosition(),
                            mMovieView.getVideoResourceId());
                    L.d();
                }

                @Override
                public void onMovieMinimized() {
                    L.d();
                    // The MovieView wants us to minimize it. We enter Picture-in-Picture mode now.
                    minimize();
                    L.d();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d();
        setContentView(R.layout.activity_main);

        // View references
        mMovieView = findViewById(R.id.movie);
        mScrollView = findViewById(R.id.scroll);
        Button switchExampleButton = findViewById(R.id.switch_example);
        switchExampleButton.setText(getString(R.string.switch_custom));
        switchExampleButton.setOnClickListener(new SwitchActivityOnClick());

        // Set up the video; it automatically starts.
        mMovieView.setMovieListener(mMovieListener);
        findViewById(R.id.pip).setOnClickListener(mOnClickListener);
        L.d();
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d();
        initializeMediaSession();
        L.d();
    }

    private void initializeMediaSession() {
        L.d();
        mSession = new MediaSessionCompat(this, TAG);
        mSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
                        | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mSession.setActive(true);
        MediaControllerCompat.setMediaController(this, mSession.getController());

        MediaMetadataCompat metadata = new MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, mMovieView.getTitle())
                .build();
        mSession.setMetadata(metadata);

        MediaSessionCallback mMediaSessionCallback = new MediaSessionCallback(mMovieView);
        mSession.setCallback(mMediaSessionCallback);

        int state =
                mMovieView.isPlaying()
                        ? PlaybackStateCompat.STATE_PLAYING
                        : PlaybackStateCompat.STATE_PAUSED;
        updatePlaybackState(
                state,
                MEDIA_ACTIONS_ALL,
                mMovieView.getCurrentPosition(),
                mMovieView.getVideoResourceId());
        L.d();
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d();
        // On entering Picture-in-Picture mode, onPause is called, but not onStop.
        // For this reason, this is the place where we should pause the video playback.
        mMovieView.pause();
        mSession.release();
        mSession = null;
        L.d();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d();
        if (!isInPictureInPictureMode()) {
            // Show the video controls so the video can be easily resumed.
            mMovieView.showControls();
        }
        L.d();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        L.d();
        adjustFullScreen(newConfig);
        L.d();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        L.d();
        if (hasFocus) {
            adjustFullScreen(getResources().getConfiguration());
        }
        L.d();
    }

    @Override
    public void onPictureInPictureModeChanged(
            boolean isInPictureInPictureMode, Configuration configuration) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, configuration);
        L.d();
        if (!isInPictureInPictureMode) {
            // Show the video controls if the video is not playing
            if (mMovieView != null && !mMovieView.isPlaying()) {
                mMovieView.showControls();
            }
        }
        L.d();
    }

    /** Enters Picture-in-Picture mode. */
    void minimize() {
        L.d();
        if (mMovieView == null) {
            return;
        }
        // Hide the controls in picture-in-picture mode.
        mMovieView.hideControls();
        // Calculate the aspect ratio of the PiP screen.
        Rational aspectRatio = new Rational(mMovieView.getWidth(), mMovieView.getHeight());
        mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());
        L.d();
    }

    /**
     * Adjusts immersive full-screen flags depending on the screen orientation.
     *
     * @param config The current {@link Configuration}.
     */
    private void adjustFullScreen(Configuration config) {
        L.d();
        final View decorView = getWindow().getDecorView();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            mScrollView.setVisibility(View.GONE);
            mMovieView.setAdjustViewBounds(false);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            mScrollView.setVisibility(View.VISIBLE);
            mMovieView.setAdjustViewBounds(true);
        }
        L.d();
    }

    /**
     * Overloaded method that persists previously set media actions.
     *
     * @param state The state of the video, e.g. playing, paused, etc.
     * @param position The position of playback in the video.
     * @param mediaId The media id related to the video in the media session.
     */
    private void updatePlaybackState(
            @PlaybackStateCompat.State int state, int position, int mediaId) {
        L.d();
        long actions = mSession.getController().getPlaybackState().getActions();
        updatePlaybackState(state, actions, position, mediaId);
        L.d();
    }

    private void updatePlaybackState(
            @PlaybackStateCompat.State int state, long playbackActions, int position, int mediaId) {
        L.d();
        PlaybackStateCompat.Builder builder =
                new PlaybackStateCompat.Builder()
                        .setActions(playbackActions)
                        .setActiveQueueItemId(mediaId)
                        .setState(state, position, 1.0f);
        mSession.setPlaybackState(builder.build());
        L.d();
    }

    /**
     * Updates the {@link MovieView} based on the callback actions. <br>
     * Simulates a playlist that will disable actions when you cannot skip through the playlist in a
     * certain direction.
     */
    private class MediaSessionCallback extends MediaSessionCompat.Callback {

        private static final int PLAYLIST_SIZE = 2;

        private MovieView movieView;
        private int indexInPlaylist;

        public MediaSessionCallback(MovieView movieView) {
            L.d();
            this.movieView = movieView;
            indexInPlaylist = 1;
            L.d();
        }

        @Override
        public void onPlay() {
            super.onPlay();
            L.d();
            movieView.play();
            L.d();
        }

        @Override
        public void onPause() {
            super.onPause();
            L.d();
            movieView.pause();
            L.d();
        }

        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
            L.d();
            movieView.startVideo();
            if (indexInPlaylist < PLAYLIST_SIZE) {
                indexInPlaylist++;
                if (indexInPlaylist >= PLAYLIST_SIZE) {
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PLAYING,
                            MEDIA_ACTIONS_PLAY_PAUSE | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS,
                            movieView.getCurrentPosition(),
                            movieView.getVideoResourceId());
                } else {
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PLAYING,
                            MEDIA_ACTIONS_ALL,
                            movieView.getCurrentPosition(),
                            movieView.getVideoResourceId());
                }
            }
            L.d();
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
            L.d();
            movieView.startVideo();
            if (indexInPlaylist > 0) {
                indexInPlaylist--;
                if (indexInPlaylist <= 0) {
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PLAYING,
                            MEDIA_ACTIONS_PLAY_PAUSE | PlaybackStateCompat.ACTION_SKIP_TO_NEXT,
                            movieView.getCurrentPosition(),
                            movieView.getVideoResourceId());
                } else {
                    updatePlaybackState(
                            PlaybackStateCompat.STATE_PLAYING,
                            MEDIA_ACTIONS_ALL,
                            movieView.getCurrentPosition(),
                            movieView.getVideoResourceId());
                }
            }
            L.d();
        }
    }

    private class SwitchActivityOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            L.d();
            startActivity(new Intent(view.getContext(), MainActivity.class));
            L.d();
            finish();
        }
    }
}
