package id.ac.unej.ilkom.ods.buangin.view.BankSampah;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelPoin;
import id.ac.unej.ilkom.ods.buangin.model.ModelSampah;
import id.ac.unej.ilkom.ods.buangin.model.Model_harga;
import id.ac.unej.ilkom.ods.buangin.model.Model_leaderboard;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;
import id.ac.unej.ilkom.ods.buangin.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
class mDialogVerifikasi extends DialogFragment {

    final static String DATA_DITERIMA = "data_diterima";

    private ImageView imageView;
    private TextView id_sampah, pemilik, status, poin, textHarga;
    private EditText berat, sampah, harga;
    private Button terima, tolak;
    private Spinner jenisSampah;
    private ImageButton cekHarga;

    private String kode;

    private String strKertas = "", strBotol = "";

    private double
            hargaBotolPlastik,
            hargaKertas,
            poinBotolPlastik,
            poinKertas,
            totalPoin,
            finalTotalPoin,
            hargaSampah;

    private Bitmap img;
    private Uri imgUri;

    private FirebaseDatabase firebaseDatabase, fDat;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference, dRef, postsRef;

//    private static final String[] jenis = {"botol plastik", "kertas", "jenis lain"};


    public void setKode(String kode) {
        this.kode = kode;
    }

    private String strUID = "", strPoin = "", strDesc = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_verifikasi, container, false);

        getHarga();

        imageView = (ImageView) view.findViewById(R.id.img_preview_verifikasi);
        id_sampah = (TextView) view.findViewById(R.id.text_id_sampah_verifikasi);
        pemilik = (TextView) view.findViewById(R.id.text_pemilik_sampah_verifikasi);
        berat = (EditText) view.findViewById(R.id.input_berat_sampah_verifikasi);
        status = (TextView) view.findViewById(R.id.text_status_verifikasi);
        jenisSampah = (Spinner) view.findViewById(R.id.spin_jenis_sampah);
        sampah = (EditText) view.findViewById(R.id.input_jenis_sampah);
        harga = (EditText) view.findViewById(R.id.input_harga_sampah);
        cekHarga = (ImageButton) view.findViewById(R.id.btn_cek_harga);
        poin = (TextView) view.findViewById(R.id.text_poin_verifikasi);
        textHarga = (TextView) view.findViewById(R.id.text_harga_verifikasi);

        terima = (Button) view.findViewById(R.id.btn_terima_verifikasi);
        tolak = (Button) view.findViewById(R.id.btn_tolak_verifikasi);

        berat.setText("0");

        sampah.setEnabled(false);
        harga.setEnabled(false);

        return view;
    }

    private void getHarga() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("dataHarga").child("dataAdmin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Model_harga m = new Model_harga(Model_harga.class);
                    strBotol = m.getHargaBotolPlastik();
                    strKertas = m.getHargaKertas();
                } else {
                    strKertas="3600";
                    strBotol="950";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataSampah").child(kode);
        getPemilik();

        List<String> list_sampah = new ArrayList<String>();
        list_sampah.add("Botol Plastik");
        list_sampah.add("Kertas");
        list_sampah.add("Jenis Lain");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_sampah);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisSampah.setSelection(0);
        jenisSampah.setAdapter(arrayAdapter);

//        cekHarga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String jenis_sampah = String.valueOf(jenisSampah.getSelectedItem());
//
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelSampah model = dataSnapshot.getValue(ModelSampah.class);
                final String stringStatus = model.getStatusVerifikasi();
                final String stringUID = model.getUidVolunteer();
//                String stringNama = model.getNamaVolunteer();

                id_sampah.setText(kode);
                status.setText(stringStatus);

                fDat = FirebaseDatabase.getInstance();
                dRef = fDat.getReference("pengguna").child(stringUID);
                System.out.println("uid pengguna : " + stringUID);
                dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Pengguna pengguna = dataSnapshot1.getValue(Pengguna.class);
                            String stringNama = pengguna.getNama();
                            final String stringKey = dataSnapshot1.getKey();
                            final String stringPoin = pengguna.getPoin();
                            System.out.println("data snapshot pengguna : " + dataSnapshot.toString());
                            System.out.println("poin pengguna : " + stringPoin);
                            System.out.println("nama pengguna : " + stringNama);

                            pemilik.setText(stringNama);
//                            status.setText(stringPoin);

                            cekHarga.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String jenis_sampah = String.valueOf(jenisSampah.getSelectedItem());
                                    final String stringHargaSampah = harga.getText().toString().trim();
                                    final String stringBeratSampah = berat.getText().toString().trim();
//                                    final double doubleBeratSampah = Double.valueOf(stringBeratSampah);

                                    hargaBotolPlastik = Double.valueOf(strBotol);
                                    hargaKertas = Double.valueOf(strKertas);
                                    poinBotolPlastik = hargaBotolPlastik / 100;
                                    poinKertas = hargaKertas / 100;
                                    if (jenis_sampah.equalsIgnoreCase("botol plastik")) {
                                        totalPoin = poinBotolPlastik;
                                        hargaSampah = totalPoin * 100;

                                        Toast.makeText(getContext(), "jenis sampah : " + jenis_sampah + ", getSampah " + sampah.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                        sampah.setText(jenis_sampah);

                                        sampah.setEnabled(false);
                                        harga.setEnabled(false);

                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        harga.setText(String.valueOf(hargaSampah));
                                    } else if (jenis_sampah.equalsIgnoreCase("kertas")) {
                                        totalPoin = poinKertas;
                                        hargaSampah = totalPoin * 100;

                                        sampah.setEnabled(false);
                                        harga.setEnabled(false);

                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                        sampah.setText(jenis_sampah);
                                        harga.setText(String.valueOf(hargaSampah));
                                    } else if (jenis_sampah.equalsIgnoreCase("jenis lain")) {
                                        sampah.setEnabled(true);
                                        harga.setEnabled(true);

                                        harga.setText("0");

                                        textHarga.setText("Rp. " + String.valueOf(hargaSampah * Double.valueOf(stringBeratSampah)));
                                        Double doubleHargaSampah = Double.valueOf(stringHargaSampah);
                                        totalPoin = doubleHargaSampah / 100;
                                        hargaSampah = totalPoin * 100;
                                        poin.setText(String.valueOf(totalPoin * Double.valueOf(stringBeratSampah)) + " poin");
                                    }

                                    terima.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dismiss();
                                            Boolean bolehVerifikasi = true;
                                            String stringBerat = berat.getText().toString().trim();
                                            String stringJenisSampah = sampah.getText().toString().trim();
                                            String stringHargaSampah = harga.getText().toString().trim();
                                            Double doubleHarga = Double.valueOf(stringHargaSampah);
                                            Double doublePoinSampah = doubleHarga / 100;
                                            Double dooubleTotalHarga = doubleHarga * Double.valueOf(stringBerat);
                                            final Double doubleTotalPoin = (Double.valueOf(doubleHarga) / 100) * Double.valueOf(stringBerat);

                                            Double doublePoin = Double.valueOf(stringPoin) + doubleTotalPoin;
                                            System.out.println("poin total pengguna : " + String.valueOf(doublePoin));

                                            if (sampah.getText().toString().trim().isEmpty()) {
                                                sampah.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan jenis sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (harga.getText().toString().trim().isEmpty() || harga.getText().toString().trim().equalsIgnoreCase("0")) {
                                                harga.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan harga sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (berat.getText().toString().trim().isEmpty() || berat.getText().toString().trim().equalsIgnoreCase("0")) {
                                                berat.requestFocus();
                                                bolehVerifikasi = false;
                                                Toast.makeText(getContext(), "Masukkan berat sampah", Toast.LENGTH_SHORT).show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }
                                            if (stringStatus.equalsIgnoreCase(ModelSampah.VERIFIKASI_DITERIMA)) {
                                                bolehVerifikasi = false;
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                                        .setCancelable(false)
                                                        .setTitle("Gagal Verifikasi")
                                                        .setMessage("Maaf data sampah dengan kode " + kode + "sudah diverifikasi")
                                                        .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            } else {
                                                bolehVerifikasi = true;
                                            }

                                            if (bolehVerifikasi) {
                                                SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                                                String stringTanggalSubmit = data.format(new Date());
                                                if (stringBerat.isEmpty()) {
                                                    berat.requestFocus();
                                                    berat.setError("Masukan berat sampah terlebih dahulu");
                                                } else {
                                                    dismiss();
                                                    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("jenisSampah").setValue(stringJenisSampah);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("statusVerifikasi").setValue(ModelSampah.VERIFIKASI_DITERIMA);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("idBank").setValue(uid);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("beratSampah").setValue(stringBerat);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("poinSampah").setValue(String.valueOf(doubleTotalPoin));
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("tanggalSubmit").setValue(stringTanggalSubmit);
                                                    firebaseDatabase.getReference("dataSampah").child(kode).child("hargaSampah").setValue(String.valueOf(dooubleTotalHarga));
                                                    firebaseDatabase.getReference("pengguna").child(stringUID).child(stringKey).child("poin").setValue(String.valueOf(doublePoin));

                                                    System.out.println("double poin 1 : " + String.valueOf(doublePoin));
                                                    System.out.println("double poin 2 : " + String.valueOf(doubleTotalPoin));
//                                                    simpanLDR(doubleTotalPoin);

//                                                    System.out.println("simpan data : " + String.valueOf(doubleTotalPoin));
//                                                    double dPoin = Double.valueOf(strPoin) + doubleTotalPoin;
//                                                    double dDesc = Double.valueOf(Util.util_desc_leaderboard) - dPoin;
//                                                    System.out.println("dpoin : " + String.valueOf(dPoin) + ", dDesc : " + String.valueOf(dDesc));
//
//                                                    FirebaseDatabase.getInstance().getReference()
//                                                            .child(Util.util_data_leaderboard)
//                                                            .child(strUID)
//                                                            .child("poin")
//                                                            .setValue(String.valueOf(dPoin));
//                                                    FirebaseDatabase.getInstance().getReference()
//                                                            .child(Util.util_data_leaderboard)
//                                                            .child(strUID)
//                                                            .child("desc")
//                                                            .setValue(String.valueOf(dDesc));

//                                                    ModelPoin modelPoin = new ModelPoin(String.valueOf(doubleTotalPoin),stringUID,)
//                                                    pengguna.setPoin(String.valueOf(doublePoin));

                                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("dataBankSampah");
                                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                                                v_daftarBank_model model2 = dataSnapshot2.getValue(v_daftarBank_model.class);
                                                                String UID = model2.getId();

                                                                String stringNamaInstansi = model2.getNamaInstansi();
                                                                System.out.println("UID bank sampah : " + uid + ", UID data bank : " + UID + ", nama instansi : " + stringNamaInstansi);
                                                                if (UID.equalsIgnoreCase(uid)) {
                                                                    System.out.println("UID bank sampah 2 : " + uid + ", UID data bank 2 : " + UID + ", nama instansi 2 : " + stringNamaInstansi);
                                                                    ModelPoin modelPoin = new ModelPoin(String.valueOf(doubleTotalPoin), stringUID, stringNamaInstansi, "verifikasi sampah");
                                                                    firebaseDatabase.getReference("dataPoin").push().setValue(modelPoin);
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });

                                                    Toast.makeText(getContext(), "jenis : " + stringJenisSampah +
                                                                    ", berat : " + stringBerat + ", poin : " + String.valueOf(doubleTotalPoin) +
                                                                    ", harga : " + String.valueOf(dooubleTotalHarga)
                                                            , Toast.LENGTH_LONG).show();
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                                            .setCancelable(false)
                                                            .setTitle("Sukses Verifikasi")
                                                            .setMessage("Terima kasih, data sampah dengan kode " + kode + " telah berhasil diverifikasi")
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                                        }
                                    });

                                    tolak.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dismiss();
                                        }
                                    });
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
    }

    private void getPemilik() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("dataSampah").child(kode);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ModelSampah m = dataSnapshot.getValue(ModelSampah.class);
                    strUID = m.getUidVolunteer();

                    getPoin();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getPoin() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Util.util_data_leaderboard);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Model_leaderboard m = dataSnapshot.getValue(Model_leaderboard.class);
                    strPoin = m.getPoin();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void simpanLDR(double poinsampah) {
        System.out.println("simpan data : " + poinsampah);
        double dPoin = Double.valueOf(strPoin) + poinsampah;
        double dDesc = Double.valueOf(Util.util_desc_leaderboard) - dPoin;
        System.out.println("dpoin : " + String.valueOf(dPoin) + ", dDesc : " + String.valueOf(dDesc));

        FirebaseDatabase.getInstance().getReference()
                .child(Util.util_data_leaderboard)
                .child(strUID)
                .child("poin")
                .setValue(String.valueOf(dPoin));
        FirebaseDatabase.getInstance().getReference()
                .child(Util.util_data_leaderboard)
                .child(strUID)
                .child("desc")
                .setValue(String.valueOf(dDesc));
    }
}
