package id.ac.unej.ilkom.ods.buangin.view.Mitra;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //    ListView listMenu;
//    OnMenuClickListener mCallback;
    CardView mDaftarVoucher, mRiwayatPenukaran;

    public HomeFragment() {
        // Required empty public constructor
    }
//
//    public interface OnMenuClickListener {
//        void onMenuSelected(int position);
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

//        try {
//            mCallback = (OnMenuClickListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " Must Implement Listener");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeMitra) getActivity()).setActionBarTitle("Halaman Menu");
        View rootView = inflater.inflate(R.layout.fragment_mitra_home, container, false);

        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        mDaftarVoucher = (CardView) rootView.findViewById(R.id.cv_daftar_voucher);
        mRiwayatPenukaran = (CardView) rootView.findViewById(R.id.cv_riwayat_penukaran);

        mDaftarVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.container_mitra, new DaftarVoucherFragment()).commit();
            }
        });

        mRiwayatPenukaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.container_mitra, new RiwayatPenukaranFragment()).commit();
            }
        });

//        listMenu = rootView.findViewById(R.id.daftar_utama_mitra);
//        List<DaftarMenu> menu = new ArrayList<>();
//        menu.add(new DaftarMenu(1, "Daftar ModelVoucher", R.drawable.ic_attach_money_black_24dp));
//        menu.add(new DaftarMenu(2, "Riwayat Penukaran ModelVoucher", R.drawable.ic_history_black_24dp));
//
//        DaftarMenuAdapter adapter = new DaftarMenuAdapter(getActivity(), menu);
//        listMenu.setAdapter(adapter);
//
//        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mCallback.onMenuSelected(position);
//            }
//        });
        return rootView;
    }

}
