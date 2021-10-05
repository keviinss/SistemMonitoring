package com.example.skripsi.activity.ortu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skripsi.R;
import com.example.skripsi.activity.SplashScreen;
import com.example.skripsi.services.MyPreferences;

public class DashboardOrtu extends AppCompatActivity {
    ImageView imageExit;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_ortu);

        initView();

        user.setText(MyPreferences.getSharedPreferences()
                .getString(MyPreferences.USERNAME, "username"));

        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences.getEditorPreferences().clear().commit();
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        imageExit = findViewById(R.id.imageExit);
        user = findViewById(R.id.user);
    }
}