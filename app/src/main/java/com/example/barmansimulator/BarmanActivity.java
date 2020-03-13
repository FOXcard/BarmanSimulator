package com.example.barmansimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barmansimulator.camera.CameraActivity;

import java.util.Timer;
import java.util.TimerTask;

public class BarmanActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private AudioManager audioManager;

    // Maximumn sound stream.
    private static final int MAX_STREAMS = 5;

    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int soundDebase;
    private int soundCasseVert;
    private float volume;


    private Timer myTimerResulta;
    private Timer myTimerText;
    private TextView textView;
    private boolean aBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        textView=findViewById(R.id.textView);
       // startTimerResultat();
        startTimerText();
        setLoadedMusic();
        
    }













private void setLoadedMusic(){
    // AudioManager audio settings for adjusting the volume
    audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

    // Current volumn Index of particular stream type.
    float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

    // Get the maximum volume index for a particular stream type.
    float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

    // Volumn (0 --> 1)
    this.volume = currentVolumeIndex / maxVolumeIndex;

    // Suggests an audio stream whose volume should be changed by
    // the hardware volume controls.
    this.setVolumeControlStream(streamType);

    // For Android SDK >= 21
    if (Build.VERSION.SDK_INT >= 21 ) {

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        SoundPool.Builder builder= new SoundPool.Builder();
        builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

        this.soundPool = builder.build();
    }
    // for Android SDK < 21
    else {
        // SoundPool(int maxStreams, int streamType, int srcQuality)
        this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    }

    // When Sound Pool load complete.
    this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
        @Override
        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            loaded = true;
        }
    });

    // Load sound file (destroy.wav) into SoundPool.
    this.soundDebase = this.soundPool.load(this, R.raw.background,1);

    // Load sound file (gun.wav) into SoundPool.
    this.soundCasseVert = this.soundPool.load(this, R.raw.casse_vert,1);

}


    public void startTimerResultat() {
        myTimerResulta = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean take=false;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BarmanActivity.this, "Open camera", Toast.LENGTH_SHORT).show();
                        if(!take){
                            ouvrirCamera();
                            myTimerResulta.cancel();
                        }
                        else {
                            myTimerResulta.cancel();
                        }
                    }
                });
            }
        };
        myTimerResulta.scheduleAtFixedRate(timerTask, 2000, 2000);
    }
    public void startTimerText() {
        myTimerText = new Timer();
        TimerTask timerTask = new TimerTask() {
            boolean take=false;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        changeText();
                    }
                });
            }
        };
        myTimerText.scheduleAtFixedRate(timerTask, 1000, 5000);
    }

    // When users click on the button "Gun"
    public void playSoundGun()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of gunfire. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundCasseVert,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    // When users click on the button "Destroy"
    public void playSoundDestroy()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundDebase,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    private void changeText(){
        aBoolean=!aBoolean;
        if(aBoolean){
            textView.setText("Le risque");
            playSoundGun();
        }

        else {textView.setText("Position");
        playSoundDestroy();
        }
    }
    public void ouvrirCamera() {
        Intent intent = new Intent(BarmanActivity.this, CameraActivity.class);
        startActivity(intent);
    }


}
