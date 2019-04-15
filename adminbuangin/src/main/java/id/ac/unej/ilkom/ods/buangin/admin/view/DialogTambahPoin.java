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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.model.ModelPenjualan;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class DialogTambahPoin extends DialogFragment {
    private TextView textKode, textPemilik, textInstansi;
    private Button btnSimpan, btnBatal;
    private EditText inputHarga, inputPoin;
    private String kode;
    private ImageButton cekPoin;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public void setKode(String kode) {
        this.kode = kode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_tambah_poin, container, false);

        textKode = (TextView) view.findViewById(R.id.dialog_poin_kode);
        textPemilik = (TextView) view.findViewById(R.id.dialog_poin_pemilik);
        textInstansi = (TextView) view.findViewById(R.id.dialog_poin_instansi);
        inputPoin = (EditText) view.findViewById(R.id.dialog_poin_input_poin);
        inputHarga = (EditText) view.findViewById(R.id.dialog_poin_input_harga);
        cekPoin = (ImageButton) view.findViewById(R.id.dialog_poin_btn_cek_poin);
        btnBatal = (Button) view.findViewById(R.id.dialog_poin_btn_batal);
        btnSimpan = (Button) view.findViewById(R.id.dialog_poin_btn_simpan);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textKode.setText(kode);
        inputPoin.setText("0");
        inputPoin.setEnabled(false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("dataBankSampah").child(kode);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pengguna pengguna = dataSnapshot.getValue(Pengguna.class);
                final String stringUID = pengguna.getId();
                final String stringPemilik = pengguna.getNamaPemilik();
                final String stringInstansi = pengguna.getNamaInstansi();

                System.out.println("bank sampah : " + stringUID + " " + stringPemilik + " " + stringInstansi);

                textPemilik.setText(stringPemilik);
                textInstansi.setText(stringInstansi);

                reference = database.getReference("pengguna").child(stringUID);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (final DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            Pengguna model = dataSnapshot2.getValue(Pengguna.class);
                            final String stringKey = dataSnapshot2.getKey();
                            final String stringPoin = model.getPoin();
                            btnSimpan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String strHarga = inputHarga.getText().toString().trim();
                                    Double doubleHarga = Double.valueOf(strHarga);
                                    if (strHarga.isEmpty() || strHarga.equalsIgnoreCase("0")) {
                                        btnSimpan.setEnabled(false);
                                        inputHarga.requestFocus();
                                        Toast.makeText(getContext(), "Harga tidak boleh kosong", Toast.LENGTH_SHORT).show();
                                    } else {
                                        final String strPoin = String.valueOf(doubleHarga / 100);
                                        inputPoin.setText(strPoin);
                                        final String stringTotalPoin = inputPoin.getText().toString().trim();

                                        Double doublePoin = Double.valueOf(stringPoin);
                                        Double doubleTotalPoin = doublePoin + Double.valueOf(stringTotalPoin);

                                        database.getReference("pengguna").child(stringUID).child(stringKey).child("poin").setValue(String.valueOf(doubleTotalPoin));
                                        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                        String tanggal = date.format(new Date());
                                        ModelPenjualan penjualan = new ModelPenjualan(stringPemilik, stringInstansi, stringPoin, tanggal);
                                        database.getReference("dataPenjualan").push().setValue(penjualan);
                                        System.out.println("total poin : " + String.valueOf(doubleTotalPoin));

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                                .setCancelable(false)
                                                .setTitle("Pembelian Poin")
                                                .setMessage("Pembelian poin oleh " + stringInstansi.toUpperCase() + " senilai Rp. " + strHarga + " telah berhasil dilakukan")
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
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cekPoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHarga = inputHarga.getText().toString().trim();
                Double doubleHarga = Double.valueOf(strHarga);
                if (strHarga.isEmpty() || strHarga.equalsIgnoreCase("0")) {
                    btnSimpan.setEnabled(false);
                    inputHarga.requestFocus();
                    Toast.makeText(getContext(), "Harga tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    btnSimpan.setEnabled(true);
                    String strPoin = String.valueOf(doubleHarga / 100);
                    inputPoin.setText(strPoin);
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
}
