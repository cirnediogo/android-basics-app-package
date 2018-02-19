package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Diogo on 30/05/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColor = 0;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mMediaCompletionListener;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private AudioManager mAudioManager;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        mMediaCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };
        mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer();
                }
                else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.start();
                        mMediaPlayer.setOnCompletionListener(mMediaCompletionListener);
                    }
                }
            }
        };
    }

    public void setColor(int color) { this.mColor = color; }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID word_image_view
        ImageView wordImageView = (ImageView) listItemView.findViewById(R.id.word_image_view);

        if (currentWord.getImageSource() != 0) {
            // Get the image from the current Word object and
            // set this image on the wordImageView
            wordImageView.setImageResource(currentWord.getImageSource());
            wordImageView.setVisibility(View.VISIBLE);
        } else {
            // Hide ImageView
            wordImageView.setVisibility(View.GONE);
        }

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok translation from the current Word object and
        // set this text on the miwokTextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the current Word object and
        // set this text on the defaultTextView
        defaultTextView.setText(currentWord.getDefaultTranslation());


        if (mColor != 0) {
            LinearLayout wordTextGroup = (LinearLayout) listItemView.findViewById(R.id.word_text_group);
            wordTextGroup.setBackgroundColor(ContextCompat.getColor(getContext(),mColor));
        }

        // Adding the audio playback - wrong way
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaPlayer != null) {
//                    mediaPlayer.reset();
//                }
//                mediaPlayer = MediaPlayer.create(getContext(), currentWord.getMediaSource());
//                mediaPlayer.start();
//            }
//        });

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;


        //Adapted from:
        //github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java
    }

    public void playMedia(int position) {
        releaseMediaPlayer();
        if (requestAudioFocus()) {
            mMediaPlayer = MediaPlayer.create(getContext(), getItem(position).getMediaSource());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mMediaCompletionListener);
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;


            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    private boolean requestAudioFocus() {

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Start playback
            return true;
        } else {
            Toast.makeText(getContext(), "Ooops! Audio device is busy.", Toast.LENGTH_SHORT);
        }
        return false;
    }

}
