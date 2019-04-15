package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.v_daftarBank_adapter;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankSampahFragment extends Fragment {

    private RecyclerView recyclerView;
    private v_daftarBank_adapter adapter;
    private List<v_daftarBank_model> modelList;

    private TextView namaVolunteer, levelVolunteer;

    private FirebaseDatabase database;
    private DatabaseReference reference, dRef;
    private FirebaseAuth auth;

    public BankSampahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeVolunteer) getActivity()).setActionBarTitle("Daftar Bank Sampah");
        View view = inflater.inflate(R.layout.fragment_volunteer_daftar_bank_sampah, container, false);

//        namaVolunteer = (TextView) view.findViewById(R.id.volunteer_bank_nama);
//        levelVolunteer = (TextView) view.findViewById(R.id.volunteer_bank_level);

        database = FirebaseDatabase.getInstance();
//        auth = FirebaseAuth.getInstance();
//        String UID = auth.getUid();
//        dRef = database.getReference("pengguna").child(UID);
//        dRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    Pengguna pengguna = dataSnapshot1.getValue(Pengguna.class);
//                    String nama = pengguna.getNama();
//                    String level = pengguna.getLevel();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        reference = database.getReference("dataBankSampah");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    v_daftarBank_model model = dataSnapshot1.getValue(v_daftarBank_model.class);
                    String pemilik = model.getNamaPemilik();
                    String instansi = model.getNamaInstansi();
                    String telp = model.getTelp();
                    String alamat = model.getAlamat();

                    model = new v_daftarBank_model(null,pemilik, instansi, alamat, telp);
                    modelList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarBankSampah);
        modelList = new ArrayList<>();
        adapter = new v_daftarBank_adapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
