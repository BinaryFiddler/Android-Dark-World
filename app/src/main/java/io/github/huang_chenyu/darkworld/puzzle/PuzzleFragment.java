package io.github.huang_chenyu.darkworld.puzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import io.github.huang_chenyu.darkworld.MovementOptions;
import io.github.huang_chenyu.darkworld.R;
import io.github.huang_chenyu.darkworld.config.ConfigActivity;

public class PuzzleFragment extends Fragment implements PuzzleContract.View {

    private int posX;

    private int posY;

    private int rowNum;

    private int colNum;

    private int avatarResId;

    private PuzzleContract.Presenter mPresenter;

    float x1, x2;
    float y1, y2;

    public static PuzzleFragment newInstance() {
        return new PuzzleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_puzzle, container, false);

        avatarResId = R.drawable.man;

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent touchevent) {
                switch (touchevent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = touchevent.getX();
                        y1 = touchevent.getY();
                        Toast.makeText(getContext(), "Hello",Toast.LENGTH_SHORT);
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = touchevent.getX();
                        y2 = touchevent.getY();
                        //if left to right sweep event on screen
                        if (x1 < x2 - 300 && Math.abs(x2 - x1) - 300 > Math.abs(y1 - y2)) {
                            mPresenter.getMovementResult(MovementOptions.RIGHT);
                            break;
                        }
                        // if right to left sweep event on screen
                        if (x1 > x2 + 300 && Math.abs(x2 - x1) - 300 > Math.abs(y1 - y2)){
                            mPresenter.getMovementResult(MovementOptions.LEFT);
                            break;
                        }
                        // if UP to Down sweep event on screen
                        if (y1 < y2 - 300 && Math.abs(x2 - x1) + 300 < Math.abs(y1 - y2)){
                            mPresenter.getMovementResult(MovementOptions.DOWN);
                            break;
                        }
                        //if Down to UP sweep event on screen
                        if (y1 > y2 + 300 && Math.abs(x2 - x1) + 300 < Math.abs(y1 - y2)) {
                            mPresenter.getMovementResult(MovementOptions.UP);
                            break;
                        }
                        break;
                }
                return true;
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setUpPuzzleBoard(getActivity().getIntent().getStringExtra("puzzleParam"));
        mPresenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(PuzzleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void drawBoard(int row, int col) {
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams cellParams = new TableRow.LayoutParams();

        TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.board_table);

        rowNum = row;
        colNum = col;

        posX = 0;
        posY = 0;

        int id = 0;
        for (int i = 0; i < row; i++){
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(tableParams);// TableLayout is the parent view
            for (int j = 0; j < col; j++){
                ImageView cellView = new ImageView(getContext());
                cellView.setId(id++);
                cellView.setBackgroundColor(Color.GRAY);

                cellParams.width = 128;
                cellParams.height = 128;
                cellParams.setMargins(10, 10, 10, 10);

                cellView.setScaleType(ImageView.ScaleType.FIT_XY);
                cellView.setLayoutParams(cellParams);// TableRow is the parent view
                tableRow.addView(cellView);
            }
            tableLayout.addView(tableRow);
        }
    }

    @Override
    public void moveLeft() {
        removeOldAvatar();
        posY--;
        upDateAvatarPosition();
    }

    @Override
    public void moveRight() {
        removeOldAvatar();
        posY++;
        upDateAvatarPosition();
    }

    @Override
    public void moveUp() {
        removeOldAvatar();
        posX--;
        upDateAvatarPosition();
    }

    @Override
    public void moveDown() {
        removeOldAvatar();
        posX++;
        upDateAvatarPosition();
    }

    @Override
    public void showSword() {
        avatarResId = R.drawable.sword_man;
    }

    @Override
    public void showDeath() {
        avatarResId = R.drawable.dead_man;
    }

    @Override
    public void showTriumph() {
        avatarResId = R.drawable.win_man;
    }

    @Override
    public void showSlayDragon() {
        avatarResId = R.drawable.dragon_slay;
    }

    @Override
    public void gameOver() {
        Intent intent = new Intent(getContext(), ConfigActivity.class);
        startActivity(intent);
    }

    @Override
    public void removeListeners(){
        getView().setOnTouchListener(null);
//        leftButton.setOnClickListener(null);
//        rightButton.setOnClickListener(null);
//        upButton.setOnClickListener(null);
//        downButton.setOnClickListener(null);
    }

    private void removeOldAvatar(){
        int loc = posX * colNum + posY;
        ImageView view = (ImageView) getActivity().findViewById(loc);
        view.setImageResource(0);
    }

    private void upDateAvatarPosition(){
        int loc = posX * colNum + posY;
        ImageView view = (ImageView) getActivity().findViewById(loc);
        view.setBackgroundColor(Color.GREEN);
        view.setImageResource(avatarResId);
        avatarResId = R.drawable.man;
    }
}
