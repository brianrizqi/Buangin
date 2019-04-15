package id.ac.unej.ilkom.ods.buangin.view.Volunteer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.TabAdapter;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;

public class InfoPoinFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private TextView nama, poin, level;

    public InfoPoinFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeVolunteer) getActivity()).setActionBarTitle("Info Poin");

        View view = inflater.inflate(R.layout.fragment_volunteer_info_poin, container, false);

        nama = (TextView) view.findViewById(R.id.infopoin_nama);
        poin = (TextView) view.findViewById(R.id.infopoin_poin);
        level = (TextView) view.findViewById(R.id.infopoin_level);

        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pengguna").child(UID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                    String stringNama = model.getNama();
                    String stringLevel = model.getLevel();
                    String stringPoin = model.getPoin();

                    nama.setText(stringNama);
                    level.setText(stringLevel);
                    poin.setText(stringPoin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tabLayout = (TabLayout) view.findViewById(R.id.tab_infopoin);
        viewPager = (ViewPager) view.findViewById(R.id.pager_infopoin);

        TabAdapter infopoin_adapter = new TabAdapter(getChildFragmentManager());
        infopoin_adapter.tambahFragment(new TabPoinFragment(), "Poin");
        infopoin_adapter.tambahFragment(new TabVoucherFragment(), "Voucher");
        infopoin_adapter.tambahFragment(new TabRiwayatFragment(), "Riwayat");

        viewPager.setAdapter(infopoin_adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
