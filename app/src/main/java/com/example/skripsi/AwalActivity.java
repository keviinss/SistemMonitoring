package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AwalActivity extends AppCompatActivity {
    Button pindah1;
    Button pindah2;
    Button pindah3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        pindah1 = findViewById(R.id.btnSantri);
        pindah2 = findViewById(R.id.btnOrtu);
        pindah3 = findViewById(R.id.btnMentor);

        pindah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AwalActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        pindah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AwalActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        pindah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AwalActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}