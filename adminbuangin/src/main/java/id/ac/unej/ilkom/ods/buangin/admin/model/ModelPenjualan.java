package id.ac.unej.ilkom.ods.buangin.admin.model;

public class ModelPenjualan {
    private String namaPemilik, namaInstansi, jumlahPoin, tanggalBeli;

    public ModelPenjualan() {
    }

    public ModelPenjualan(String namaPemilik, String namaInstansi, String jumlahPoin, String tanggalBeli) {
        this.namaPemilik = namaPemilik;
        this.namaInstansi = namaInstansi;
        this.jumlahPoin = jumlahPoin;
        this.tanggalBeli = tanggalBeli;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaPnstansi) {
        this.namaInstansi = namaPnstansi;
    }

    public String getJumlahPoin() {
        return jumlahPoin;
    }

    public void setJumlahPoin(String jumlahPoin) {
        this.jumlahPoin = jumlahPoin;
    }

    public String getTanggalBeli() {
        return tanggalBeli;
    }

    public void setTanggalBeli(String tanggalBeli) {
        this.tanggalBeli = tanggalBeli;
    }
}
