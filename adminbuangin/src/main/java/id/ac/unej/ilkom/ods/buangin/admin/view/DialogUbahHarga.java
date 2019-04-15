package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.admin.R;

public class DialogUbahHarga extends DialogFragment {

    private TextView textJenis, textHarga;
    private Button simpan, batal;
    private EditText hargaBaru;

    private String harga, jenis;

    private FirebaseDatabase firebaseDatabase;

    public DialogUbahHarga() {
    }

    public void terimaData(String jenis, String harga) {
        this.jenis = jenis;
        this.harga = harga;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_ubah_harga_sampah, container, false);
        textJenis = (TextView) view.findViewById(R.id.dialog_ubah_sampah_jenis);
        textHarga = (TextView) view.findViewById(R.id.dialog_ubah_sampah_harga_lama);
        batal = (Button) view.findViewById(R.id.dialog_ubah_sampah_btn_batal);
        hargaBaru = (EditText) view.findViewById(R.id.dialog_ubah_sampah_input_harga);
        simpan = (Button) view.findViewById(R.id.dialog_ubah_sampah_btn_simpan);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();

        textJenis.setText(jenis);
        textHarga.setText(harga);
        System.out.println("terima " + jenis + harga);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringHargaBaru = hargaBaru.getText().toString().trim().replace(",", ".");
                Boolean bolehSimpan = true;
                if (stringHargaBaru.isEmpty()) {
                    hargaBaru.requestFocus();
                    bolehSimpan = false;
                    Toast.makeText(getContext(), "Harga baru tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    bolehSimpan = true;
                }

                if (bolehSimpan) {
                    if (stringHargaBaru.equalsIgnoreCase("0")) {
                        hargaBaru.requestFocus();
                        bolehSimpan = false;
                        Toast.makeText(getContext(), "Harga baru tidak boleh 0", Toast.LENGTH_SHORT).show();
                    } else {
                        if (jenis.equalsIgnoreCase("botol plastik")) {
                            firebaseDatabase.getReference("dataHarga").child("dataAdmin").child("hargaBotolPlastik").setValue(stringHargaBaru);
                            dialogSimpan(jenis, harga, stringHargaBaru);
                        } else if (jenis.equalsIgnoreCase("kertas")) {
                            firebaseDatabase.getReference("dataHarga").child("dataAdmin").child("hargaKertas").setValue(stringHargaBaru);
                            dialogSimpan(jenis, harga, stringHargaBaru);
                        }
                    }
                }
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void dialogSimpan(String jenis, String hargaLama, String hargaBaru) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setTitle("Simpan Data")
                .setMessage("Data sampah dengan jenis " + jenis + ", harga sebelumnya Rp. " + hargaLama + " berhasil diperbarui ke harga Rp. " + hargaBaru)
                .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
