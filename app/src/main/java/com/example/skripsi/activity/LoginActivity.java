package com.example.skripsi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skripsi.R;
import com.example.skripsi.activity.mentor.DashboardMentor;
import com.example.skripsi.activity.ortu.DashboardOrtu;
import com.example.skripsi.activity.santri.DashboardSantri;
import com.example.skripsi.model.Users;
import com.example.skripsi.services.FirebaseUtils;
import com.example.skripsi.services.MyPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText user, inputPassword;
    Button loginBtn;
    TextView txtRegister;
    private String username;
    private String password;
    boolean valid = true;
    private ProgressDialog progressDialog;
    private String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAccount();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        user = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);
        progressDialog = new ProgressDialog(this);
    }

    private void signInAccount() {
        username = user.getText().toString().trim();
        password = inputPassword.getText().toString().trim();

        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (username.isEmpty()) {
                    user.setError("Username wajib diisi");
                    return;
                } else if (password.isEmpty()) {
                    inputPassword.setError("Password wajib diisi");
                    return;
                } else if (password.length() < 6) {
                    inputPassword.setError("Password minimal terdiri dari 6 karakter");
                } else {

                }
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users userData = snapshot.getValue(Users.class);
                        if (userData.getUsername().equals(username) &&
                                userData.getPassword().equals(password) && userData.getLevel().equals("Santri")) {
                            MyPreferences.getEditorPreferences()
                                    .putBoolean(MyPreferences.IS_LOGINSANTRI, true);

                            //GET DATA ID USER
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.IDUSER, userData.getUserId());

                            //GET DATA USERNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.USERNAME, userData.getNamaLengkap());

                            //GET DATA PASSWORD
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.PASSWORD, userData.getPassword());

                            //GET DATA FULLNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NAMALENGKAP, userData.getNamaLengkap());

                            //GET DATA NOHP
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NOHP, userData.getNoHP());

                            //GET DATA LEVEL
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.LEVEL, userData.getLevel());

                            progressDialog.setMessage("Mohon Tunggu");
                            progressDialog.show();
                            progressDialog.setCanceledOnTouchOutside(false);

                            MyPreferences.getEditorPreferences().commit();
                            Intent intent = new Intent(LoginActivity.this, DashboardSantri.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Selamat Datang " + userData.getNamaLengkap(), Toast.LENGTH_SHORT).show();

                        } else if (userData.getUsername().equals(username) &&
                                userData.getPassword().equals(password) && userData.getLevel().equals("OrangTua")) {
                            MyPreferences.getEditorPreferences()
                                    .putBoolean(MyPreferences.IS_LOGINORTU, true);

                            //GET DATA ID USER
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.IDUSER, userData.getUserId());

                            //GET DATA USERNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.USERNAME, userData.getNamaLengkap());

                            //GET DATA PASSWORD
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.PASSWORD, userData.getPassword());

                            //GET DATA FULLNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NAMALENGKAP, userData.getNamaLengkap());

                            //GET DATA NOHP
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NOHP, userData.getNoHP());

                            //GET DATA LEVEL
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.LEVEL, userData.getLevel());

                            progressDialog.setMessage("Mohon Tunggu");
                            progressDialog.show();
                            progressDialog.setCanceledOnTouchOutside(false);

                            MyPreferences.getEditorPreferences().commit();
                            Intent intent = new Intent(LoginActivity.this, DashboardOrtu.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Selamat Datang " + userData.getNamaLengkap(), Toast.LENGTH_SHORT).show();

                        } else if (userData.getUsername().equals(username) &&
                                userData.getPassword().equals(password) && userData.getLevel().equals("Mentor")) {
                            MyPreferences.getEditorPreferences()
                                    .putBoolean(MyPreferences.IS_LOGINMENTOR, true);

                            //GET DATA ID USER
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.IDUSER, userData.getUserId());

                            //GET DATA USERNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.USERNAME, userData.getNamaLengkap());

                            //GET DATA PASSWORD
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.PASSWORD, userData.getPassword());

                            //GET DATA FULLNAME
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NAMALENGKAP, userData.getNamaLengkap());

                            //GET DATA NOHP
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.NOHP, userData.getNoHP());

                            //GET DATA LEVEL
                            MyPreferences.getEditorPreferences()
                                    .putString(MyPreferences.LEVEL, userData.getLevel());

                            progressDialog.setMessage("Mohon Tunggu");
                            progressDialog.show();
                            progressDialog.setCanceledOnTouchOutside(false);

                            MyPreferences.getEditorPreferences().commit();
                            Intent intent = new Intent(LoginActivity.this, DashboardMentor.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Selamat Datang " + userData.getNamaLengkap(), Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Username dan Password belum terdaftar", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails() + " | " + databaseError.getMessage());
            }
        });

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