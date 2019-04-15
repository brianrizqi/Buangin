package id.ac.unej.ilkom.ods.buangin.model;

public class Model_harga {
    private String hargaBotolPlastik,hargaKertas;

    public Model_harga(Class<Model_harga> model_hargaClass) {
    }


    public Model_harga(String hargaBotolPlastik, String hargaKertas) {
        this.hargaBotolPlastik = hargaBotolPlastik;
        this.hargaKertas = hargaKertas;
    }

    public String getHargaBotolPlastik() {
        return hargaBotolPlastik;
    }

    public Model_harga() {
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
