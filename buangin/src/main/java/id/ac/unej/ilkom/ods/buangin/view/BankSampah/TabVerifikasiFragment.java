package id.ac.unej.ilkom.ods.buangin.view.BankSampah;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.Verifikasi_Adapter;
import id.ac.unej.ilkom.ods.buangin.model.ModelSampah;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabVerifikasiFragment extends Fragment {

    private RecyclerView recyclerView;
    private Verifikasi_Adapter adapter;
    private List<ModelSampah> modelList;
    private EditText cari_kode;
    private Button cek;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public TabVerifikasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_sampah_tab_verifikasi, container, false);

        cari_kode = (EditText) view.findViewById(R.id.input_kode);
        cek = (Button) view.findViewById(R.id.btn_cek_kode);
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringKode = cari_kode.getText().toString().trim();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("dataSampah").child(stringKode);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Dialog_verifikasi_sampah d = new Dialog_verifikasi_sampah(getActivity());
                            d.setCancelable(true);
                            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            d.getKode(stringKode);
                            d.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setCancelable(false)
                                    .setTitle("buang.in")
                                    .setMessage("Data sampah tidak tersedia, periksa kembali kodemu :)")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
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
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_verifikasi);
        modelList = new ArrayList<>();
        adapter = new Verifikasi_Adapter(modelList, getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
