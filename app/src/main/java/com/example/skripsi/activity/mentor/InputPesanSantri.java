package com.example.skripsi.activity.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skripsi.R;
import com.example.skripsi.activity.ortu.InputAmalanYaumiyah;
import com.example.skripsi.activity.retrieve.RetrieveKategoriAmalanYaumiyah;
import com.example.skripsi.activity.retrieve.RetrievePesanSantri;
import com.example.skripsi.model.KategoriAmalanYaumiyah;
import com.example.skripsi.model.PesanSantri;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputPesanSantri extends AppCompatActivity {
    EditText namaSantri,kodeSantri,tgl,deskripsi;
    Button btnSimpan;
    DatabaseReference databaseReference;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pesan_santri);
        initView();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PesanSantri");
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, xnamaSantri,xkodeSantri,xTgl,xDeskripsi;

                userId = databaseReference.push().getKey();
                xnamaSantri = namaSantri.getText().toString();
                xkodeSantri = kodeSantri.getText().toString();
                xTgl = tgl.getText().toString();
                xDeskripsi = deskripsi.getText().toString();


                if (xnamaSantri.isEmpty()) {
                    namaSantri.setError("nama santri tidak boleh kosong");
                }else if (xkodeSantri.isEmpty()) {
                    kodeSantri.setError("kode santri tidak boleh kosong");
                }else if (xDeskripsi.isEmpty()) {
                    deskripsi.setError("deskripsi santri tidak boleh kosong");
                }
                else {
                    PesanSantri pesanSantri = new PesanSantri(userId,xnamaSantri,xkodeSantri,xTgl,xDeskripsi);
                    databaseReference.child(userId).setValue(pesanSantri);
                    Toast.makeText(InputPesanSantri.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RetrievePesanSantri.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }


        });

    }


    public void initView() {
        namaSantri = findViewById(R.id.inputNamaSantri);
        kodeSantri = findViewById(R.id.inputKodeSantri);
        tgl = findViewById(R.id.tgl);
        deskripsi = findViewById(R.id.deskripsi);
        btnSimpan = findViewById(R.id.btnSimpan);
        //get Tanggal Sekarang
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);
        tgl.setText(formattedDate);
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