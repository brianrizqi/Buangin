package id.ac.unej.ilkom.ods.buangin.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.Model_leaderboard;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.util.Util;

//import gambar bergerak

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class HalamanDaftar extends AppCompatActivity {

    /*
     * TODO pendaftaran semua akun melalui online, volunteer, bank sampah, mitra, perusahaan
     * Termasuk pengecekan dan verifikasi mereka
     */
    private ViewGroup mainLayout;
    private ImageView tong;
    private static String TAG = HalamanMasuk.class.getSimpleName();
    private TextInputEditText nama, email, password;
    private Button daftar;
    private ImageView msgRegister;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private Button btninfo;
    private Button btnclose;


    //gambarbergerak
    private int xDelta;
    private int yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        nama = (TextInputEditText) findViewById(R.id.input_daftar_nama);
        email = (TextInputEditText) findViewById(R.id.input_daftar_email);
        password = (TextInputEditText) findViewById(R.id.input_daftar_password);
        daftar = (Button) findViewById(R.id.btn_datftarDaftar);
        msgRegister = findViewById(R.id.msgregister);
        btninfo = findViewById(R.id.btn_info);
        btnclose = findViewById(R.id.btn_close);

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        tong = (ImageView) findViewById(R.id.tong);

        tong.setOnTouchListener(onTouchListener());

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View overlay = findViewById(R.id.loading_overlay);
                final Button btnDaftar = daftar;
                btnDaftar.setEnabled(false);
                overlay.setVisibility(View.VISIBLE);
                boolean bolehDaftar = true;
                final String strNama = nama.getText().toString().trim();
                String strEmail = email.getText().toString().trim();
                String strPassword = password.getText().toString().trim();

                String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (strNama.isEmpty()) {
                    nama.requestFocus();
                    bolehDaftar = false;
                    nama.setError("Isi terlebih dahulu!");
                    overlay.setVisibility(View.GONE);
                    btnDaftar.setEnabled(true);
                } else {
                    bolehDaftar = true;
                }

                if (strEmail.isEmpty()) {
                    email.requestFocus();
                    bolehDaftar = false;
                    email.setError("Isi terlebih dahulu!");
                    overlay.setVisibility(View.GONE);
                    btnDaftar.setEnabled(true);
                } else {
                    bolehDaftar = true;
                }

                if (strPassword.isEmpty()) {
                    password.requestFocus();
                    bolehDaftar = false;
                    password.setError("Isi terlebih dahulu!");
                    overlay.setVisibility(View.GONE);
                    btnDaftar.setEnabled(true);
                } else {
                    bolehDaftar = true;
                }

                if (bolehDaftar) {


                    if (strEmail.matches(emailValid)) {
                        if (strPassword.length() < 6) {
                            password.requestFocus();
                            password.setError("Password min 6 karakter");
                            overlay.setVisibility(View.GONE);
                            btnDaftar.setEnabled(true);
                        } else {
                            auth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(HalamanDaftar.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                        String email = user.getEmail();
                                        final String uid = user.getUid();

                                        Toast.makeText(HalamanDaftar.this, "email : " + email, Toast.LENGTH_LONG).show();
                                        Pengguna daftar = new Pengguna(null, strNama, email, Pengguna.VOLUNTEER, "0");
                                        Pengguna dataVolunteer = new Pengguna(uid, strNama, email, null, null);

//                                        simpan ke data leaderboard leader
                                        simpanLDR(strNama, uid);

                                        dbRef.child("pengguna").child(uid).push().setValue(daftar);
                                        dbRef.child("dataVolunteer").child(uid).setValue(dataVolunteer)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            FirebaseDatabase.getInstance().getReference().child("dataVolunteer")
                                                                    .child(uid).child("poin").setValue("0");
                                                        }
                                                    }
                                                });

                                        Toast.makeText(HalamanDaftar.this, "Pendaftaran Berhasil", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(HalamanDaftar.this, HalamanMasuk.class));
                                    } else if (!task.isSuccessful()) {
                                        overlay.setVisibility(View.GONE);
                                        btnDaftar.setEnabled(true);
                                        if (!cekJaringan()) {
                                            Toast.makeText(HalamanDaftar.this, "Tidak Ada Koneksi Internet", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(HalamanDaftar.this, "Pendaftaran Gagal", Toast.LENGTH_LONG).show();
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        }
                                    }

                                }
                            });
                        }
                    } else {
                        overlay.setVisibility(View.GONE);
                        btnDaftar.setEnabled(true);
                        email.requestFocus();
                        email.setError("E-mail tidak valid");
                    }
                }
            }

        });

        msgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgRegister.setVisibility(View.GONE);

            }
        });

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tong.setVisibility(View.VISIBLE);
                btninfo.setVisibility(View.GONE);
                btnclose.setVisibility(View.VISIBLE);
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgRegister.setVisibility(View.GONE);
                tong.setVisibility(View.GONE);
                btninfo.setVisibility(View.VISIBLE);
                btnclose.setVisibility(View.GONE);
            }
        });


    }

    private void simpanLDR(String nama, String UID) {
        Model_leaderboard m = new Model_leaderboard(nama, "0", Util.util_desc_leaderboard, UID);
        FirebaseDatabase.getInstance()
                .getReference()
                .child(Util.util_data_leaderboard)
                .child(UID)
                .setValue(m);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Batal daftar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama.setText("");
                email.setText("");
                password.setText("");
                startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                HalamanDaftar.this.finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    //gambar bergerak

    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {


//                msgemail.setVisibility(View.VISIBLE);
//                msgpass.setVisibility(View.GONE);
//                msgdaftar.setVisibility(View.GONE);
//                msgreminder.setVisibility(View.GONE);
//                tgemail.setVisibility(View.VISIBLE);
//                tgpass.setVisibility(View.GONE);
//                tgdaftar.setVisibility(View.GONE);


                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                msgRegister.setVisibility(View.VISIBLE);

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(HalamanDaftar.this,
                                "HALO AKU TONG! AKU BISA DIGESER LOH", Toast.LENGTH_SHORT)
                                .show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        };
    }

    private boolean cekJaringan() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
