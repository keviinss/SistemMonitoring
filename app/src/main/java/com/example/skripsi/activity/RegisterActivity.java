package com.example.skripsi.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skripsi.R;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText user,password,fullName,phone;
    Spinner level;
    Button registerBtn;
    CheckBox cbMentor, cbOrtu, cbSantri;
    DatabaseReference databaseReference;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, xUsername, xPassword, xNamaLengkap, xNoHp, xLevel;

                userId = databaseReference.push().getKey();
                xUsername = user.getText().toString();
                xPassword = password.getText().toString();
                xNamaLengkap = fullName.getText().toString();
                xNoHp = phone.getText().toString();
                xLevel = level.getSelectedItem().toString();

                if (xUsername.isEmpty()){
                    user.setError("Username tidak boleh kosong");
                }
                else if (xPassword.isEmpty()){
                    password.setError("Password tidak boleh kosong");
                }
                else if (xNamaLengkap.isEmpty()){
                    fullName.setError("Nama tidak boleh kosong");
                }
                else if (xNoHp.isEmpty()){
                    phone.setError("No.HP tidak boleh kosong");
                }
                else if (xLevel.equals("")){
                    Toast.makeText(RegisterActivity.this, "Wajib Di Isi !!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Users users = new Users(userId, xUsername, xPassword, xNamaLengkap, xNoHp, xLevel);
                    databaseReference.child(userId).setValue(users);
                    Toast.makeText(RegisterActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

                    user.requestFocus();
                    user.setText("");
                    password.setText("");
                    fullName.setText("");
                    phone.setText("");
                    level.setSelection(0);
                    onBackPressed();
                }
            }
        });

    }

    public void init() {
        user = findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPassword);
        fullName = findViewById(R.id.inputNama);
        phone = findViewById(R.id.noHp);
        registerBtn = findViewById(R.id.btnRegister);
        level = findViewById(R.id.level);
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}