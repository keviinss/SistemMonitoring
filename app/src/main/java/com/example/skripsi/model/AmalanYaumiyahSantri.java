package com.example.skripsi.model;

public class AmalanYaumiyahSantri {
    String userId;
    String namasantri;
    String kodesantri;
    String amalanyaumiyah;
    String tglinput;

    public AmalanYaumiyahSantri(String userId, String namasantri,String kodesantri,String amalanyaumiyah, String tglinput) {
        this.userId = userId;
        this.namasantri = namasantri;
        this.kodesantri = kodesantri;
        this.amalanyaumiyah = amalanyaumiyah;
        this.tglinput = tglinput;
    }

    public AmalanYaumiyahSantri() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNamasantri() {
        return namasantri;
    }

    public void setNamasantri(String namasantri) {
        this.namasantri = namasantri;
    }

    public String getKodesantri() {
        return kodesantri;
    }

    public void setKodesantri(String kodesantri) {
        this.kodesantri = kodesantri;
    }

    public String getAmalanyaumiyah() {
        return amalanyaumiyah;
    }

    public void setAmalanyaumiyah(String amalanyaumiyah) {
        this.amalanyaumiyah = amalanyaumiyah;
    }

    public String getTglinput() {
        return tglinput;
    }

    public void setTglinput(String tglinput) {
        this.tglinput = tglinput;
    }
}
