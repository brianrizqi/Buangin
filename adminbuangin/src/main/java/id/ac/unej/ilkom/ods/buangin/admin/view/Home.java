package id.ac.unej.ilkom.ods.buangin.admin.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import id.ac.unej.ilkom.ods.buangin.admin.Login;
import id.ac.unej.ilkom.ods.buangin.admin.R;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    FragmentTransaction ft;
    FragmentManager fm;
    FragmentDaftarHarga daftarHarga;
    FragmentDaftarPengguna daftarPengguna;
    FragmentPoin fragmentPoin;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public Home() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        daftarHarga = new FragmentDaftarHarga();
        daftarPengguna = new FragmentDaftarPengguna();
        fragmentPoin = new FragmentPoin();

        ft.add(R.id.main_frameLayout, daftarHarga, FragmentDaftarHarga.class.getSimpleName()).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_nav_bawah);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.main_nav_harga:
                        fm.beginTransaction().replace(R.id.main_frameLayout, daftarHarga).commit();
                        break;
                    case R.id.main_nav_pengguna:
                        fm.beginTransaction().replace(R.id.main_frameLayout, daftarPengguna).commit();
                        break;
                    case R.id.main_nav_poin:
                        fm.beginTransaction().replace(R.id.main_frameLayout, fragmentPoin).commit();
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Admin")
                .setMessage("Yakin mau keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        Home.this.finish();
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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        tampilanAwal(user);
    }

    private void tampilanAwal(FirebaseUser user) {
        if (user != null) {
            System.out.println("pengguna masuk : " + user.getEmail() + " uid : " + user.getUid());
        }
    }
}
