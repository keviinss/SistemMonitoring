package com.example.skripsi.activity.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.skripsi.R;
import com.example.skripsi.activity.SplashScreen;
import com.example.skripsi.services.MyPreferences;

public class DashboardMentor extends AppCompatActivity {
    ImageView imageExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_mentor);

        initView();

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
    }
}