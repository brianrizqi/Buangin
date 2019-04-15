package id.ac.unej.ilkom.ods.buangin.admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Welcome extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar = (ProgressBar) findViewById(R.id.welcome_pb);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                }, 5000);
            }
        }, 500);
    }
}
