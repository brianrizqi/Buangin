package id.ac.unej.ilkom.ods.buangin.model;

public class Model_leaderboard {
    private String nama, poin, desc, UID;

    public Model_leaderboard() {
    }

    public Model_leaderboard(String nama, String poin, String desc, String UID) {
        this.nama = nama;
        this.poin = poin;
        this.desc = desc;
        this.UID = UID;
    }

    public String getNama() {
        return nama;
    }

    public String getPoin() {
        return poin;
    }

    public String getDesc() {
        return desc;
    }

    public String getUID() {
        return UID;
    }
}
