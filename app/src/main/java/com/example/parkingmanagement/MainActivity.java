package com.example.parkingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        if(sharedPreferences.getBoolean("loggedIn", false)){
            String userId = sharedPreferences.getString("userId", "null");
            String name = sharedPreferences.getString("name", "null");
            Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);

            intentHome.putExtra("userId", userId);
            intentHome.putExtra("name", name);
            startActivity(intentHome);
        }
        else
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }



    }
}