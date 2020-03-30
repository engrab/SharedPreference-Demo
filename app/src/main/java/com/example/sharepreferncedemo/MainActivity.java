package com.example.sharepreferncedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        SharedPreferences sharedPreferences = getSharedPreferences(HomeActivity.FILE_NAME, MODE_PRIVATE);
        String emailString = sharedPreferences.getString(EMAIL_KEY, "default email...");
        String  passwordString =sharedPreferences.getString(PASSWORD_KEY, "default password...");

        email.setText(emailString);
        password.setText(passwordString);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(EMAIL_KEY, emailString);
                intent.putExtra(PASSWORD_KEY, passwordString);
                startActivity(intent);
            }
        });
    }
}
