package com.example.skripsi.activity.ortu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skripsi.R;
import com.example.skripsi.activity.RegisterActivity;
import com.example.skripsi.model.Donasi;
import com.example.skripsi.model.Users;
import com.example.skripsi.services.MyPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TambahDonasi extends AppCompatActivity {
    EditText txtNama, txtTanggal, txtJumlah, txtBayar;
    Button btnDonasi;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_donasi);

        initView();
    }

    private void initView() {
        txtNama = findViewById(R.id.txtNama);
        txtTanggal = findViewById(R.id.txtTanggal);
        txtJumlah = findViewById(R.id.txtJumlah);
        txtBayar = findViewById(R.id.txtBayar);
        btnDonasi = findViewById(R.id.btnDonasi);
        txtNama.setEnabled(false);
        txtBayar.setEnabled(false);
        txtJumlah.setText("Rp." );
        txtJumlah.requestFocus();

        txtNama.setText(MyPreferences.getSharedPreferences()
                .getString(MyPreferences.USERNAME, "username"));

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Donasi");

        btnDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donasiId, xNama, xTanggal, xJumlah, xMetode;

                donasiId = databaseReference.push().getKey();
                xNama = txtNama.getText().toString();
                xTanggal = txtTanggal.getText().toString();
                xJumlah = txtJumlah.getText().toString();
                xMetode = txtBayar.getText().toString();

                if (xNama.isEmpty()){
                    txtNama.setError("Nama tidak boleh kosong");
                }
                else if (xTanggal.isEmpty()){
                    txtTanggal.setError("Tanggal tidak boleh kosong");
                }
                else if (xJumlah.isEmpty()){
                    txtJumlah.setError("Jumlah tidak boleh kosong");
                }
                else if (xMetode.isEmpty()){
                    txtBayar.setError("Metode tidak boleh kosong");
                }
                else {
                    Donasi donasi = new Donasi( donasiId, xNama, xTanggal, xJumlah, xMetode);
                    databaseReference.child(donasiId).setValue(donasi);
                    Toast.makeText(TambahDonasi.this, "Donasi Berhasil Dikirim", Toast.LENGTH_SHORT).show();

                    txtJumlah.requestFocus();
                    txtJumlah.setText("");
                    txtTanggal.setText("");
                    onBackPressed();
                }
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                txtTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}