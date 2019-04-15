package id.ac.unej.ilkom.ods.buangin.view.BankSampah;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.bs_daftarPerusahaan_adapter;
import id.ac.unej.ilkom.ods.buangin.model.bs_daftarPerusahaan_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarPerusahaanFragment extends Fragment {

    private RecyclerView recyclerView;
    private bs_daftarPerusahaan_adapter adapter;
    private List<bs_daftarPerusahaan_model> modelList;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public DaftarPerusahaanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_sampah_daftar_perusahaan, container, false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("dataPerusahaan");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    bs_daftarPerusahaan_model model = dataSnapshot1.getValue(bs_daftarPerusahaan_model.class);
                    String instansi = model.getNamaInstansi();
                    String pemilik = model.getNamaPemilik();
                    String alamat = model.getAlamat();
                    String deskripsi = model.getDeskripsi();

                    model = new bs_daftarPerusahaan_model(instansi, pemilik, alamat, deskripsi, null);
                    modelList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarPerusahaan);
        modelList = new ArrayList<>();
        adapter = new bs_daftarPerusahaan_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
