package id.ac.unej.ilkom.ods.buangin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.ac.unej.ilkom.ods.buangin.adapter.SliderAdapter;
import id.ac.unej.ilkom.ods.buangin.view.HalamanMasuk;

public class HalamanAwal extends AppCompatActivity {

    private ViewPager pager_layout;
    private LinearLayout dot_layout;

    private TextView[] text_dot;
    private SliderAdapter adapter;

    private Button btnBack, btnNext;
    private int currentPage;
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float f, int p) {

        }

        @Override
        public void onPageSelected(int i) {
            indikator(i);

            currentPage = i;

            if (i == 0) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);
                btnBack.setVisibility(View.INVISIBLE);

                btnNext.setText("selanjutnya");
                btnBack.setText("");

                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pager_layout.setCurrentItem(currentPage + 1);
                    }
                });
            } else if (i == 1) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("selanjutnya");
                btnBack.setText("kembali");

//                btnBack.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pager_layout.setCurrentItem(currentPage - 1);
//                    }
//                });

//                btnNext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
//                    }
//                });

            } else if (i == 2) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("mulai");
                btnBack.setText("kembali");

//                btnNext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pager_layout.setCurrentItem(currentPage + 1);
//                    }
//                });
//
//                btnBack.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pager_layout.setCurrentItem(currentPage - 1);
//                    }
//                });
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_awal);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        pager_layout = (ViewPager) findViewById(R.id.halamanAwal_layout_pager);
        dot_layout = (LinearLayout) findViewById(R.id.halamanAwal_layout_dot);

        btnBack = (Button) findViewById(R.id.btn_back);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_layout.setCurrentItem(currentPage + 1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_layout.setCurrentItem(currentPage - 1);
            }
        });

        SliderAdapter adapter = new SliderAdapter(this);
        pager_layout.setAdapter(adapter);

        indikator(0);
        pager_layout.addOnPageChangeListener(onPageChangeListener);
    }

    private void indikator(int position) {
        text_dot = new TextView[3];
        dot_layout.removeAllViews();

        for (int i = 0; i < text_dot.length; i++) {
            text_dot[i] = new TextView(this);
            text_dot[i].setText(Html.fromHtml("&#8226;"));
            text_dot[i].setTextSize(35);
            text_dot[i].setTextColor(getResources().getColor(R.color.warnaTransparanPutih));

            dot_layout.addView(text_dot[i]);
        }

        if (text_dot.length > 0) {
            text_dot[position].setTextColor(getResources().getColor(R.color.warnaPutih));
        }
    }

    public void exit() {
        HalamanAwal.this.finish();
    }
}
