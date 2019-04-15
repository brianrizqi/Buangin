package id.ac.unej.ilkom.ods.buangin.model;

public class v_daftarBank_model {
    private String id, namaPemilik, namaInstansi, alamat, telp;

    public v_daftarBank_model() {
    }

    public v_daftarBank_model(String id,String namaPemilik, String namaInstansi, String alamat, String telp) {
        this.id=id;
        this.namaPemilik = namaPemilik;
        this.namaInstansi = namaInstansi;
        this.alamat = alamat;
        this.telp = telp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
