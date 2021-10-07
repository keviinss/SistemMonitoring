package com.example.skripsi.model;

public class Donasi {
    String donasiId;
    String nama;
    String tanggal;
    String jumlah;
    String metode;

    public Donasi() {
    }

    public Donasi(String donasiId, String nama, String tanggal, String jumlah, String metode) {
        this.donasiId = donasiId;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.metode = metode;
    }

    public String getDonasiId() {
        return donasiId;
    }

    public void setDonasiId(String donasiId) {
        this.donasiId = donasiId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }
}
