package id.ac.unej.ilkom.ods.buangin.model;

public class bs_daftarPerusahaan_model {
    private String namaInstansi, namaPemilik, alamat, deskripsi, gambar,id;

    public bs_daftarPerusahaan_model() {
    }

    public bs_daftarPerusahaan_model(String namaInstansi, String namaPemilik, String alamat, String deskripsi, String gambar) {
        this.namaInstansi = namaInstansi;
        this.namaPemilik = namaPemilik;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
