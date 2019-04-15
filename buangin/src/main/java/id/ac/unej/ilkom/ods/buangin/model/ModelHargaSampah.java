package id.ac.unej.ilkom.ods.buangin.model;

public class ModelHargaSampah {
    private String hargaKertas, hargaPlastik;

    public ModelHargaSampah() {
    }

    public ModelHargaSampah(String hargaKertas, String hargaPlastik) {
        this.hargaKertas = hargaKertas;
        this.hargaPlastik = hargaPlastik;
    }

    public String getHargaKertas() {
        return hargaKertas;
    }

    public String getHargaPlastik() {
        return hargaPlastik;
    }


}
