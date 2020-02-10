package com.example.languageprefrences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       super.onOptionsItemSelected(item);
       switch (item.getItemId()){
           case R.id.english:
               setlanguage("English");
               return true;
           case R.id.spanish:
               setlanguage("Spanish");
               return true;
           default:
            return false;
       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text);
        sharedPreferences=this.getSharedPreferences("com.example.languageprefrences", Context.MODE_PRIVATE);
        String language=sharedPreferences.getString("language","Error");
        if (language.equals("Error")) {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Select Language")
                    .setMessage("Please select language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setlanguage("English");
                        }
                    }).setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setlanguage("Spanish");
                }
            }).show();
        }
        else
        {
            textView.setText(language);
        }
    }
    void setlanguage(String language)
    {
        sharedPreferences.edit().putString("language",language).apply();
        textView.setText(language);
    }
}
