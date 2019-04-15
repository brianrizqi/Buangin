package id.ac.unej.ilkom.ods.buangin.model;

public class ModelPoin {

    public static final String DARI_PEMBELIAN_VOUCHER = "dariBeliVoucher";
    public static final String DARI_PENUKARAN_SAMPAH = "dariTukarSampah";

    private String poin;
    private String uidVolunteer;
    private String dari;
    private String sumber;

    public ModelPoin() {
    }

    public ModelPoin(String poin, String uidVolunteer, String dari, String sumber) {
        this.poin = poin;
        this.uidVolunteer = uidVolunteer;
        this.dari = dari;
        this.sumber = sumber;
    }


    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getUidVolunteer() {
        return uidVolunteer;
    }

    public void setUidVolunteer(String uidVolunteer) {
        this.uidVolunteer = uidVolunteer;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}
