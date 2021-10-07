package com.example.skripsi.model;

public class Users {
    String userId;
    String username;
    String password;
    String namaLengkap;
    String namaSantri;
    String kodeSantri;
    String noHP;
    String level;

    public Users(String userId, String username, String password, String namaLengkap,String namaSantri,String kodeSantri, String noHP, String level) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.namaSantri = namaSantri;
        this.kodeSantri = kodeSantri;
        this.noHP = noHP;
        this.level = level;
    }

    public Users() {
    }

    public String getNamaSantri() {
        return namaSantri;
    }

    public void setNamaSantri(String namaSantri) {
        this.namaSantri = namaSantri;
    }

    public String getKodeSantri() {
        return kodeSantri;
    }

    public void setKodeSantri(String kodeSantri) {
        this.kodeSantri = kodeSantri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
