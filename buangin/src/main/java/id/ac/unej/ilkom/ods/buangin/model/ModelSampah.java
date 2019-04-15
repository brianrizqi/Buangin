package id.ac.unej.ilkom.ods.buangin.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelSampah {

    public static final String VERIFIKASI_MENUNGGU = "Menunggu";
    public static final String VERIFIKASI_DITERIMA = "Diterima";
    public static final String VERIFIKASI_DITOLAK = "Ditolak";

    private String kodeSampah;
    private String uidVolunteer;
    private String namaVolunteer;
    private String uidBank;
    private String urlFoto;
    private String jenisSampah;
    private String poinSampah;
    private String tanggalSubmit;
    private String tanggalAkhir;
    private String hargaSampah;
    private String beratSampah;
    private String statusVerifikasi;
    private String idBank;

    public ModelSampah() {

    }

    public ModelSampah(String kodeSampah, String uidVolunteer, String namaVolunteer, String uidBank, String urlFoto, String jenisSampah, String poinSampah, String tanggalSubmit, String tanggalAkhir, String hargaSampah, String beratSampah, String statusVerifikasi) {
        this.kodeSampah = kodeSampah;
        this.uidVolunteer = uidVolunteer;
        this.namaVolunteer = namaVolunteer;
        this.uidBank = uidBank;
        this.urlFoto = urlFoto;
        this.jenisSampah = jenisSampah;
        this.poinSampah = poinSampah;
        this.tanggalSubmit = tanggalSubmit;
        this.tanggalAkhir = tanggalAkhir;
        this.hargaSampah = hargaSampah;
        this.beratSampah = beratSampah;
        this.statusVerifikasi = statusVerifikasi;
    }

    public String getIdBank() {
        return idBank;
    }

    public void setIdBank(String idBank) {
        this.idBank = idBank;
    }

    public String getKodeSampah() {
        return kodeSampah;
    }

    public void setKodeSampah(String kodeSampah) {
        this.kodeSampah = kodeSampah;
    }

    public String getUidVolunteer() {
        return uidVolunteer;
    }

    public void setUidVolunteer(String uidVolunteer) {
        this.uidVolunteer = uidVolunteer;
    }

    public String getNamaVolunteer() {
        return namaVolunteer;
    }

    public void setNamaVolunteer(String namaVolunteer) {
        this.namaVolunteer = namaVolunteer;
    }

    public String getUidBank() {
        return uidBank;
    }

    public void setUidBank(String uidBank) {
        this.uidBank = uidBank;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public void setJenisSampah(String jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    public String getPoinSampah() {
        return poinSampah;
    }

    public void setPoinSampah(String poinSampah) {
        this.poinSampah = poinSampah;
    }

    public String getTanggalSubmit() {
        return tanggalSubmit;
    }

    public void setTanggalSubmit(String tanggalSubmit) {
        this.tanggalSubmit = tanggalSubmit;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getHargaSampah() {
        return hargaSampah;
    }

    public void setHargaSampah(String hargaSampah) {
        this.hargaSampah = hargaSampah;
    }

    public String getBeratSampah() {
        return beratSampah;
    }

    public void setBeratSampah(String beratSampah) {
        this.beratSampah = beratSampah;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }
}
