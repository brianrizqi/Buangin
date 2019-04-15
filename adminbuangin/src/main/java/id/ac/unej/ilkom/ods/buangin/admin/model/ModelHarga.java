package id.ac.unej.ilkom.ods.buangin.admin.model;

public class ModelHarga {
    private String hargaBotolPlastik;
    private String hargaKertas;

    public ModelHarga() {
    }

    public ModelHarga(String hargaBotolPlastik, String hargaKertas) {
        this.hargaBotolPlastik = hargaBotolPlastik;
        this.hargaKertas = hargaKertas;
    }

    public String getHargaBotolPlastik() {
        return hargaBotolPlastik;
    }

    public void setHargaBotolPlastik(String hargaBotolPlastik) {
        this.hargaBotolPlastik = hargaBotolPlastik;
    }

    public String getHargaKertas() {
        return hargaKertas;
    }

    public void setHargaKertas(String hargaKertas) {
        this.hargaKertas = hargaKertas;
    }
}
