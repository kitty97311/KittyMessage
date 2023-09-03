package com.galaxy.kittymessage

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.util.Log


class SplashActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var countDownTimer: CountDownTimer
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Log.e("App developer", AppConstant.APP_DEVELOPER);

        progressBar = findViewById(R.id.progressBar)
        // Set the countdown timer to 10 seconds
        countDownTimer = object : CountDownTimer(2200, 20) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the text view with the remaining time
                progressBar.progress = ++ count
            }

            override fun onFinish() {
                // Stop the countdown timer and reset the text view
                countDownTimer.cancel()

                // Request permission
                if (ContextCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@SplashActivity,
                        arrayOf(Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS),
                        1)
                } else {
                    // Permission already granted, start the main activity
                    if (AppHelper.GetCurrentLanguage(this@SplashActivity).equals("not_set")) {
                        startActivity(Intent(this@SplashActivity, LanguageActivity::class.java))
                    } else {
                        startActivity(Intent(this@SplashActivity, MessagingActivity::class.java))
                    }
                    finish()
                }
            }
        }.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
            && grantResults[2] == PackageManager.PERMISSION_GRANTED
            && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, start the main activity
            startActivity(Intent(this, LanguageActivity::class.java))
            finish()
        } else {
            // Permission denied, show a message to the user
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}