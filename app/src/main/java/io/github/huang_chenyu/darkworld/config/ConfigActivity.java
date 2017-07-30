package io.github.huang_chenyu.darkworld.config;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.huang_chenyu.darkworld.R;

public class ConfigActivity extends AppCompatActivity {


    ConfigContract.Presenter mConfigPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ConfigFragment configFragment = (ConfigFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (configFragment == null){
            configFragment = ConfigFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content_frame, configFragment);
            transaction.commit();
        }

        // Create the presenter
        mConfigPresenter = new ConfigPresenter(configFragment);
    }
}
