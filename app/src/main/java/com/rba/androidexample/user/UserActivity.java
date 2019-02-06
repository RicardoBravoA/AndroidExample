package com.rba.androidexample.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rba.androidexample.R;
import com.rba.data.datasource.UserDataStoreFactory;
import com.rba.data.repository.UserDataRepository;
import com.rba.domain.interactor.UserInteractor;
import com.rba.domain.model.UserModel;
import com.squareup.picasso.Picasso;

import static android.view.View.VISIBLE;

public class UserActivity extends AppCompatActivity implements UserView {

    private AppCompatEditText etUSer;
    private AppCompatImageView ivAvatar;
    private TextView tvUser;
    private TextView tvName;
    private UserPresenter userPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUSer = findViewById(R.id.et_user);
        AppCompatButton btnSearch = findViewById(R.id.btn_search);
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUser = findViewById(R.id.tv_user);
        tvName = findViewById(R.id.tv_name);

        UserDataRepository userDataRepository = new UserDataRepository(new UserDataStoreFactory(this));
        userPresenter = new UserPresenter(new UserInteractor(userDataRepository));
        userPresenter.attach(this);

        btnSearch.setOnClickListener(v -> {
            String user = etUSer.getText().toString();

            userPresenter.getUser(user);
        });
    }

    @Override
    public void onUserSuccess(UserModel userModel) {
        Picasso.get()
                .load(userModel.getAvatarUrl())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(ivAvatar);
        ivAvatar.setVisibility(VISIBLE);
        tvUser.setText(userModel.getLogin());
        tvUser.setVisibility(VISIBLE);
        tvName.setText(userModel.getName());
        tvName.setVisibility(VISIBLE);
    }

    @Override
    public void onUserError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.detach();
    }
}
