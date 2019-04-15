package id.ac.unej.ilkom.ods.buangin.model;

public class v_poin_model {
    private String tanggal, poin, berat;

    public v_poin_model(String tanggal, String poin, String berat) {
        this.tanggal = tanggal;
        this.poin = poin;
        this.berat = berat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getPoin() {
        return poin;
    }

    public String getBerat() {
        return berat;
    }
}
