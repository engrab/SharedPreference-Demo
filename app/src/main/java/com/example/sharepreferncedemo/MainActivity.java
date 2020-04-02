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

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import static android.provider.Contacts.SettingsColumns.KEY;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    EditText email, password;
    SharedPreferences.OnSharedPreferenceChangeListener mListener;
    EditText name, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.email2);
        age = findViewById(R.id.password2);
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

    public void save(View view){
        String nameStr = name.getText().toString();
        int ageStr = Integer.parseInt(age.getText().toString());

        User user = new User(ageStr, nameStr);
        Gson gson = new Gson();
        String jsonString;
        jsonString = gson.toJson(user, User.class);

        SharedPreferences.Editor s = PreferenceManager.getDefaultSharedPreferences(this).edit();
        s.putString("key",jsonString);
        s.apply();


    }
    public void read(View view){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String data = sharedPreferences.getString("key", "default");

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("key", data);
        startActivity(intent);

    }
}
