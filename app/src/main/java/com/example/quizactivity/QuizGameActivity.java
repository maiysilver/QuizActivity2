package com.example.quizactivity;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

public class QuizGameActivity extends QuizActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
    }
}
