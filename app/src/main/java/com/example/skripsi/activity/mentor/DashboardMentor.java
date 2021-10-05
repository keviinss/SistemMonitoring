package com.example.skripsi.activity.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.skripsi.R;
import com.example.skripsi.activity.SplashScreen;
import com.example.skripsi.activity.retrieve.RetrieveUserData;
import com.example.skripsi.services.MyPreferences;

public class DashboardMentor extends AppCompatActivity {
    ImageView imageExit;
    LinearLayout LLKelolaAkun;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_mentor);

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

        LLKelolaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrieveUserData.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        imageExit = findViewById(R.id.imageExit);
        LLKelolaAkun = findViewById(R.id.LLKelolaAkun);
        user = findViewById(R.id.user);
    }
}