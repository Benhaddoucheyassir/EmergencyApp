package com.example.emergencyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "AidGuardPrefs";
    private static final String KEY_IS_VERIFIED = "is_verified";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Check if the user is already logged in/verified
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isVerified = sharedPreferences.getBoolean(KEY_IS_VERIFIED, false);

        if (isVerified) {
            // User is verified, skip this page and go straight to the main app layout
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish(); // Closes LoginActivity so clicking 'Back' doesn't return here
            return;
        }

        setContentView(R.layout.activity_login);

        Button btnVerify = findViewById(R.id.btn_verify); // Make sure this matches your Stitch XML ID
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. Save validation status flag to true
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_IS_VERIFIED, true);
                editor.apply();

                // 3. Forward the user to the active navigation shell
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}