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

import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.android.pictureinpicture.widget.MovieView;

import java.util.ArrayList;

/** Demonstrates usage of Picture-in-Picture mode on phones and tablets. */
public class MainActivity extends AppCompatActivity {

    /** Intent action for media controls from Picture-in-Picture mode. */
    private static final String ACTION_MEDIA_CONTROL = "media_control";

    /** Intent extra for media controls from Picture-in-Picture mode. */
    private static final String EXTRA_CONTROL_TYPE = "control_type";

    /** The request code for play action PendingIntent. */
    private static final int REQUEST_PLAY = 1;

    /** The request code for pause action PendingIntent. */
    private static final int REQUEST_PAUSE = 2;

    /** The request code for info action PendingIntent. */
    private static final int REQUEST_INFO = 3;

    /** The intent extra value for play action. */
    private static final int CONTROL_TYPE_PLAY = 1;

    /** The intent extra value for pause action. */
    private static final int CONTROL_TYPE_PAUSE = 2;

    /** The arguments to be used for Picture-in-Picture mode. */
    private final PictureInPictureParams.Builder mPictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();

    /** This shows the video. */
    private MovieView mMovieView;

    /** The bottom half of the screen; hidden on landscape */
    private ScrollView mScrollView;

    /** A {@link BroadcastReceiver} to receive action item events from Picture-in-Picture mode. */
    private BroadcastReceiver mReceiver;

    private String mPlay;
    private String mPause;

    private final View.OnClickListener mOnClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.pip:
                            L.d();
                            minimize();
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
                    // We are playing the video now. In PiP mode, we want to show an action item to
                    // pause
                    // the video.
                    updatePictureInPictureActions(
                            R.drawable.ic_pause_24dp, mPause, CONTROL_TYPE_PAUSE, REQUEST_PAUSE);
                }

                @Override
                public void onMovieStopped() {
                    L.d();
                    // The video stopped or reached its end. In PiP mode, we want to show an action
                    // item to play the video.
                    updatePictureInPictureActions(
                            R.drawable.ic_play_arrow_24dp, mPlay, CONTROL_TYPE_PLAY, REQUEST_PLAY);
                }

                @Override
                public void onMovieMinimized() {
                    L.d();
                    // The MovieView wants us to minimize it. We enter Picture-in-Picture mode now.
                    minimize();
                }
            };

    /**
     * Update the state of pause/resume action item in Picture-in-Picture mode.
     *
     * @param iconId The icon to be used.
     * @param title The title text.
     * @param controlType The type of the action. either {@link #CONTROL_TYPE_PLAY} or {@link
     *     #CONTROL_TYPE_PAUSE}.
     * @param requestCode The request code for the {@link PendingIntent}.
     */
    void updatePictureInPictureActions(
            @DrawableRes int iconId, String title, int controlType, int requestCode) {
        L.d();
        final ArrayList<RemoteAction> actions = new ArrayList<>();

        // This is the PendingIntent that is invoked when a user clicks on the action item.
        // You need to use distinct request codes for play and pause, or the PendingIntent won't
        // be properly updated.
        final PendingIntent intent =
                PendingIntent.getBroadcast(
                        MainActivity.this,
                        requestCode,
                        new Intent(ACTION_MEDIA_CONTROL).putExtra(EXTRA_CONTROL_TYPE, controlType),
                        0);
        final Icon icon = Icon.createWithResource(MainActivity.this, iconId);
        actions.add(new RemoteAction(icon, title, title, intent));

        // Another action item. This is a fixed action.
        actions.add(
                new RemoteAction(
                        Icon.createWithResource(MainActivity.this, R.drawable.ic_info_24dp),
                        getString(R.string.info),
                        getString(R.string.info_description),
                        PendingIntent.getActivity(
                                MainActivity.this,
                                REQUEST_INFO,
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(getString(R.string.info_uri))),
                                0)));

        mPictureInPictureParamsBuilder.setActions(actions);

        // This is how you can update action items (or aspect ratio) for Picture-in-Picture mode.
        // Note this call can happen even when the app is not in PiP mode. In that case, the
        // arguments will be used for at the next call of #enterPictureInPictureMode.
        setPictureInPictureParams(mPictureInPictureParamsBuilder.build());
        L.d();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d();
        setContentView(R.layout.activity_main);
        L.d();

        // Prepare string resources for Picture-in-Picture actions.
        mPlay = getString(R.string.play);
        mPause = getString(R.string.pause);

        // View references
        mMovieView = findViewById(R.id.movie);
        L.d();
        mScrollView = findViewById(R.id.scroll);
        L.d();

        Button switchExampleButton = findViewById(R.id.switch_example);
        switchExampleButton.setText(getString(R.string.switch_media_session));
        switchExampleButton.setOnClickListener(new SwitchActivityOnClick());

        // Set up the video; it automatically starts.
        L.d();
        mMovieView.setMovieListener(mMovieListener);
        L.d();
        findViewById(R.id.pip).setOnClickListener(mOnClickListener);
        L.d();
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.d();
    }

    @Override
    protected void onStop() {
        L.d();
        // On entering Picture-in-Picture mode, onPause is called, but not onStop.
        // For this reason, this is the place where we should pause the video playback.
        mMovieView.pause();
        super.onStop();
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
    protected void onStart() {
        super.onStart();
        L.d();
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.d();
    }

    @Override
    public void onPictureInPictureModeChanged(
            boolean isInPictureInPictureMode, Configuration configuration) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, configuration);
        L.d();
        if (isInPictureInPictureMode) {
            // Starts receiving events from action items in PiP mode.
            mReceiver =
                    new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            L.d();
                            if (intent == null
                                    || !ACTION_MEDIA_CONTROL.equals(intent.getAction())) {
                                return;
                            }

                            // This is where we are called back from Picture-in-Picture action
                            // items.
                            final int controlType = intent.getIntExtra(EXTRA_CONTROL_TYPE, 0);
                            switch (controlType) {
                                case CONTROL_TYPE_PLAY:
                                    mMovieView.play();
                                    break;
                                case CONTROL_TYPE_PAUSE:
                                    mMovieView.pause();
                                    break;
                            }
                            L.d();
                        }
                    };
            registerReceiver(mReceiver, new IntentFilter(ACTION_MEDIA_CONTROL));
        } else {
            L.d();
            // We are out of PiP mode. We can stop receiving events from it.
            unregisterReceiver(mReceiver);
            mReceiver = null;
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

    /** Launches {@link MediaSessionPlaybackActivity} and closes this activity. */
    private class SwitchActivityOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            L.d();
            startActivity(new Intent(view.getContext(), MediaSessionPlaybackActivity.class));
            finish();
            L.d();
        }
    }
}
