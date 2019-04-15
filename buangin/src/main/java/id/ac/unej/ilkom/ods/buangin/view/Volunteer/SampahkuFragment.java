package id.ac.unej.ilkom.ods.buangin.view.Volunteer;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.adapter.SampahAdapter;
import id.ac.unej.ilkom.ods.buangin.adapter.v_daftarSampah_adapter;
import id.ac.unej.ilkom.ods.buangin.model.ModelSampah;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarSampah_model;

import static android.app.Activity.RESULT_OK;
import static id.ac.unej.ilkom.ods.buangin.util.Util.REQUEST_IMAGE_CAPTURE;
import static id.ac.unej.ilkom.ods.buangin.util.Util.WRITE_EXTERNAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampahkuFragment extends Fragment {

    //    private ImageButton buka_kamera;
    private static final int req = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    String name = "";
    private RecyclerView recyclerView;
    private SampahAdapter adapter;
    private List<ModelSampah> modelList;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private FloatingActionButton buka_kamera;
    private TabVoucherFragment voucherFragment;
    private TabPoinFragment poinFragment;

    public SampahkuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeVolunteer) getActivity()).setActionBarTitle("Sampahku");
        View view = inflater.inflate(R.layout.fragment_volunteer_sampah, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daftarMySampah);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbRef = database.getReference().child("dataSampah");
        final String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("broto UID : " + UID);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    modelList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ModelSampah model = dataSnapshot1.getValue(ModelSampah.class);
                        String stringStatus = model.getStatusVerifikasi();
                        String stringUID = model.getUidVolunteer();
                        String stringKode = model.getKodeSampah();
                        String stringAmbil = model.getTanggalSubmit();
                        String stringAkhit = model.getTanggalAkhir();

                        if (stringStatus.equalsIgnoreCase("menunggu") && stringUID.equalsIgnoreCase(UID)) {
                            System.out.println("UID pengguna : " + stringUID + " = " + UID + " = " + stringStatus);
                            model = new ModelSampah(stringKode, null, null, null, null, null, null, stringAmbil, stringAkhit, null, null, stringStatus);
                            modelList.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        modelList = new ArrayList<>();
        adapter = new SampahAdapter(getActivity(), modelList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        buka_kamera = (FloatingActionButton) view.findViewById(R.id.btn_sampah_buka_kamera);
        buka_kamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                cekIzin();
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("CAMERA : ", "SUCCESS");
            Bitmap thumb = (Bitmap) data.getExtras().get("data");
            Uri fotoURI = getImageUri(getContext(), thumb);

            DialogSampah dialog = new DialogSampah();

            dialog.setImg(thumb);
            dialog.setImgUri(fotoURI);
            dialog.show(getChildFragmentManager(), "Konfirmasi ModelSampah");
            Toast.makeText(getContext(), "sukses", Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        return Uri.parse(path);
    }

    private void cekIzin() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("Fragment Sampahku", "Masuk resul");
        switch (requestCode) {
            case WRITE_EXTERNAL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Fragment Sampahku", "disetujui");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
        }
    }
}
