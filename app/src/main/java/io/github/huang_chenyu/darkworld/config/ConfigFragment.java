package io.github.huang_chenyu.darkworld.config;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.huang_chenyu.darkworld.R;
import io.github.huang_chenyu.darkworld.puzzle.PuzzleActivity;

public class ConfigFragment extends Fragment implements ConfigContract.View{

    ConfigContract.Presenter mPresenter;

    SeekBar dragonBar;

    SeekBar swordBar;

    SeekBar sizeBar;

    TextView dragonTextView;

    TextView swordTextView;

    TextView sizeTextView;

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance() {
        return new ConfigFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_config, container, false);

        Button startButton = (Button) rootView.findViewById(R.id.start_button);

        dragonBar = (SeekBar) rootView.findViewById(R.id.dragon_setter);

        swordBar = (SeekBar) rootView.findViewById(R.id.sword_setter);

        sizeBar = (SeekBar) rootView.findViewById(R.id.size_setter);

        dragonTextView = (TextView) rootView.findViewById(R.id.dragon_textview);

        swordTextView = (TextView) rootView.findViewById(R.id.sword_textview);

        sizeTextView = (TextView) rootView.findViewById(R.id.size_textview);


        dragonBar.setOnSeekBarChangeListener(seekBarChangeListener);

        swordBar.setOnSeekBarChangeListener(seekBarChangeListener);

        sizeBar.setOnSeekBarChangeListener(seekBarChangeListener);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numOfDragons = dragonBar.getProgress() + 1;

                int numOfSwords = swordBar.getProgress();

                int sizeOfPuzzle = sizeBar.getProgress() + 3;

                JSONObject puzzleJson = new JSONObject();

                try {
                    puzzleJson.put("numOfDragons", numOfDragons);
                    puzzleJson.put("numOfSwords", numOfSwords);
                    puzzleJson.put("sizeOfPuzzle", sizeOfPuzzle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getContext(), PuzzleActivity.class);
                intent.putExtra("puzzleParam", puzzleJson.toString());
                startActivity(intent);
            }
        });

        return rootView;
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            int progressValue = 0;
            if (seekBar == dragonBar){
                progressValue = progress + 1;
                dragonTextView.setText("Num of Dragons: " + progressValue);
            }else if (seekBar == swordBar){
                progressValue = progress;
                swordTextView.setText("Num of Swords: " + progressValue);
            }else if (seekBar == sizeBar){
                progressValue = progress + 3;
                sizeTextView.setText("Size of Puzzle: " + progressValue + "x" + progressValue);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(ConfigContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
