package com.example.quizactivity;

import androidx.annotation.RequiresApi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;

public class QuizSplashActivity extends QuizActivity {

    private TextView titulo;
    private TextView subtitulo;
    private TextView versionn;
    private ImageView logo;
    private Animation animation_titulo;
    private Animation animation_titulo2;
    private Animation animation_logo;
    public static final String LAST_LAUNCH = "GamePrefs";
    private static final String TAG = "fechaHora";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        leerPreferencias();
        aparicionTitulos();
        animCustomLogo();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void leerPreferencias(){
        SharedPreferences lastTime = getSharedPreferences(LAST_LAUNCH, MODE_PRIVATE);
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat ("EEE dd-MMM-yyyy  HH:mm:ss zzz ", Locale.ENGLISH);
        if (lastTime.contains("dateTime")) {
            String dateTime = lastTime.getString("dateTime", "Default");
            Log.i(TAG, "Last app launch: " + dateTime);
        }
        SharedPreferences.Editor dateEditor = lastTime.edit();
        dateEditor.putString("dateTime",format.format(now));
        dateEditor.commit();
    }

    public void aparicionTitulos(){
        titulo = findViewById(R.id.titulin);
        subtitulo = findViewById(R.id.subtitulin);
        versionn = findViewById(R.id.version_);
        animation_titulo = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animation_titulo2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        titulo.startAnimation(animation_titulo);
        subtitulo.startAnimation(animation_titulo);
        versionn.startAnimation(animation_titulo2);
    }

    public void animCustomLogo(){
        logo = findViewById(R.id.logo);
        animation_logo = AnimationUtils.loadAnimation(this, R.anim.custom);
        animation_logo.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(QuizSplashActivity.this, QuizMenuActivity.class));
                QuizSplashActivity.this.finish();
            }
            public void onAnimationRepeat(Animation animation) { }
        });
        logo.startAnimation(animation_logo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        titulo = findViewById(R.id.titulin);
        titulo.clearAnimation();
        subtitulo = findViewById(R.id.subtitulin);
        subtitulo.clearAnimation();
        logo = findViewById(R.id.logo);
        logo.clearAnimation();
        versionn = findViewById(R.id.version_);
        versionn.clearAnimation();
    }
}
