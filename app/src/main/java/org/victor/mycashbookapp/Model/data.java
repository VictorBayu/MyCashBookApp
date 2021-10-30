package org.victor.mycashbookapp.Model;

public class data {
    String simbol, nominal, keterangan,tgl,status;
    int id;

    public data(int id, String simbol, String tgl, String nominal, String keterangan,  String status) {
        this.id=id;
        this.simbol = simbol;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tgl = tgl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
