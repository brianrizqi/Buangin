package id.ac.unej.ilkom.ods.buangin.view.Mitra;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.VoucherAdapter;
import id.ac.unej.ilkom.ods.buangin.model.BaseApi;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDaftarVoucherFragment extends Fragment {

    private static final String TAG = TabDaftarVoucherFragment.class.getSimpleName();

    private List<ModelVoucher> listVoucher;
    private RecyclerView recyclerView;
    private ModelVoucher voucher;

    private DatabaseReference dbRef;
    private FirebaseAuth auth;

    public TabDaftarVoucherFragment() {
        // Required empty public constructor
    }

    String UID = "";
    VoucherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((HomeMitra) getActivity()).setActionBarTitle("Daftar ModelVoucher");
        View view = inflater.inflate(R.layout.fragment_mitra_tab_daftar_voucher, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarVoucher);

        UID = FirebaseAuth.getInstance().getUid();

        get_data(UID);
        System.out.println("UID MITRA : "+UID);
        listVoucher = new ArrayList<>();
        adapter = new VoucherAdapter(getContext(), listVoucher);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void get_data(final String UID) {
        dbRef =FirebaseDatabase.getInstance().getReference().child("dataVoucher");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    listVoucher.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        ModelVoucher m = snapshot.getValue(ModelVoucher.class);
                        String nama=m.getNamaVoucher();
                        String jumlah=m.getJumlahKuota();
                        String url =m.getUrl_foto();
                        String harga=m.getHargaPoin();
                        String uid =m.getUidMitra();
                        System.out.println("uid mitra : "+uid);

                        if (uid.equalsIgnoreCase(UID)){
                            m=new ModelVoucher(null,null,url,nama,null,harga,jumlah,null);
                            listVoucher.add(m);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
