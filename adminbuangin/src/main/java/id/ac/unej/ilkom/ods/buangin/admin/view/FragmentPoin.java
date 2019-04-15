package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.PoinAdapter;
import id.ac.unej.ilkom.ods.buangin.admin.model.ModelPenjualan;

public class FragmentPoin extends Fragment {
    private RecyclerView recyclerView;
    private ImageButton cek_kode_bank;
    private EditText kode_bank;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private PoinAdapter adapter;
    private List<ModelPenjualan> penjualanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poin, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataPenjualan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                penjualanList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelPenjualan model = dataSnapshot1.getValue(ModelPenjualan.class);
                    String strPemilik = model.getNamaPemilik();
                    String strInstansi = model.getNamaInstansi();
                    String strTanggal = model.getTanggalBeli();
                    String strPoin = model.getJumlahPoin();

                    System.out.println("data penjualan : " + strPemilik + strInstansi + strTanggal + strPoin);

                    model = new ModelPenjualan(strPemilik, strInstansi, strPoin, strTanggal);
                    penjualanList.add(model);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_poin);
        kode_bank = (EditText) view.findViewById(R.id.poin_input_kode_bank);
        cek_kode_bank = (ImageButton) view.findViewById(R.id.poin_btn_cek_bank);

        cek_kode_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kode = kode_bank.getText().toString().trim();
                if (kode.isEmpty()) {
                    kode_bank.requestFocus();
                    kode_bank.setError("Isi kode bank sampah");
                } else {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("dataBankSampah").child(kode);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                DialogTambahPoin tambahPoin = new DialogTambahPoin();
                                tambahPoin.setKode(kode);
                                tambahPoin.show(getChildFragmentManager(), "tambah poin");
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                        .setCancelable(false)
                                        .setTitle("Gagal")
                                        .setMessage("Data bank sampah dengan kode " + kode + " tidak ditemukan")
                                        .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                                kode_bank.setText("");
                                            }
                                        });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        penjualanList = new ArrayList<>();
        adapter = new PoinAdapter(getContext(), penjualanList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
