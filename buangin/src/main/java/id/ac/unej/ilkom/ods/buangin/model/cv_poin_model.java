package id.ac.unej.ilkom.ods.buangin.model;

public class cv_poin_model {
    private String no, tanggal, poin, sampah;

    public cv_poin_model(String no, String tanggal, String poin, String sampah) {
        this.no = no;
        this.tanggal = tanggal;
        this.poin = poin;
        this.sampah = sampah;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getSampah() {
        return sampah;
    }

    public void setSampah(String sampah) {
        this.sampah = sampah;
    }
}
