package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.AdminAdapter;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class FragmentAdmin extends Fragment {
    private RecyclerView recyclerView;

    private List<Pengguna> penggunaList;
    private AdminAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_admin);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("dataAdmin");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                penggunaList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                    String nama = model.getNama();
                    String email = model.getEmail();
                    String alamat = model.getAlamat();
                    String telp = model.getTelp();

                    model = new Pengguna(null, nama, null, null, email, null, alamat, telp, null, null,null);
                    penggunaList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        penggunaList = new ArrayList<>();
        adapter = new AdminAdapter(getContext(), penggunaList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
