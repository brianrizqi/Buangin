package id.ac.unej.ilkom.ods.buangin.model;

public class ModelVoucherVolunteer {

    //apablia voucher sudah dibeli
    public static final String VOUCHER_DIBELI = "dibeli";
    //apablia voucher sudah ditukar
    public static final String VOUCHER_DITUKAR = "ditukar";

    private String namaVolunteer;
    private String namaMitra;
    private String uidMitra;
    private String kodeVoucher;
    private String url_foto;
    private String namaVoucher;
    private String deskripsi;
    private String hargaPoin;
    private String statusVoucher;
    private String tanggal;
    private String uidVolunteer;

    public ModelVoucherVolunteer() {
    }

    public ModelVoucherVolunteer(String namaVolunteer, String namaMitra, String uidMitra, String kodeVoucher, String url_foto, String namaVoucher, String deskripsi, String hargaPoin, String statusVoucher, String tanggal, String uidVolunteer) {
        this.namaVolunteer = namaVolunteer;
        this.namaMitra = namaMitra;
        this.uidMitra = uidMitra;
        this.kodeVoucher = kodeVoucher;
        this.url_foto = url_foto;
        this.namaVoucher = namaVoucher;
        this.deskripsi = deskripsi;
        this.hargaPoin = hargaPoin;
        this.statusVoucher = statusVoucher;
        this.tanggal = tanggal;
        this.uidVolunteer = uidVolunteer;
    }

    public String getNamaVolunteer() {
        return namaVolunteer;
    }

    public void setNamaVolunteer(String namaVolunteer) {
        this.namaVolunteer = namaVolunteer;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getUidMitra() {
        return uidMitra;
    }

    public void setUidMitra(String uidMitra) {
        this.uidMitra = uidMitra;
    }

    public String getKodeVoucher() {
        return kodeVoucher;
    }

    public void setKodeVoucher(String kodeVoucher) {
        this.kodeVoucher = kodeVoucher;
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

    public String getStatusVoucher() {
        return statusVoucher;
    }

    public void setStatusVoucher(String statusVoucher) {
        this.statusVoucher = statusVoucher;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getUidVolunteer() {
        return uidVolunteer;
    }

    public void setUidVolunteer(String uidVolunteer) {
        this.uidVolunteer = uidVolunteer;
    }
}
