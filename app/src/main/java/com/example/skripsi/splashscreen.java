package com.example.skripsi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread thread = new Thread() {
           public void run() {
               try {
                   sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   //startActivity(new Intent(splashscreen.this, HomeActivity.class));
                   finish();
               }
            }
        };
        thread.start();

    }
}