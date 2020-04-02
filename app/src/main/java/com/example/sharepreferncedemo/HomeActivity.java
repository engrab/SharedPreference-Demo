package com.example.sharepreferncedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    public static final String FILE_NAME = "file_name";
    TextView email, password;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);
        textView = findViewById(R.id.textView);

        String key = getIntent().getStringExtra("key");

        Gson gson = new Gson();
        User user = gson.fromJson(key, User.class);



        textView.setText(user.getName()+"\n"+user.getAge());


//        String  mail = getIntent().getStringExtra(MainActivity.EMAIL_KEY);
//        String pass = getIntent().getStringExtra(MainActivity.PASSWORD_KEY);
//
//        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
//        editor.putString(MainActivity.EMAIL_KEY, mail);
//        editor.putString(MainActivity.PASSWORD_KEY, pass);
//        editor.apply();
//
//        email.setText(mail);
//        password.setText(pass);
    }
}
