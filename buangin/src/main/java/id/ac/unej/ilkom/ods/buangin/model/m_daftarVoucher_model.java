package id.ac.unej.ilkom.ods.buangin.model;

public class m_daftarVoucher_model {
    private String namaMitra, poin, jumlah, keterangan;
    private int gambar;

    public m_daftarVoucher_model(String namaMitra, String poin, String jumlah, String keterangan, int gambar) {
        this.namaMitra = namaMitra;
        this.poin = poin;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.gambar = gambar;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public String getPoin() {
        return poin;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public int getGambar() {
        return gambar;
    }
}
