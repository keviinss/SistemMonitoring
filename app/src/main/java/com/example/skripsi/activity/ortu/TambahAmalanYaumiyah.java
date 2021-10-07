package com.example.skripsi.activity.ortu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi.R;
import com.example.skripsi.activity.retrieve.RetrieveKategoriAmalanYaumiyah;
import com.example.skripsi.activity.retrieve.RetrieveUserData;
import com.example.skripsi.adapter.AdapterKategoriAmalanYaumiyah;
import com.example.skripsi.adapter.AdapterUserData;
import com.example.skripsi.model.KategoriAmalanYaumiyah;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TambahAmalanYaumiyah extends AppCompatActivity {
    EditText tambahAmalanYaumiyah;
    Button btnTambah,daftarAmalan;
    DatabaseReference databaseReference;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_amalanyaumiyah);
        initView();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("AmalanYaumiyah");
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, xAmalanYaumiyah;

                userId = databaseReference.push().getKey();
                xAmalanYaumiyah = tambahAmalanYaumiyah.getText().toString();

                if (xAmalanYaumiyah.isEmpty()) {
                    tambahAmalanYaumiyah.setError("Amalan Yaumiyah tidak boleh kosong");
                } else {
                    KategoriAmalanYaumiyah kategoriAmalanYaumiyah = new KategoriAmalanYaumiyah(userId, xAmalanYaumiyah);
                    databaseReference.child(userId).setValue(kategoriAmalanYaumiyah);
                    Toast.makeText(TambahAmalanYaumiyah.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),InputAmalanYaumiyah.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }


        });

        daftarAmalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrieveKategoriAmalanYaumiyah.class);
                startActivity(intent);
            }
        });
    }


    public void initView() {
        tambahAmalanYaumiyah = findViewById(R.id.namaAmalan);
        btnTambah = findViewById(R.id.btnSimpan);
        daftarAmalan = findViewById(R.id.daftarAmalan);

    }


    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }


}