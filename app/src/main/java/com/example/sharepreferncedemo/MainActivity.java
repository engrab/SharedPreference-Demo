package com.example.sharepreferncedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    EditText email, password;
    SharedPreferences.OnSharedPreferenceChangeListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        final TextView tvname = findViewById(R.id.name);

        SharedPreferences sharedPreferencess = PreferenceManager.getDefaultSharedPreferences(this);

        mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals("edit_name")){
                    String name = sharedPreferences.getString("edit_name", "default name");
                    tvname.setText(name);
                }
            }
        };
        sharedPreferencess.registerOnSharedPreferenceChangeListener(mListener);

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
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }
}
