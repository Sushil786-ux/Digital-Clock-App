package com.example.digitalclockapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvClock, tvDate;
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvClock = findViewById(R.id.tvClock);
        tvDate = findViewById(R.id.tvDate);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateClock();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
    private void updateClock() {
        String currentTime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());

        // Format date (e.g., Friday, October 16, 2026)
        String currentDate = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(new Date());

        tvClock.setText(currentTime);
        tvDate.setText(currentDate);

    }
    protected void onDestroy() {
        super.onDestroy();
        // Clean up handler callbacks to prevent memory leaks when activity closes
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}