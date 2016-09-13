package com.example.ashsrivast.pappu.listItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;

/**
 * Created by ashsrivast on 13/04/16.
 */
public class ScoreRowItem extends LinearLayout {

    public static final int MAX_PAPLU_VALUE = 2;
    public static final int MAX_SCORE_VALUE = 10;
    public static final int MIN_VALUE = 0;
    private final TextView playerName;
    private final NumberPicker eachPlayerScore;
    private final CheckBox paplu;
    private final NumberPicker papluScore;
    private final CheckBox winner;

    public ScoreRowItem(Context context) {
        super(context);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_score_row, this, true);
        playerName = (TextView) root.findViewById(R.id.player_name);
        eachPlayerScore = (NumberPicker) root.findViewById(R.id.each_player_score);
        papluScore = (NumberPicker) root.findViewById(R.id.papluScore);
        paplu = (CheckBox) root.findViewById(R.id.paplu);
        winner = (CheckBox) root.findViewById(R.id.winner);
    }

    public void setPlayer(Player player) {
        playerName.setText(player.getName());
        eachPlayerScore.setMinValue(MIN_VALUE);
        eachPlayerScore.setMaxValue(MAX_SCORE_VALUE);
        papluScore.setMinValue(MIN_VALUE);
        papluScore.setMaxValue(MAX_PAPLU_VALUE);
    }

    public int getScore() {
        return Integer.valueOf(eachPlayerScore.getValue());
    }

    public void setScore(int calculatedScore) {
        eachPlayerScore.setValue(calculatedScore);
    }

    public void setNonEditable() {
        eachPlayerScore.setEnabled(false);
    }
}
