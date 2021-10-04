package com.example.skripsi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.skripsi.R;
import com.example.skripsi.activity.mentor.DashboardMentor;
import com.example.skripsi.activity.ortu.DashboardOrtu;
import com.example.skripsi.activity.santri.DashboardSantri;
import com.example.skripsi.services.MyPreferences;

public class SplashScreen extends AppCompatActivity {
    private String TAG = SplashScreen.class.getSimpleName();
    private MyPreferences myPreferences;
    private int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myPreferences = new MyPreferences(SplashScreen.this);

        //FullScreen
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINSANTRI, false)) {
                    Intent intent = new Intent(SplashScreen.this,
                            DashboardSantri.class);
                    startActivity(intent);
                    finish();

                } else if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINORTU, false)) {
                    Intent intent = new Intent(SplashScreen.this,
                            DashboardOrtu.class);
                    startActivity(intent);
                    finish();

                } else if (MyPreferences.getSharedPreferences().
                        getBoolean(MyPreferences.IS_LOGINMENTOR, false)) {
                    Intent intent = new Intent(SplashScreen.this,
                            DashboardMentor.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, TIME_OUT);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        //Method toolbar transaparant
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}