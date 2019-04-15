package id.ac.unej.ilkom.ods.buangin.model;

public class bs_verifikasi_model {
    public int gambar;
    public String tanggal, berat, waktu, status;

    public bs_verifikasi_model(int gambar, String tanggal, String berat, String waktu, String status) {
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.berat = berat;
        this.waktu = waktu;
        this.status = status;
    }

    public int getGambar() {
        return gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getBerat() {
        return berat;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getStatus() {
        return status;
    }
}
