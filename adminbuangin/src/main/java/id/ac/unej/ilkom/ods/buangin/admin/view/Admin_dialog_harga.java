package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.util.Util;

public class Admin_dialog_harga extends Dialog implements View.OnClickListener {
    private Activity activity;
    private String kode = "";
    String harga = "";

    public Admin_dialog_harga(Activity activity, String kode, String harga) {
        super(activity);
        this.activity = activity;
        this.kode = kode;
        this.harga = harga;
    }

    @Override
    public void onClick(View v) {

    }

    private TextView txtIsi, btnSimpan, btnBatal;
    private EditText inputHarga;

    private String strSampah = "",strHarga="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dialog_harga);

        txtIsi = findViewById(R.id.admin_dialog_harga_text);
        btnSimpan = findViewById(R.id.admin_dialog_harga_btn_simpan);
        btnBatal = findViewById(R.id.admin_dialog_harga_btn_batal);
        inputHarga = findViewById(R.id.admin_dialog_harga_input_harga);

        if (kode.equalsIgnoreCase(Util.util_harga_kertas)) {
            strSampah = "KERTAS";
        } else {
            strSampah = "BOTOL PLASTIK";
        }

        inputHarga.setText(harga);
        txtIsi.setText("Masukkan harga baru untuk jenis sampah " + strSampah);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strHarga=inputHarga.getText().toString().trim();
                if (strHarga.isEmpty()){
                    pesan("Jangan biarkan kosong");
                }else{
                    simpan();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void simpan() {
        FirebaseDatabase.getInstance().getReference()
                .child("hargaSampah")
                .child(kode)
                .setValue(strHarga)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pesan("Berhasil");
                            dismiss();
                        }
                    }
                });
    }

    private void pesan(String pesan){
        Toast.makeText(getContext(), pesan, Toast.LENGTH_SHORT).show();
    }
}
