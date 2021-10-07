package com.example.skripsi.activity.ortu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.skripsi.R;
import com.example.skripsi.activity.SplashScreen;
import com.example.skripsi.activity.retrieve.RetrieveDonasi;
import com.example.skripsi.activity.retrieve.RetrievePesanSantri;
import com.example.skripsi.services.MyPreferences;

public class DashboardOrtu extends AppCompatActivity {
    ImageView imageExit;
    CardView menuAmalan,menuPesan,menuDonasi,menuPerkembangan;
    TextView user;
    LinearLayout LLDonasi;
    CardView pesanmentorMenu;

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
        LLDonasi = findViewById(R.id.LLDonasi);
        menuAmalan = findViewById(R.id.amalanMenu);
        pesanmentorMenu = findViewById(R.id.pesanmentorMenu);

        menuAmalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InputAmalanYaumiyah.class);
                startActivity(intent);
            }
        });

        LLDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrieveDonasi.class);
                startActivity(intent);
            }
        });

        pesanmentorMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrievePesanSantri.class);
                startActivity(intent);
            }
        });
    }
}