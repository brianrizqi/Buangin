package id.ac.unej.ilkom.ods.buangin.view.Volunteer.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.volunteer.Volunteer_adapter_leaderboard;
import id.ac.unej.ilkom.ods.buangin.model.Model_leaderboard;
import id.ac.unej.ilkom.ods.buangin.util.Util;

public class Volunteer_leaderboard extends Fragment {
    private RecyclerView rvLeader;

    private List<Model_leaderboard> model;
    private Volunteer_adapter_leaderboard adapter;

    private DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.volunteer_leaderboard,container,false);

        rvLeader = view.findViewById(R.id.volunteer_leaderboard_recycleview);

        getData();
        model = new ArrayList<>();
        adapter = new Volunteer_adapter_leaderboard(getContext(), model);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvLeader.setLayoutManager(layoutManager);
        rvLeader.setAdapter(adapter);

        return view;
    }

    private void getData() {
        reference = FirebaseDatabase.getInstance().getReference().child(Util.util_data_leaderboard);
        reference.orderByChild("desc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    model.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Model_leaderboard m = snapshot.getValue(Model_leaderboard.class);
                        String nama = m.getNama();
                        String poin = m.getPoin();
                        String desc = m.getDesc();

                        m = new Model_leaderboard(nama, poin, desc, null);
                        model.add(m);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Data leaderboard belum tersedia :)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
