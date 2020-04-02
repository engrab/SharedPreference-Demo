package com.example.sharepreferncedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import static android.provider.Contacts.SettingsColumns.KEY;

public class MainActivity extends AppCompatActivity {


    public static final String FILE_NAME = "file_name";
    public static final String NAME = "name";
    public static final String AGE = "age";
    private static final String TAG = "MyPref";
    Button save, read, remove, removeAll;
    TextView textView;
    EditText name, age;

    private SharedPreferences.OnSharedPreferenceChangeListener mListener;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.btn_save);
        read = findViewById(R.id.btn_read);
        remove = findViewById(R.id.btn_remove);
        removeAll = findViewById(R.id.btn_remove_all);

        textView = findViewById(R.id.textview);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);

        save.setOnClickListener(this::save);
        read.setOnClickListener(this::read);
        remove.setOnClickListener(this::remove);
        removeAll.setOnClickListener(this::removeAll);

        mListener = (sharedPreferences, key) -> {

            switch (key){
                case AGE:
                    int age = Integer.parseInt(String.valueOf(sharedPreferences.getInt(AGE, 0)));
                    Log.d(TAG, "onSharedPreferenceChanged: "+age);
                    break;
                case NAME:
                    String name = sharedPreferences.getString(NAME, "name");
                    Log.d(TAG, "onSharedPreferenceChanged: "+name);
                    break;
            }
        };

        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(mListener);
    }

    private void removeAll(View view) {

        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();

    }

    private void remove(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
        editor.remove(AGE);
        editor.apply();


    }

    private void read(View view) {

        SharedPreferences editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String nameStr = editor.getString(NAME, "default name");
        int ageStr = editor.getInt(AGE, 0);

        textView.setText(nameStr + "\n" + ageStr);
    }

    private void save(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();

        String nameStr = name.getText().toString();
        int ageStr = Integer.parseInt(age.getText().toString());

        editor.putString(NAME, nameStr);
        editor.putInt(AGE, ageStr);
        editor.apply();

    }
}
