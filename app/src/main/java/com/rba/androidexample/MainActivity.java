package com.rba.androidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.rba.androidexample.shake.ShakeListener;
import com.rba.androidexample.shake.ShakeManager;

public class MainActivity extends AppCompatActivity implements ShakeListener {

    private ShakeManager shakeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shakeManager = new ShakeManager(this);
        shakeManager.startListening(this);
        startShake();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startShake();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startShake();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopShake();
    }

    @Override
    public void onShake(float force) {
        Log.i("z- onShake", "" + force);
        startShake();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopShake();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopShake();
    }

    private void stopShake() {
        if (shakeManager.isListening()) {
            shakeManager.stopListening();
        }
    }

    private void startShake() {
        if (shakeManager.isSupported() && !shakeManager.isListening()) {
            shakeManager.startListening(this);
        }
    }

}
