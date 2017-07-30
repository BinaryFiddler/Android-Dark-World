package io.github.huang_chenyu.darkworld.puzzle;

import io.github.huang_chenyu.darkworld.BasePresenter;
import io.github.huang_chenyu.darkworld.BaseView;

/**
 * Created by chenyu on 7/29/17.
 */

public interface PuzzleContract {
    public interface View extends BaseView<Presenter>{
        void drawBoard(int i, int i1);

        void moveLeft();

        void moveRight();

        void moveUp();

        void moveDown();

        void showSword();

        void showDeath();

        void showTriumph();

        void showSlayDragon();

        void gameOver();

        void removeListeners();
    }

    public interface Presenter extends BasePresenter{

        void getMovementResult(int movement);




    }
}
