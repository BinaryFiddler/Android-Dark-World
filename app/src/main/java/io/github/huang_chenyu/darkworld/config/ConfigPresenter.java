package io.github.huang_chenyu.darkworld.config;

/**
 * Created by chenyu on 7/30/17.
 */

public class ConfigPresenter implements ConfigContract.Presenter{

    ConfigContract.View mView;


    public ConfigPresenter(ConfigFragment configFragment) {
        mView = configFragment;
    }

    @Override
    public void start() {

    }

    @Override
    public void setUpPuzzleParams() {

    }
}
