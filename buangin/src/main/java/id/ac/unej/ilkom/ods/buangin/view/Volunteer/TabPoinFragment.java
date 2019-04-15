package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.PoinAdapter;
import id.ac.unej.ilkom.ods.buangin.adapter.v_poin_adapter;
import id.ac.unej.ilkom.ods.buangin.model.ModelPoin;
import id.ac.unej.ilkom.ods.buangin.model.v_poin_model;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabPoinFragment extends Fragment {

    private RecyclerView recyclerView;
    private PoinAdapter poinAdapter;
    private List<ModelPoin> modelList;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public TabPoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteer_tab_poin, container, false);

        final String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dataPoin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelPoin model = dataSnapshot1.getValue(ModelPoin.class);
                    String stringPoin = model.getPoin();
                    String stringDari = model.getDari();
                    String stringUID = model.getUidVolunteer();
                    String stringSumber = model.getSumber();

//                    model = new ModelPoin(stringPoin, null, stringDari, stringSumber);
//                    modelList.add(model);
                    if (stringUID.equalsIgnoreCase(UID)) {
                        model = new ModelPoin(stringPoin, null, stringDari, stringSumber);
                        modelList.add(model);
                    }
                }
                poinAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_poin);
        modelList = new ArrayList<>();
        poinAdapter = new PoinAdapter(getActivity(), modelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(poinAdapter);

        return view;
    }
}
