package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

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
import id.ac.unej.ilkom.ods.buangin.adapter.p_ubahHarga_adapter;
import id.ac.unej.ilkom.ods.buangin.model.p_ubahHarga_model;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

public class TabDaftarBankFragment extends Fragment {
    private RecyclerView recyclerView;
    private p_ubahHarga_adapter adapter;
    private List<v_daftarBank_model> modelList;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public TabDaftarBankFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perusahaan_tab_daftar_harga, container, false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("dataBankSampah");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    v_daftarBank_model model = dataSnapshot1.getValue(v_daftarBank_model.class);
                    String instansi = model.getNamaInstansi();
                    String pemilik = model.getNamaPemilik();
                    String alamat = model.getAlamat();
                    String telp = model.getTelp();
                    model = new v_daftarBank_model(null, pemilik, instansi, alamat, telp);
                    modelList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_perusahaan);
        modelList = new ArrayList<>();
        adapter = new p_ubahHarga_adapter(getActivity(), modelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
