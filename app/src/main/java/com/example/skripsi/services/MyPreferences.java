package com.example.skripsi.services;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private Context context;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final String ACCOUNT_PREF = "ACCOUNT_PREF";
    public static final String IS_LOGINMENTOR = "IS_LOGINMENTOR";
    public static final String IS_LOGINORTU = "IS_LOGINORTU";
    public static final String IS_LOGINSANTRI = "IS_LOGINSANTRI";
    public static final String IDUSER = "IDUSER";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String NAMALENGKAP = "NAMALENGKAP";
    public static final String NOHP = "NOHP";
    public static final String LEVEL = "LEVEL";


    public MyPreferences(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditorPreferences() {
        return editor;
    }

}
