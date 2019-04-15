package id.ac.unej.ilkom.ods.buangin.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;
import id.ac.unej.ilkom.ods.buangin.admin.view.Home;

public class Login extends AppCompatActivity {

    private TextInputEditText email, password;
    private Button masuk;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private boolean loggedIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        initFirebase();

        loggedIn = sudahMasuk();
        if (loggedIn) {
            halamanAdmin();
        }

        email = (TextInputEditText) findViewById(R.id.login_input_email);
        password = (TextInputEditText) findViewById(R.id.login_input_password);
        masuk = (Button) findViewById(R.id.login_btn_masuk);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEmail = email.getText().toString().trim();
                String stringPassword = password.getText().toString().trim();
                final View overlay = findViewById(R.id.login_layout_overlay);

                Button btnMasuk = masuk;
                btnMasuk.setEnabled(false);
                overlay.setVisibility(View.VISIBLE);
                Boolean bolehMasuk = true;
                String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (stringEmail.isEmpty()) {
                    email.requestFocus();
                    email.setError("Silahkan isi terlebih dahulu");
                    overlay.setVisibility(View.GONE);
                    bolehMasuk = false;
                    masuk.setEnabled(true);
                } else {
                    bolehMasuk = true;
                }
                if (stringPassword.isEmpty()) {
                    password.requestFocus();
                    password.setError("Silahkan isi terlebih dahulu");
                    overlay.setVisibility(View.GONE);
                    bolehMasuk = false;
                    masuk.setEnabled(true);
                } else {
                    bolehMasuk = true;
                }

                if (bolehMasuk) {
                    if (stringEmail.matches(emailValid)) {
                        firebaseAuth.signInWithEmailAndPassword(stringEmail, stringPassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    overlay.setVisibility(View.GONE);
                                    halamanAdmin();
                                } else {
                                    masuk.setEnabled(true);
                                    overlay.setVisibility(View.GONE);
                                    pesan("Gagal Masuk", "Tidak ada koneksi internet", "Masuk kembali");
                                }
                            }
                        });
                    } else {
                        masuk.setEnabled(true);
                        email.requestFocus();
                        email.setError("Email tidak valid");
                        overlay.setVisibility(View.GONE);
                        bolehMasuk = false;
                    }
                } else {
                    bolehMasuk = false;
                    masuk.setEnabled(true);
                }
            }
        });
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void pesan(String judul, String pesan, String a) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(judul)
                .setMessage(pesan)
                .setPositiveButton(a, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void halamanAdmin() {
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.child("pengguna").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pengguna model = dataSnapshot1.getValue(Pengguna.class);
                    String level = model.getLevel();
                    switch (level) {
                        case Pengguna.ADMIN:
                            System.out.println("masuk : " + firebaseUser.getEmail() + " uid : " + firebaseUser.getUid());
                            Intent i = new Intent(getApplicationContext(), Home.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        default:
                            masuk.setEnabled(true);
                            pesan("Gagal Masuk", "Akun dengan alamat E-Mail " + email.getText().toString().trim() + " tidak diketahui", "Tutup");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean sudahMasuk() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }
}
