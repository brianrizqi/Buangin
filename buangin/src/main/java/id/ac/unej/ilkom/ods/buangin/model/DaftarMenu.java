package id.ac.unej.ilkom.ods.buangin.model;

public class DaftarMenu {

    private int ID;
    private String Nama;
    private int IDFoto;

    public DaftarMenu(int ID, String nama, int IDFoto) {
        this.ID = ID;
        Nama = nama;
        this.IDFoto = IDFoto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getIDFoto() {
        return IDFoto;
    }

    public void setIDFoto(int IDFoto) {
        this.IDFoto = IDFoto;
    }
}
