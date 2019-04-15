package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.TabAdapter;

public class DeskripsiFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabDeskripsiFragment ubahHarga;
    private TabDaftarBankFragment daftarHarga;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public DeskripsiFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ((HomePerusahaan) getActivity()).setActionBarTitle("Ubah Harga");
        View view = inflater.inflate(R.layout.fragment_perusahaan_ubah_harga, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_ubah_harga);
        viewPager = (ViewPager) view.findViewById(R.id.pager_ubah_harga);
        ubahHarga = new TabDeskripsiFragment();
        daftarHarga = new TabDaftarBankFragment();

        TabAdapter ubahHargaAdapter = new TabAdapter(getChildFragmentManager());
        ubahHargaAdapter.tambahFragment(daftarHarga, "Daftar Bank Sampah");
        ubahHargaAdapter.tambahFragment(ubahHarga, "Infoku");

        viewPager.setAdapter(ubahHargaAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
