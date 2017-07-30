package io.github.huang_chenyu.darkworld.puzzle;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import io.github.huang_chenyu.darkworld.R;

public class PuzzleActivity extends AppCompatActivity {

    private PuzzlePresenter mPuzzlePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        PuzzleFragment puzzleFragment = (PuzzleFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (puzzleFragment == null){
            puzzleFragment = PuzzleFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, puzzleFragment);
            transaction.commit();
        }

//        String puzzleParam = getIntent().getStringExtra("puzzleParam");
        // Create the presenter
        mPuzzlePresenter = new PuzzlePresenter(puzzleFragment);
//        mPuzzlePresenter.setUpPuzzleBoard(puzzleParam);
    }


}
