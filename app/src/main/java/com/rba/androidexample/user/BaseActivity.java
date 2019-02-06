package com.rba.androidexample.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rba.androidexample.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showBaseLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }

        if (!isFinishing() && !progressDialog.isShowing()) {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.wait_please));
            progressDialog.show();
        }
    }

    public void hideBaseLoading() {
        if (!isFinishing() && progressDialog != null) {
            progressDialog.dismiss();
        }
    }


}
