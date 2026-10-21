package com.example.modulnou_seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Avion implements Parcelable {
    public Avion() {
    }
    public Avion(String modelAvion, String companie, int nrLocuri, String tip, boolean areWifi, boolean esteInServiciu, float rating) {
        this.modelAvion = modelAvion;
        this.companie = companie;
        this.nrLocuri = nrLocuri;
        this.tip = tip;
        this.areWifi = areWifi;
        this.esteInServiciu = esteInServiciu;
        this.rating = rating;
    }

    protected Avion(Parcel in) {
        modelAvion = in.readString();
        nrLocuri = in.readInt();
        companie = in.readString();
        tip = in.readString();
        areWifi = in.readByte() != 0;
        esteInServiciu = in.readByte() != 0;
        rating = in.readFloat();
    }

    public static final Creator<Avion> CREATOR = new Creator<Avion>() {
        @Override
        public Avion createFromParcel(Parcel in) {
            return new Avion(in);
        }

        @Override
        public Avion[] newArray(int size) {
            return new Avion[size];
        }
    };

    public String getModelAvion() {
        return modelAvion;
    }

    public void setModelAvion(String modelAvion) {
        this.modelAvion = modelAvion;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isAreWifi() {
        return areWifi;
    }

    public void setAreWifi(boolean areWifi) {
        this.areWifi = areWifi;
    }

    public boolean isEsteInServiciu() {
        return esteInServiciu;
    }

    public void setEsteInServiciu(boolean esteInServiciu) {
        this.esteInServiciu = esteInServiciu;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "modelAvion='" + modelAvion + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", companie='" + companie + '\'' +
                ", tip='" + tip + '\'' +
                ", areWifi=" + areWifi +
                ", esteInServiciu=" + esteInServiciu +
                ", rating=" + rating +
                '}';
    }

    private String modelAvion;
    private int nrLocuri;
    private String companie;
    private String tip;
    private boolean areWifi;
    private boolean esteInServiciu;
    private float rating;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(modelAvion);
        dest.writeInt(nrLocuri);
        dest.writeString(companie);
        dest.writeString(tip);
        dest.writeByte((byte) (areWifi ? 1 : 0));
        dest.writeByte((byte) (esteInServiciu ? 1 : 0));
        dest.writeFloat(rating);
    }
}
