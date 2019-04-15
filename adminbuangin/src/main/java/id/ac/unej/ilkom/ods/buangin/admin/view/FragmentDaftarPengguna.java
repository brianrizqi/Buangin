package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.adapter.TabAdapter;

public class FragmentDaftarPengguna extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton tambah;

    private Spinner jenis_pengguna;

    FragmentTransaction ft;
    FragmentManager fm;
    FragmentVolunteer volunteer;
    FragmentBankSampah bankSampah;
    FragmentMitra mitra;
    FragmentPerusahaan perusahaan;
    FragmentAdmin admin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_pengguna, container, false);

//        tabLayout = (TabLayout) view.findViewById(R.id.tab_daftar_pengguna);
//        viewPager = (ViewPager) view.findViewById(R.id.pager_dafftar_pengguna);
//
//        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
//        adapter.tambahFragment(new FragmentAdmin(), "1");
//        adapter.tambahFragment(new FragmentVolunteer(), "2");
//        adapter.tambahFragment(new FragmentBankSampah(), "3");
//        adapter.tambahFragment(new FragmentMitra(), "4");
//        adapter.tambahFragment(new FragmentPerusahaan(), "5");
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        volunteer = new FragmentVolunteer();
        bankSampah = new FragmentBankSampah();
        mitra = new FragmentMitra();
        perusahaan = new FragmentPerusahaan();
        admin = new FragmentAdmin();

        jenis_pengguna = (Spinner) view.findViewById(R.id.spin_jenis_pengguna);
        List<String> list = new ArrayList<String>();
        list.add("Volunteer");
        list.add("Bank Sampah");
        list.add("Mitra UMKM");
        list.add("Perusahaan");
        list.add("Admin");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis_pengguna.setSelection(0);
        jenis_pengguna.setAdapter(adapter);
        jenis_pengguna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) jenis_pengguna.getItemAtPosition(position);
                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
                if (item.equalsIgnoreCase("volunteer")) {
                    fm.beginTransaction().replace(R.id.pengguna_frameLayout, volunteer).commit();
                } else if (item.equalsIgnoreCase("bank sampah")) {
                    fm.beginTransaction().replace(R.id.pengguna_frameLayout, bankSampah).commit();
                } else if (item.equalsIgnoreCase("mitra umkm")) {
                    fm.beginTransaction().replace(R.id.pengguna_frameLayout, mitra).commit();
                } else if (item.equalsIgnoreCase("perusahaan")) {
                    fm.beginTransaction().replace(R.id.pengguna_frameLayout, perusahaan).commit();
                } else if (item.equalsIgnoreCase("admin")) {
                    fm.beginTransaction().replace(R.id.pengguna_frameLayout, admin).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tambah = (FloatingActionButton) view.findViewById(R.id.daftar_pengguna_btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), DaftarPenggunaBaru.class));
            }
        });

        return view;
    }
}
