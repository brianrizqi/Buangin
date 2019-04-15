package id.ac.unej.ilkom.ods.buangin.model;

public class p_ubahHarga_model {
    private String jenisSampah, pemilik, harga, alamat;
    private int gambar;

    public p_ubahHarga_model(String jenisSampah, String pemilik, String harga, String alamat, int gambar) {
        this.jenisSampah = jenisSampah;
        this.pemilik = pemilik;
        this.harga = harga;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getHarga() {
        return harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getGambar() {
        return gambar;
    }
}
