package com.example.skripsi;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText user,password,fullName,phone;
    Button registerBtn;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        user = findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPassword);
        fullName = findViewById(R.id.inputNama);
        phone = findViewById(R.id.editTextPhone);
        registerBtn = findViewById(R.id.btnRegister);


        checkField(user);
        checkField(password);
        checkField(fullName);
        checkField(phone);

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