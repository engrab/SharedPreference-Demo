package com.example.sharepreferncedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static final String FILE_NAME = "file_name";
    TextView email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);

        String  mail = getIntent().getStringExtra(MainActivity.EMAIL_KEY);
        String pass = getIntent().getStringExtra(MainActivity.PASSWORD_KEY);

        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.putString(MainActivity.EMAIL_KEY, mail);
        editor.putString(MainActivity.PASSWORD_KEY, pass);
        editor.apply();

        email.setText(mail);
        password.setText(pass);
    }
}
