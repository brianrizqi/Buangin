package id.ac.unej.ilkom.ods.buangin.admin.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pengguna {

    public static final String VOLUNTEER = "volunteer";
    public static final String BANK_SAMPAH = "bank_sampah";
    public static final String MITRA = "mitra";
    public static final String PERUSAHAAN = "perusahaan";
    public static final String ADMIN = "admin";

    private String id;
    private String nama;
    private String namaPemilik;
    private String namaInstansi;
    private String email;
    private String password;
    private String alamat;
    private String telp;
    private String level;
    private String poin;
    private String deskripsi;

    public Pengguna() {
    }

    public Pengguna(String id, String nama, String namaPemilik, String namaInstansi, String email, String password, String alamat, String telp, String level, String poin, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.namaPemilik = namaPemilik;
        this.namaInstansi = namaInstansi;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.telp = telp;
        this.level = level;
        this.poin = poin;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
