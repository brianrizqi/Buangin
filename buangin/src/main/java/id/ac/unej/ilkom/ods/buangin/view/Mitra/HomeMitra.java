package id.ac.unej.ilkom.ods.buangin.view.Mitra;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.view.HalamanMasuk;
import id.ac.unej.ilkom.ods.buangin.view.Pengaturan;
import id.ac.unej.ilkom.ods.buangin.view.Riwayatku;
import id.ac.unej.ilkom.ods.buangin.view.UmpanBalik;

public class HomeMitra extends AppCompatActivity /*, HomeFragment.OnMenuClickListener */ {

    public static String ACTIVE = "fragment_mitra_aktif";

    HomeFragment m_home;
    TabDaftarVoucherFragment m_daftar_voucher;
    TabBuatVoucherFragment m_voucher;
    RiwayatPenukaranFragment m_m_riwayat;
    DaftarVoucherFragment m_m_daftar_voucher;

    Pengaturan f_nav_pengaturan;
    Riwayatku f_nav_riwayat;
    UmpanBalik f_nav_timbalBalik;

    FragmentManager fm;
    FragmentTransaction ft;
    BottomNavigationView nav_mitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mitra);
        setSupportActionBar(toolbar);

        m_home = new HomeFragment();
        m_m_riwayat = new RiwayatPenukaranFragment();
        m_m_daftar_voucher = new DaftarVoucherFragment();

        m_daftar_voucher = new TabDaftarVoucherFragment();
        m_voucher = new TabBuatVoucherFragment();

        f_nav_pengaturan = new Pengaturan();
        f_nav_riwayat = new Riwayatku();
        f_nav_timbalBalik = new UmpanBalik();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.add(R.id.container_mitra, m_home, HomeFragment.class.getSimpleName()).commit();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mitra);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_side_mitra);
//        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction ft_sampah = getSupportFragmentManager().beginTransaction();
        ft_sampah.replace(R.id.container_mitra, new HomeFragment());
        ft_sampah.commit();

        nav_mitra = (BottomNavigationView) findViewById(R.id.nav_mitra);
        nav_mitra.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_mit_home:
                        fm.beginTransaction().replace(R.id.container_mitra, m_home, ACTIVE).commit();
                    case R.id.nav_mit_daftar_voucher:
                        fm.beginTransaction().replace(R.id.container_mitra, m_m_daftar_voucher, ACTIVE).commit();
                        break;
                    case R.id.nav_mit_riwayat_penukaran:
                        fm.beginTransaction().replace(R.id.container_mitra, m_m_riwayat, ACTIVE).commit();
                        break;
                }
                return false;
            }
        });

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mitra);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Keluar?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                    HomeMitra.this.finish();
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_timbalBalik) {
//            fm.beginTransaction().replace(R.id.container_mitra, f_nav_timbalBalik).commit();
//        } else if (id == R.id.nav_riwayat) {
//            fm.beginTransaction().replace(R.id.container_mitra, f_nav_riwayat).commit();
//        } else if (id == R.id.nav_pengaturan) {
//            fm.beginTransaction().replace(R.id.container_mitra, f_nav_pengaturan).commit();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mitra);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    public void onMenuSelected(int position) {
//        switch (position) {
//            case 0:
////                fm.beginTransaction().replace(R.id.container_mitra, m_m_voucher, ACTIVE).commit();
////                nav_mitra.setSelectedItemId(R.id.nav_daftar_voucher);
////                break;
//            case 1:
////                fm.beginTransaction().replace(R.id.container_mitra, m_m_riwayat, ACTIVE).commit();
////                nav_mitra.setSelectedItemId(R.id.nav_riwayat_penukaran);
////                break;
//            default:
//                //TODO Buat Default Action
////                break;
//        }
//        nav_mitra.setVisibility(View.VISIBLE);
//    }
}
