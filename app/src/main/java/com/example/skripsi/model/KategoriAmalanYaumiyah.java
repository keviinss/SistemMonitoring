package com.example.skripsi.model;

public class KategoriAmalanYaumiyah {
    String userId;
    String amalanYaumiyah;

    public KategoriAmalanYaumiyah(String userId, String amalanYaumiyah) {
        this.userId = userId;
        this.amalanYaumiyah = amalanYaumiyah;
    }

    public KategoriAmalanYaumiyah() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmalanYaumiyah() {
        return amalanYaumiyah;
    }

    public void setAmalanYaumiyah(String amalanYaumiyah) {
        this.amalanYaumiyah = amalanYaumiyah;
    }
}
