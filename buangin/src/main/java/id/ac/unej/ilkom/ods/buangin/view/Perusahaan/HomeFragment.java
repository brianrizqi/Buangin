package id.ac.unej.ilkom.ods.buangin.view.Perusahaan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView mUbahHarga;
    private ImageButton btnInfo;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ((HomePerusahaan)getActivity()).setActionBarTitle("Menu Perusahaan");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perusahaan_home, container, false);
        mUbahHarga = (CardView) view.findViewById(R.id.cv_ubahHarga);
        mUbahHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_perusahaan, new DeskripsiFragment());
                ft.commit();
            }
        });

        btnInfo = (ImageButton) view.findViewById(R.id.perusahaan_btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                reference = database.getReference("pengguna").child(UID);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                            String nama = model.getNama().toUpperCase();
                            String email = model.getEmail().toUpperCase();
                            String level = model.getLevel().toUpperCase();

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                    .setCancelable(false)
                                    .setTitle("Data Pengguna")
                                    .setMessage(level + " atas nama " + nama + " dengan alamat e-Mail " + email)
                                    .setPositiveButton("tutup", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }
}
