package id.ac.unej.ilkom.ods.buangin.view.BankSampah;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.TabAdapter;

public class VerifikasiFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabVerifikasiFragment verifikasi;
    private TabRiwayatFragment riwayat;

    public VerifikasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_sampah_verifikasi, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_verifikasi);
        viewPager = (ViewPager) view.findViewById(R.id.pager_verifikasi);
        verifikasi = new TabVerifikasiFragment();
        riwayat = new TabRiwayatFragment();

        TabAdapter verifikasiAdapter = new TabAdapter(getChildFragmentManager());
        verifikasiAdapter.tambahFragment(verifikasi, "Verifikasi");
        verifikasiAdapter.tambahFragment(riwayat, "Riwayat");

        viewPager.setAdapter(verifikasiAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
