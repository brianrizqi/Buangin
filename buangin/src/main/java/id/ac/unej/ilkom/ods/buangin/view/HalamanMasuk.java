package id.ac.unej.ilkom.ods.buangin.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Welcome;
import id.ac.unej.ilkom.ods.buangin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.view.BankSampah.HomeBankSampah;
import id.ac.unej.ilkom.ods.buangin.view.Mitra.HomeMitra;
import id.ac.unej.ilkom.ods.buangin.view.Perusahaan.HomePerusahaan;
import id.ac.unej.ilkom.ods.buangin.view.Volunteer.HomeVolunteer;


//importan gambar bergerak
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

public class HalamanMasuk extends AppCompatActivity {

    private static String TAG = "HalamanMasuk";

    private TextInputEditText email, password;
    private Button masuk;
    private TextView daftar;
    private Button tutorial;
    private Button paham;
    private String strEmail, strPassword;
    private ImageView tong1;
    private ImageView tong2;
    private TextView tong3;
    private TextView tong4;
    private ImageView tong;
    private ImageView msgemail;
    private ImageView msgdaftar;
    private ImageView msgpass;
    private ImageView msgreminder;
    private ImageView msgtombol;
    private ImageView tgemail;
    private ImageView tgdaftar;
    private ImageView tgpass;
    private Button btninfo;
    private Button btnclose;




    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    //deklarasi gambar bergerak
    private ViewGroup mainLayout;
    private ImageView image;

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        auth = FirebaseAuth.getInstance();

        dbRef = FirebaseDatabase.getInstance().getReference();

        //gambar bergerak



        mainLayout = (RelativeLayout) findViewById(R.id.main);
        tong = (ImageView) findViewById(R.id.tong);

        tong.setOnTouchListener(onTouchListener());

        //end

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        email = (TextInputEditText) findViewById(R.id.input_masukEmail);
        password = (TextInputEditText) findViewById(R.id.input_masukPassword);
//        tutorial = (Button) findViewById(R.id.btn_tutorial);
//        paham = (Button) findViewById(R.id.btn_ok);
        masuk = (Button) findViewById(R.id.btn_masuk);
        msgemail=   findViewById(R.id.msgemail);
        msgpass = findViewById(R.id.msgpassword);
        msgdaftar = findViewById(R.id.msgdaftar);
        msgreminder = findViewById(R.id.msgreminder);
        msgtombol = findViewById(R.id.msgtombol);
        tgemail = findViewById(R.id.tgemail);
        tgpass = findViewById(R.id.tgpassword);
        tgdaftar = findViewById(R.id.tgdaftar);
        btninfo = findViewById(R.id.btn_info);
        btnclose = findViewById(R.id.btn_close);



        daftar = (TextView) findViewById(R.id.btn_daftar);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = email.getText().toString();
                strPassword = password.getText().toString();
                boolean bolehMasuk = true;

                String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (strEmail.isEmpty()) {
                    email.requestFocus();
                    bolehMasuk = false;
                    email.setError("Isi terlebih dahulu!");
                } else {
                    bolehMasuk = true;
                }
                if (strPassword.isEmpty()) {
                    password.requestFocus();
                    bolehMasuk = false;
                    password.setError("Isi terlebih dahulu!");
                } else {
                    bolehMasuk = true;
                }

                if (bolehMasuk) {
                    if (strEmail.matches(emailValid)) {
                        auth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(HalamanMasuk.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = auth.getCurrentUser();
                                    dbRef.child("pengguna").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                Pengguna pen = child.getValue(Pengguna.class);
                                                cekLevel(pen.getLevel());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(HalamanMasuk.this, "Username Atau Email Salah", Toast.LENGTH_LONG).show();
                                            Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                                        }
                                    });

                                } else {
                                    if (cekJaringan()) {

                                    }
                                    Toast.makeText(HalamanMasuk.this, "Gagal Masuk", Toast.LENGTH_LONG).show();
                                    Log.w(TAG, "Gagal Masuk: " + task.getException());
                                }
                            }
                        });
                    }
                }
            }
        });



        msgemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.VISIBLE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                tgemail.setVisibility(View.GONE);
                tgpass.setVisibility(View.VISIBLE);
                tgdaftar.setVisibility(View.GONE);

            }
        });

        msgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.VISIBLE);
                msgreminder.setVisibility(View.GONE);
                tgemail.setVisibility(View.GONE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.VISIBLE);

            }
        });

        msgdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.VISIBLE);
                tgemail.setVisibility(View.GONE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.GONE);

            }
        });

        msgreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                msgtombol.setVisibility(View.VISIBLE);
                tgemail.setVisibility(View.GONE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.GONE);

            }
        });

        msgtombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                msgtombol.setVisibility(View.GONE);
                tgemail.setVisibility(View.GONE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.GONE);

            }
        });

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
//                msgemail.setVisibility(View.GONE);
//                msgpass.setVisibility(View.GONE);
//                msgdaftar.setVisibility(View.GONE);
//                msgreminder.setVisibility(View.GONE);
//                msgreminder.setVisibility(View.GONE);
//                tgemail.setVisibility(View.GONE);
//                tgpass.setVisibility(View.GONE);
//                tgdaftar.setVisibility(View.GONE);
                tong.setVisibility(View.VISIBLE);
                btninfo.setVisibility(View.GONE);
                btnclose.setVisibility(View.VISIBLE);
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
                msgemail.setVisibility(View.GONE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.GONE);
                tong.setVisibility(View.GONE);
                btninfo.setVisibility(View.VISIBLE);
                btnclose.setVisibility(View.GONE);
            }
        });





        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HalamanDaftar.class));
            }
        });
    }

    private void cekLevel(String level) {
        switch (level) {
            case Pengguna.VOLUNTEER:
                startActivity(new Intent(HalamanMasuk.this, HomeVolunteer.class));
                break;
            case Pengguna.BANK_SAMPAH:
                startActivity(new Intent(HalamanMasuk.this, HomeBankSampah.class));
                break;
            case Pengguna.MITRA:
                startActivity(new Intent(HalamanMasuk.this, HomeMitra.class));
                break;
            case Pengguna.PERUSAHAAN:
                startActivity(new Intent(HalamanMasuk.this, HomePerusahaan.class));
                break;
            default:
                Toast.makeText(HalamanMasuk.this, "Tidak Diketahui", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Level Tidak Diketahui");
        }
        View context = findViewById(R.id.root_layout_masuk);
        Snackbar.make(context, "Masuk Sebagai " + level, Snackbar.LENGTH_LONG).show();
        Log.d(TAG, "Berhasil Masuk");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Tutup aplikasi BUANG.IN?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                System.exit(1);
                //                HalamanMasuk.this.finish();
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);
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

    private boolean cekJaringan() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    //ontouch gambar bergerak

    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                msgemail.setVisibility(View.VISIBLE);
                msgpass.setVisibility(View.GONE);
                msgdaftar.setVisibility(View.GONE);
                msgreminder.setVisibility(View.GONE);
                tgemail.setVisibility(View.VISIBLE);
                tgpass.setVisibility(View.GONE);
                tgdaftar.setVisibility(View.GONE);


                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(HalamanMasuk.this,
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
}

