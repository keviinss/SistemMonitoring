package com.example.skripsi.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static final String ACCOUNTS_PATH = "Users";
    public static final String AMALANYAUMIYAH_PATH = "AmalanYaumiyah";
    public static final String AMALANYAUMIYAHSANTRI_PATH = "AmalanYaumiyahSantri";
    public static final String PESANSANTRI_PATH = "PesanSantri";
    public static final String PERKEMBANGANSANTRI_PATH = "PerkembanganSantri";

    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public static DatabaseReference getReference(String path){
        return firebaseDatabase.getReference(path);
    }
}
