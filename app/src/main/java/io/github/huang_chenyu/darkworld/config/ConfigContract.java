package io.github.huang_chenyu.darkworld.config;

import io.github.huang_chenyu.darkworld.BasePresenter;
import io.github.huang_chenyu.darkworld.BaseView;
import io.github.huang_chenyu.darkworld.puzzle.PuzzleContract;

/**
 * Created by chenyu on 7/30/17.
 */

public interface ConfigContract {
    public interface View extends BaseView<Presenter>{

    }

    public interface Presenter extends BasePresenter{
        void setUpPuzzleParams();
    }
}
