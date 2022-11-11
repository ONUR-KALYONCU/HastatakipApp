package com.example.hafta6_1;

import java.io.Serializable;

public class Hasta implements Serializable {

    private String adsoyad,cinsiyet;
    private double boy,kilo;

    public Hasta(String adsoyad, String cinsiyet, double boy, double kilo) {
        super();
        this.adsoyad = adsoyad;
        this.cinsiyet = cinsiyet;
        this.boy = boy;
        this.kilo = kilo;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public double getBoy() {
        return boy;
    }

    public void setBoy(double boy) {
        this.boy = boy;
    }

    public double getKilo() {
        return kilo;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }
}
