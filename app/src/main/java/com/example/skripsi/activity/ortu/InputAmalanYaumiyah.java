package com.example.skripsi.activity.ortu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skripsi.R;
import com.example.skripsi.activity.SplashScreen;
import com.example.skripsi.adapter.AdapterKategoriAmalanYaumiyah;
import com.example.skripsi.model.AmalanYaumiyahSantri;
import com.example.skripsi.model.KategoriAmalanYaumiyah;
import com.example.skripsi.services.MyPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InputAmalanYaumiyah extends AppCompatActivity {
    ImageView imageExit;
    TextView user;
    Button btnTambah, btnSimpan;
    Spinner amalanSpinner;
    String amalanYaumiyah;
    EditText tglInput, namaSantri, kodeSantri;
    DatabaseReference amalanYaumiyahdRef;
    ArrayList<KategoriAmalanYaumiyah> kategoriAmalanYaumiyahs;
    ArrayAdapter<String> kategoriAmalanAdapter;
    ArrayList<String> kategoriAmalans;
    DatabaseReference databaseReference;
    private ValueEventListener listenerCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amalan);
        initView();
        namaSantri.setText(MyPreferences.getSharedPreferences()
                .getString(MyPreferences.NAMASANTRI, "namasantri"));
        kodeSantri.setText(MyPreferences.getSharedPreferences()
                .getString(MyPreferences.KODESANTRI, "kodesantri"));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("AmalanYaumiyahSantri");
        fetchdataAmalan();
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TambahAmalanYaumiyah.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        btnTambah = findViewById(R.id.btnTambah);
        btnSimpan = findViewById(R.id.btnSimpan);
        amalanSpinner = findViewById(R.id.amalanSpinner);
        namaSantri = findViewById(R.id.namaSantri);
        kodeSantri = findViewById(R.id.kodeSantri);
        tglInput = findViewById(R.id.tglInput);


        //spinner Kategori Amalan Yaumiyah
        amalanYaumiyahdRef = FirebaseDatabase.getInstance().getReference("AmalanYaumiyah");
        kategoriAmalans = new ArrayList<>();
        kategoriAmalanAdapter = new ArrayAdapter<>(InputAmalanYaumiyah.this, android.R.layout.simple_spinner_dropdown_item, kategoriAmalans);
        amalanSpinner.setAdapter(kategoriAmalanAdapter);
        //get Tanggal Sekarang
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);
        tglInput.setText(formattedDate);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, xnamaSantri, xkodeSantri, xtglInput, xAmalanYaumiyah;

                userId = databaseReference.push().getKey();
                xAmalanYaumiyah = amalanSpinner.getSelectedItem().toString();
                xtglInput = tglInput.getText().toString();
                xnamaSantri = namaSantri.getText().toString();
                xkodeSantri = kodeSantri.getText().toString();

                AmalanYaumiyahSantri amalanYaumiyahSantri = new AmalanYaumiyahSantri(userId,xnamaSantri,xkodeSantri,xAmalanYaumiyah,xtglInput);
                databaseReference.child(userId).setValue(amalanYaumiyahSantri);
                Toast.makeText(InputAmalanYaumiyah.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
                onBackPressed();

            }
        });
    }

    public void fetchdataAmalan() {
        listenerCategory = amalanYaumiyahdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kategoriAmalanYaumiyahs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    KategoriAmalanYaumiyah kategoriAmalanYaumiyah = dataSnapshot.getValue(KategoriAmalanYaumiyah.class);
                    kategoriAmalans.add(kategoriAmalanYaumiyah.getAmalanYaumiyah().toString());
                    kategoriAmalanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}