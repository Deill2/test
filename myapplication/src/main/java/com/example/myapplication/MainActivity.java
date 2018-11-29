package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    SharedPreferences.Editor    editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText    account =   findViewById(R.id.account);
        final EditText    password    =   findViewById(R.id.password);
        final CheckBox    checkBox    =   findViewById(R.id.check);
        Button      btn         =   findViewById(R.id.login);
        SharedPreferences   sharedPreferences   =   getSharedPreferences("remenber", Context.MODE_PRIVATE);
        account.setText(sharedPreferences.getString("name",""));
        password.setText(sharedPreferences.getString("password", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("remenber", false));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor  =   getSharedPreferences("remenber",Context.MODE_PRIVATE).edit();
                if (checkBox.isChecked()){
                    editor.putBoolean("remenber",true);
                    editor.putString("name", account.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.apply();
                }else {
                    editor.putBoolean("remenber",false);
                    editor.putString("name", "");
                    editor.putString("password", "");
                    editor.apply();
                }
            }
        });


    }

    private String getData() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder s = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            if ((line = reader.readLine()) != null) {
                s.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s.toString();
    }

    private void save(String s) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
