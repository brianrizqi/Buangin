package id.ac.unej.ilkom.ods.buangin.model;

public class bs_riwayat_model {
    private String tanggal, berat, status;
    private int gambar;

    public bs_riwayat_model(String tanggal, String berat, String status, int gambar) {
        this.tanggal = tanggal;
        this.berat = berat;
        this.status = status;
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getBerat() {
        return berat;
    }

    public String getStatus() {
        return status;
    }

    public int getGambar() {
        return gambar;
    }
}
