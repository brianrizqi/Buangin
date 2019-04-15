package id.ac.unej.ilkom.ods.buangin.view.Mitra;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.BaseApi;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.util.Util;

import static android.app.Activity.RESULT_OK;
import static id.ac.unej.ilkom.ods.buangin.util.Util.REQUEST_IMAGE_CAPTURE;
import static id.ac.unej.ilkom.ods.buangin.util.Util.WRITE_EXTERNAL;

public class TabBuatVoucherFragment extends Fragment {

    private final String TAG = TabBuatVoucherFragment.class.getSimpleName();

    private EditText namaProduk, deskripsi, poin, kuota;
    private ImageView voucher;
    private Button tambah, batal;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    private Bitmap hasilFoto;
    private Uri uriFoto;
    private String uid;
    private String namaMitra;

    public TabBuatVoucherFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mitra_tab_buat_voucher, container, false);

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference();

        namaProduk = (EditText) view.findViewById(R.id.input_voucher_namaProduk);
        deskripsi = (EditText) view.findViewById(R.id.input_voucher_deskripsi);
        poin = (EditText) view.findViewById(R.id.input_voucher_poin);
        kuota = (EditText) view.findViewById(R.id.input_voucher_jumlahKuota);
        voucher = (ImageView) view.findViewById(R.id.foto_produk_voucher);

        batal = (Button) view.findViewById(R.id.btn_voucher_batal);
        tambah = (Button) view.findViewById(R.id.btn_voucher_tambah);

        dbRef.child("pengguna").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    namaMitra = data.getValue(Pengguna.class).getNama();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
                } else {
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strNama = namaProduk.getText().toString().trim();
                final String strDeskripsi = deskripsi.getText().toString().trim();
                final String strPoin = poin.getText().toString().trim();
                final String strKuota = kuota.getText().toString().trim();


                boolean kirim = false;

                if (strNama.isEmpty()) {
                    namaProduk.requestFocus();
                    namaProduk.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strDeskripsi.isEmpty()) {
                    deskripsi.requestFocus();
                    deskripsi.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strPoin.isEmpty()) {
                    poin.requestFocus();
                    poin.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strKuota.isEmpty()) {
                    kuota.requestFocus();
                    kuota.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (kirim) {
                    final StorageReference ref = FirebaseStorage.getInstance().getReference(BaseApi.DIR_FOTO_VOUCHER).child(uriFoto.getLastPathSegment());

                    UploadTask uploadFoto = ref.putFile(uriFoto);

                    Task<Uri> uploading = uploadFoto.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Gagal upload foto voucher:" + task.getException());
                            }
                            return ref.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String urlDownload = task.getResult().toString();
                                ModelVoucher voucher = new ModelVoucher(uid, namaMitra, urlDownload, strNama, strDeskripsi, strPoin, strKuota, ModelVoucher.VOUCHER_BERLAKU);
                                dbRef.child("dataVoucher").child(Util.kode_voucher()).setValue(voucher);
                                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "Gagal mengambil url voucher");
                            }
                        }
                    });
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ModelVoucher");
                builder.setMessage("Batal menambahkan voucher?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        namaProduk.setText("");
                        deskripsi.setText("");
                        poin.setText("");
                        kuota.setText("");
                        voucher.setImageResource(R.drawable.bg_img_voucher);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            hasilFoto = (Bitmap) data.getExtras().get("data");
            uriFoto = getImageUri(getContext(), hasilFoto);

            voucher.setImageBitmap(hasilFoto);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        return Uri.parse(path);
    }

    private void kirimStorage(final StorageReference storageReference, Uri uri, final String namaVoucher, final String deskripsi, final String hargaPoin, final String jumlahKuota, final String uid, final String key) {
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    String url = storageReference.getDownloadUrl().toString();
                    Log.d("TabBuatVoucherFragment", "URL foto ModelVoucher: " + url);

                    ModelVoucher voucher = new ModelVoucher(uid, namaMitra, url, namaVoucher, deskripsi, hargaPoin, jumlahKuota, ModelVoucher.VOUCHER_BERLAKU);
                    dbRef.child("dataVoucher").child(key).setValue(voucher);
                } else {
                    Log.w("TabBuatVoucherFragment", "Gagal Upload Foto" + task.getException());
                    Toast.makeText(getContext(), "Gagal Upload Foto", Toast.LENGTH_LONG).show();
                }
            }
        });

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
