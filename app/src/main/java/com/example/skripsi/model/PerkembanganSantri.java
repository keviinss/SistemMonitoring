package com.example.skripsi.model;

public class PerkembanganSantri {
    String userId;
    String namaSantri;
    String kodeSantri;
    String tgl;
    String deskripsi;


    public PerkembanganSantri(String userId, String namaSantri, String kodeSantri, String tgl, String deskripsi) {
        this.userId = userId;
        this.namaSantri = namaSantri;
        this.kodeSantri = kodeSantri;
        this.tgl = tgl;
        this.deskripsi = deskripsi;
    }

    public PerkembanganSantri() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
