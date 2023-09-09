package com.galaxy.kittymessage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private static final int PERMISSION_REQUEST_CODE = 123;

    private String[] permissions = {
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            // Add any other permissions you need
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        // Check if permissions are granted
        if (checkPermissions()) {
            startProgress();
        } else {
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                startProgress();
            } else {
                Toast.makeText(this, "Permissions not granted. Exiting app.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void startProgress() {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;

                // Update the progress on the UI thread
                handler.post(new Runnable() {
                    public void run() {
                        progressBar.setProgress(progressStatus);
                    }
                });

                try {
                    // Delay for 20 milliseconds (adjust as needed)
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startMainActivity();
        }).start();

    }

    private void startMainActivity() {
        Intent intent;
        if (AppHelper.GetCurrentLanguage(SplashActivity.this).equals(AppConstant.NOT_SET_KEY)) {
            intent = new Intent(SplashActivity.this, LanguageActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, MessagingActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
