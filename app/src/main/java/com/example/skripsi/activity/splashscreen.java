package com.example.skripsi.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.skripsi.R;
import com.example.skripsi.activity.mentor.DashboardMentor;
import com.example.skripsi.activity.ortu.DashboardOrtu;
import com.example.skripsi.activity.santri.DashboardSantri;
import com.example.skripsi.services.MyPreferences;

import static android.net.sip.SipErrorCode.TIME_OUT;


public class splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINMENTOR, false)) {
                    Intent intent = new Intent(splashscreen.this,
                            DashboardMentor.class);
                    startActivity(intent);
                    finish();

                } else if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINORTU, false)) {
                    Intent intent = new Intent(splashscreen.this,
                            DashboardOrtu.class);
                    startActivity(intent);
                    finish();

                } else if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINSANTRI, false)) {
                    Intent intent = new Intent(splashscreen.this,
                            DashboardSantri.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, TIME_OUT);

    }
}