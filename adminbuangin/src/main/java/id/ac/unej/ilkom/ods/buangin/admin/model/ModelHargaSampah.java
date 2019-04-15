package id.ac.unej.ilkom.ods.buangin.admin.model;

public class ModelHargaSampah {
    private String hargaKertas, hargaPlastik, kertas, plastik;

    public ModelHargaSampah() {
    }

    public ModelHargaSampah(String hargaKertas, String hargaPlastik, String kertas, String plastik) {
        this.hargaKertas = hargaKertas;
        this.hargaPlastik = hargaPlastik;
        this.kertas = kertas;
        this.plastik = plastik;
    }

    public String getHargaKertas() {
        return hargaKertas;
    }

    public String getHargaPlastik() {
        return hargaPlastik;
    }

    public String getKertas() {
        return kertas;
    }

    public String getPlastik() {
        return plastik;
    }
}
