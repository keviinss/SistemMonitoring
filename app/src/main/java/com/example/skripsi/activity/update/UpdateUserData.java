package com.example.skripsi.activity.update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.skripsi.R;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateUserData extends AppCompatActivity {
    EditText inputUser, inputPassword, inputNama, noHandphone;
    Spinner spLevel;
    Button btnUpdate;
    String userId, username, password, namaLengkap, noHP, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_data);

        initView();

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        namaLengkap = intent.getStringExtra("namaLengkap");
        noHP = intent.getStringExtra("noHP");
        level = intent.getStringExtra("level");

        inputUser.setText(username);
        inputPassword.setText(password);
        inputNama.setText(namaLengkap);
        noHandphone.setText(noHP);
        if (level.equals("Santri")){
            spLevel.setSelection(1);
        }
        else if (level.equals("OrangTua")){
            spLevel.setSelection(2);
        }
        else if (level.equals("Mentor")){
            spLevel.setSelection(2);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                String username, password, namaLengkap, noHP, level;

                username = inputUser.getText().toString();
                password = inputPassword.getText().toString();
                namaLengkap = inputNama.getText().toString();
                noHP = noHandphone.getText().toString();
                level = spLevel.getSelectedItem().toString();

                Users userData = new Users(userId,username, password, namaLengkap, noHP, level);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateUserData.this, "Data Berhasil Diupdate",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    private void initView() {
        inputUser = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
        inputNama = findViewById(R.id.inputNama);
        noHandphone = findViewById(R.id.noHp);
        spLevel = findViewById(R.id.level);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}