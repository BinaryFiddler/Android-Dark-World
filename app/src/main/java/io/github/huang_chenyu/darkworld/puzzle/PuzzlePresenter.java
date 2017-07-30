package io.github.huang_chenyu.darkworld.puzzle;

import android.os.Handler;

import io.github.huang_chenyu.darkworld.Board;
import io.github.huang_chenyu.darkworld.DarkWorldConstants;
import io.github.huang_chenyu.darkworld.MovementOptions;

/**
 * Created by chenyu on 7/29/17.
 */

public class PuzzlePresenter implements PuzzleContract.Presenter {

    PuzzleContract.View mView;

    Board board;

    Runnable gameOverRunnable;

    Handler handler;

    PuzzlePresenter(PuzzleContract.View view){
        mView = view;
        mView.setPresenter(this);
        gameOverRunnable = new Runnable() {
            public void run() {
                mView.gameOver();
            }
        };
        handler = new Handler();
    }

    @Override
    public void start() {
        board = new Board(10, 10);
        mView.drawBoard(10, 10);
    }

    @Override
    public void getMovementResult(int movement) {
        int result = board.move(movement);
        if (result == DarkWorldConstants.INVALID_CELL){
            return;
        }

        if (result == DarkWorldConstants.AVATAR_ALIVE_SWORD){
            mView.showSword();
        }
        if (result == DarkWorldConstants.AVATAR_SLAY_DRAGON){
            mView.showSlayDragon();
        }
        if (result == DarkWorldConstants.AVATAR_DEAD){
            mView.showDeath();
            mView.removeListeners();
            handler.postDelayed(gameOverRunnable, 1000);
        }
        if (result == DarkWorldConstants.AVATAR_TRIUMPH){
            mView.showTriumph();
            mView.removeListeners();
            handler.postDelayed(gameOverRunnable, 1000);
        }

        switch (movement){
            case MovementOptions.LEFT:
                mView.moveLeft();
                break;
            case MovementOptions.RIGHT:
                mView.moveRight();
                break;
            case MovementOptions.DOWN:
                mView.moveDown();
                break;
            case MovementOptions.UP:
                mView.moveUp();
                break;
        }
    }
}
