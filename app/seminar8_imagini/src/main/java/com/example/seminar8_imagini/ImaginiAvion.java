package com.example.seminar8_imagini;

import android.graphics.Bitmap;

public class ImaginiAvion {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImaginiAvion() {
    }

    public ImaginiAvion(Bitmap imagine, String link, String textAfisat) {
        this.imagine = imagine;
        this.link = link;
        this.textAfisat = textAfisat;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public void setTextAfisat(String textAfisat) {
        this.textAfisat = textAfisat;
    }

    @Override
    public String toString() {
        return "ImaginiAvion{" +
                "imagine=" + imagine +
                ", textAfisat='" + textAfisat + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
