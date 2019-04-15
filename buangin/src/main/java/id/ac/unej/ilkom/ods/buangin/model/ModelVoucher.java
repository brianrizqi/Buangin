package id.ac.unej.ilkom.ods.buangin.model;

public class ModelVoucher {

    //apabila stok voucher masih ada
    public static final String VOUCHER_BERLAKU = "berlaku";

    //apabila stok voucher sudah habis
    public static final String VOUCHER_TIDAK_BERLAKU = "tidak_berlaku";

    private String key;
    private String uidMitra;
    private String namaMitra;
    private String url_foto;
    private String namaVoucher;
    private String deskripsi;
    private String hargaPoin;
    private String jumlahKuota;
    private String statusVoucher;

    public ModelVoucher() {

    }

    public ModelVoucher(String uidMitra, String namaMitra, String url_foto, String namaVoucher, String deskripsi, String hargaPoin, String jumlahKuota, String statusVoucher) {
        this.uidMitra = uidMitra;
        this.namaMitra = namaMitra;
        this.url_foto = url_foto;
        this.namaVoucher = namaVoucher;
        this.deskripsi = deskripsi;
        this.hargaPoin = hargaPoin;
        this.jumlahKuota = jumlahKuota;
        this.statusVoucher = statusVoucher;
    }

    public String getUidMitra() {
        return uidMitra;
    }

    public void setUidMitra(String uidMitra) {
        this.uidMitra = uidMitra;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getNamaVoucher() {
        return namaVoucher;
    }

    public void setNamaVoucher(String namaVoucher) {
        this.namaVoucher = namaVoucher;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHargaPoin() {
        return hargaPoin;
    }

    public void setHargaPoin(String hargaPoin) {
        this.hargaPoin = hargaPoin;
    }

    public String getJumlahKuota() {
        return jumlahKuota;
    }

    public void setJumlahKuota(String jumlahKuota) {
        this.jumlahKuota = jumlahKuota;
    }

    public String getStatusVoucher() {
        return statusVoucher;
    }

    public void setStatusVoucher(String statusVoucher) {
        this.statusVoucher = statusVoucher;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
