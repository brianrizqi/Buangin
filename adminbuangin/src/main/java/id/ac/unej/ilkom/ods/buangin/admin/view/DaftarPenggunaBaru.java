package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class DaftarPenggunaBaru extends AppCompatActivity {

    private TextInputEditText namaPemilik, namaInstansi, email, password, alamat, telp;
    private Spinner jenisLevel;
    private EditText poin;
    private Button simpan;
    private ImageButton kembali, uploadGambar;
    private ImageView gambar;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    public DaftarPenggunaBaru() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengguna);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        poin = (EditText) findViewById(R.id.daftar_poin);
        jenisLevel = (Spinner) findViewById(R.id.daftar_spinner_level);
        simpan = (Button) findViewById(R.id.daftar_btn_simpan);
        namaPemilik = (TextInputEditText) findViewById(R.id.daftar_nama_pemilik);
        namaInstansi = (TextInputEditText) findViewById(R.id.daftar_nama_instansi);
        email = (TextInputEditText) findViewById(R.id.daftar_email);
        password = (TextInputEditText) findViewById(R.id.daftar_password);
        alamat = (TextInputEditText) findViewById(R.id.daftar_alamat);
        telp = (TextInputEditText) findViewById(R.id.daftar_telepon);
        kembali = (ImageButton) findViewById(R.id.daftar_btn_kembali);
//        gambar = (ImageView) findViewById(R.id.daftar_gambar);
//        uploadGambar = (ImageButton) findViewById(R.id.daftar_btn_upload_gambar);

        poin.setEnabled(false);
        poin.setText("0");

        List<String> stringList = new ArrayList<String>();
        stringList.add("Bank Sampah");
        stringList.add("Mitra UMKM");
        stringList.add("Perusahaan");
        stringList.add("Admin");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisLevel.setSelection(0);
        jenisLevel.setAdapter(adapter);
        jenisLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, final long id) {
                String item = (String) jenisLevel.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                if (item.equalsIgnoreCase("bank sampah")) {
                    poin.setEnabled(true);
                } else if (item.equalsIgnoreCase("mitra umkm")) {
                    poin.setEnabled(false);
                    poin.setText("0");
                } else if (item.equalsIgnoreCase("perusahaan")) {
                    poin.setEnabled(false);
                    poin.setText("0");
                } else if (item.equalsIgnoreCase("admin")) {
                    namaInstansi.setEnabled(false);
                    namaInstansi.setText("");
                    poin.setEnabled(false);
                    poin.setText("0");
                }

                simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String stringPemilik = namaPemilik.getText().toString().trim();
                        final String stringInstansi = namaInstansi.getText().toString().trim();
                        final String stringEmail = email.getText().toString().trim();
                        final String stringPassword = password.getText().toString().trim();
                        final String stringAlamat = alamat.getText().toString().trim();
                        final String stringTelp = telp.getText().toString().trim();
                        final String stringPoin = poin.getText().toString().trim();
                        final String stringLevel = (String) jenisLevel.getItemAtPosition(position);
                        String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        Boolean bolehSimpan = true;

                        if (stringPemilik.isEmpty()) {
                            namaPemilik.requestFocus();
                            namaPemilik.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }
                        if (stringLevel.equalsIgnoreCase("bank sampah")
                                || stringLevel.equalsIgnoreCase("perusahaan")
                                || stringLevel.equalsIgnoreCase("mitra umkm")) {
                            if (stringInstansi.isEmpty()) {
                                namaInstansi.requestFocus();
                                namaInstansi.setError("Isi terlebih dahulu");
                                bolehSimpan = false;
                            } else {
                                bolehSimpan = true;
                            }
                        } else if (stringLevel.equalsIgnoreCase("admin")) {
                            if (stringInstansi.isEmpty()) {
                                bolehSimpan = true;
                            } else {
                                bolehSimpan = false;
                            }
                        }
                        if (stringEmail.isEmpty()) {
                            email.requestFocus();
                            email.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }
                        if (stringPassword.isEmpty()) {
                            password.requestFocus();
                            password.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }
                        if (stringAlamat.isEmpty()) {
                            alamat.requestFocus();
                            alamat.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }
                        if (stringTelp.isEmpty()) {
                            telp.requestFocus();
                            telp.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }
                        if (stringPoin.isEmpty()) {
                            poin.requestFocus();
                            poin.setError("Isi terlebih dahulu");
                            bolehSimpan = false;
                        } else {
                            bolehSimpan = true;
                        }

                        if (bolehSimpan) {
                            if (stringEmail.matches(emailValid)) {
                                firebaseAuth.createUserWithEmailAndPassword(stringEmail, stringPassword)
                                        .addOnCompleteListener(DaftarPenggunaBaru.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                                    String strEmail = user.getEmail();
                                                    String UID = user.getUid();

                                                    if (stringLevel.equalsIgnoreCase("admin")) {
                                                        Pengguna pengguna = new Pengguna(null, stringPemilik, null, null, strEmail, stringPassword, null, null, Pengguna.ADMIN, null, null);
                                                        Pengguna dataAdmin = new Pengguna(UID, stringPemilik, null, null, strEmail, null, stringAlamat, stringTelp, null, null, null);
                                                        databaseReference.child("pengguna").child(UID).push().setValue(pengguna);
                                                        databaseReference.child("dataAdmin").push().setValue(dataAdmin);

                                                        dialogSimpan("Data Pengguna Baru", stringLevel, strEmail);
                                                    } else if (stringLevel.equalsIgnoreCase("bank sampah")) {
                                                        Pengguna pengguna = new Pengguna(null, stringPemilik, null, null, strEmail, stringPassword, null, null, Pengguna.BANK_SAMPAH, stringPoin, null);
                                                        Pengguna dataBankSampah = new Pengguna(UID, null, stringPemilik, stringInstansi, strEmail, null, stringAlamat, stringTelp, null, null, null);

                                                        String strInstansi = stringInstansi.replace(" ", "").replace(".", "").replace(",", "").toLowerCase();
                                                        String strUid = UID.substring(0, 3).toLowerCase();
                                                        String kodeBankSampah = strUid + strInstansi;

                                                        databaseReference.child("pengguna").child(UID).push().setValue(pengguna);
                                                        databaseReference.child("dataBankSampah").child(kodeBankSampah).setValue(dataBankSampah);

                                                        dialogSimpan("Data Pengguna Baru", stringLevel, strEmail);
                                                    } else if (stringLevel.equalsIgnoreCase("perusahaan")) {
                                                        Pengguna pengguna = new Pengguna(null, stringPemilik, null, null, strEmail, stringPassword, null, null, Pengguna.PERUSAHAAN, null, null);
                                                        Pengguna dataPerusahaan = new Pengguna(UID, null, stringPemilik, stringInstansi, strEmail, null, stringAlamat, stringTelp, null, null, "-");
                                                        databaseReference.child("pengguna").child(UID).push().setValue(pengguna);
                                                        databaseReference.child("dataPerusahaan").push().setValue(dataPerusahaan);

                                                        dialogSimpan("Data Pengguna Baru", stringLevel, strEmail);
                                                    } else if (stringLevel.equalsIgnoreCase("mitra umkm")) {
                                                        Pengguna pengguna = new Pengguna(null, stringPemilik, null, null, strEmail, stringPassword, null, null, Pengguna.MITRA, null, null);
                                                        Pengguna dataMitra = new Pengguna(UID, null, stringPemilik, stringInstansi, strEmail, null, stringAlamat, stringTelp, null, null, null);
                                                        databaseReference.child("pengguna").child(UID).push().setValue(pengguna);
                                                        databaseReference.child("dataMitra").push().setValue(dataMitra);

                                                        dialogSimpan("Data Pengguna Baru", stringLevel, strEmail);
                                                    }
                                                } else {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPenggunaBaru.this)
                                                            .setCancelable(false)
                                                            .setTitle("Gagal Simpan")
                                                            .setMessage("Tidak ada koneksi internet")
                                                            .setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }
                                            }
                                        });
                            } else {
                                email.requestFocus();
                                email.setError("E-Mail tidak valid");
                                bolehSimpan = false;
                            }
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringPemilik = namaPemilik.getText().toString().trim();
                String stringInstansi = namaInstansi.getText().toString().trim();
                String stringEmail = email.getText().toString().trim();
                String stringPassword = password.getText().toString().trim();
                String stringAlamat = alamat.getText().toString().trim();
                String stringTelp = telp.getText().toString().trim();

                if (stringPemilik.isEmpty() || stringInstansi.isEmpty() || stringEmail.isEmpty() || stringPassword.isEmpty()
                        || stringAlamat.isEmpty() || stringTelp.isEmpty()) {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPenggunaBaru.this)
                            .setCancelable(false)
                            .setTitle("Batal simpan")
                            .setMessage("Batal simpan data pengguna baru?")
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    startActivity(new Intent(getApplicationContext(), Home.class));
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    private void dialogSimpan(String judul, String userLevel, String userEmail) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPenggunaBaru.this)
                .setCancelable(false)
                .setTitle(judul)
                .setMessage("Data " + userLevel + " dengan alamat E-Mail " + userEmail + " telah berhasil ditambahkan")
                .setPositiveButton("Tambah Lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        namaPemilik.setText("");
                        namaInstansi.setText("");
                        email.setText("");
                        alamat.setText("");
                        telp.setText("");
                        password.setText("");
                        poin.setText("0");
                        jenisLevel.setSelection(0);
                    }
                })
                .setNegativeButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
