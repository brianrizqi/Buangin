package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.VolunteerAdapter;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class FragmentVolunteer extends Fragment {
    private RecyclerView recyclerView;

    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private VolunteerAdapter adapter;
    private List<Pengguna> penggunaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteer, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataVolunteer");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                penggunaList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                    String nama = model.getNama();
                    String email = model.getEmail();
                    System.out.println("volunteer nama : " + nama);
                    System.out.println("volunteer email : " + email);

                    model = new Pengguna(null, nama, null, null, email, null, null, null, null, null, null);
                    penggunaList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_volunteer);
        penggunaList = new ArrayList<>();
        adapter = new VolunteerAdapter(getContext(), penggunaList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
