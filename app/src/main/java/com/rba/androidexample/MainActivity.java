package com.rba.androidexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.rba.androidexample.view.fragment.OneFragment;
import com.rba.androidexample.view.fragment.SecondFragment;
import com.rba.androidexample.view.fragment.ThirdFragment;
import com.rba.mylibrary.Stepper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean exit;
    private Stepper stData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        stData = findViewById(R.id.st_data);
        setSupportActionBar(toolbar);

        loadData();
    }

    private void loadData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());

        stData.setData(fragmentList);
    }

    @Override
    public void onBackPressed() {

        if (exit) {
            super.onBackPressed();
            return;
        }

        this.exit = true;
        Toast.makeText(this, getString(R.string.message_back), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }


}
