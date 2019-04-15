package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.bs_daftarPerusahaan_model;

public class TabDeskripsiFragment extends Fragment {
    private EditText namaProduk, hargaProduk;
    private Button tambah;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference rootRef, demoRef, reference;
    private FirebaseDatabase firebaseDatabase, database;

    private FloatingActionButton ubah, simpan, batal;
    private EditText deskripsi;
    private TextView textDeskripsi;

    public TabDeskripsiFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perusahaan_tab_ubah_harga, container, false);

//        namaProduk = (EditText) view.findViewById(R.id.input_ubahHarga_namaProduk);
//        deskripsi = (EditText) view.findViewById(R.id.input_ubahHarga_deskripsi);
//        hargaProduk = (EditText) view.findViewById(R.id.input_ubahHarga_hargaBaru);

//        tambah = (Button) view.findViewById(R.id.btn_ubahHarga_ubah);
//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strNama = namaProduk.getText().toString().trim();
//                String strHarga = hargaProduk.getText().toString().trim();
//                String strDesk = deskripsi.getText().toString().trim();
//                String strID = FirebaseAuth.getInstance().getUid();
////                tambah(strNama, strHarga, strDesk);
//
//                rootRef = FirebaseDatabase.getInstance().getReference();
//                demoRef = rootRef.child("dataHargaSampah").child(strID);
//
//                if (strNama.isEmpty()) {
//                    namaProduk.requestFocus();
//                    namaProduk.setError("Isi terlebih dahulu");
//                } else if (strHarga.isEmpty()) {
//                    deskripsi.requestFocus();
//                    deskripsi.setError("Isi terlebih dahulu");
//                } else if (strDesk.isEmpty()) {
//                    hargaProduk.requestFocus();
//                    hargaProduk.setError("Isi terlebih dahulu");
//                } else {
//                    demoRef.push().setValue(strNama);
//                }
//            }
//        });
        textDeskripsi = (TextView) view.findViewById(R.id.perusahaan_text_deskripsi);
        final String stringUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference("dataPerusahaan");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    bs_daftarPerusahaan_model model = dataSnapshot1.getValue(bs_daftarPerusahaan_model.class);
                    String desk = model.getDeskripsi();
                    String id = model.getId();
                    System.out.println("uid perusahaan : " + stringUID + " id : " + id + " desk : " + desk);
                    if (id.equalsIgnoreCase(stringUID)) {
                        textDeskripsi.setText(desk);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        deskripsi = (EditText) view.findViewById(R.id.perusahaan_input_deskripsi);
        ubah = (FloatingActionButton) view.findViewById(R.id.perusahaan_btn_ubah);
        simpan = (FloatingActionButton) view.findViewById(R.id.perusahaan_btn_simpan);
        batal = (FloatingActionButton) view.findViewById(R.id.perusahaan_btn_batal);

        deskripsi.setEnabled(false);
        simpan.setVisibility(View.GONE);
        batal.setVisibility(View.GONE);
        ubah.setVisibility(View.VISIBLE);

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deskripsi.setEnabled(true);
                simpan.setVisibility(View.VISIBLE);
                batal.setVisibility(View.VISIBLE);
                ubah.setVisibility(View.GONE);
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringDeskripsi = deskripsi.getText().toString().trim();
                if (stringDeskripsi.isEmpty()) {
                    simpan.setVisibility(View.GONE);
                    batal.setVisibility(View.GONE);
                    ubah.setVisibility(View.VISIBLE);
                    deskripsi.setEnabled(false);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                            .setCancelable(false)
                            .setTitle("Batal Simpan")
                            .setMessage("Batal menyimpan perubahan?")
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    deskripsi.setText("");
                                    deskripsi.setEnabled(false);
                                    simpan.setVisibility(View.GONE);
                                    batal.setVisibility(View.GONE);
                                    ubah.setVisibility(View.VISIBLE);

                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strDesk = deskripsi.getText().toString().trim();
                if (strDesk.isEmpty()) {
                    deskripsi.requestFocus();
                    deskripsi.setError("Jangan dibiarkan kosong");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                            .setCancelable(false)
                            .setTitle("Simpan deskripsi")
                            .setMessage("Deskripsi baru : " + strDesk)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    deskripsi.setText("");
                                    deskripsi.setEnabled(false);
                                    simpan.setVisibility(View.GONE);
                                    batal.setVisibility(View.GONE);
                                    ubah.setVisibility(View.VISIBLE);
                                    database = FirebaseDatabase.getInstance();
                                    reference = database.getReference("dataPerusahaan");
                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                bs_daftarPerusahaan_model model = dataSnapshot1.getValue(bs_daftarPerusahaan_model.class);
                                                String uid = model.getId();
                                                String key = dataSnapshot1.getKey();
                                                System.out.println("datasnapshot_data : " + uid + " " + key);
                                                if (uid.equalsIgnoreCase(stringUID)) {
                                                    firebaseDatabase.getReference("dataPerusahaan").child(key).child("deskripsi").setValue(strDesk);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        initFirebase();

        return view;
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
