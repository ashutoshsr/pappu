package com.example.ashsrivast.pappu.scoresheet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.services.SharedBackend;

import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * Created by ashsrivast on 01/05/16.
 */
public class ScoreSheetColumItem extends LinearLayout {
    private SharedBackend shared = SharedBackend.getShared();
    private final TextView textView;
    private final TextView textView1;
    public ScoreSheetColumItem (Context context) {
        super(context);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_score_sheet_column, this, true);
        textView = (TextView) findViewById(R.id.score_sheet_column_player_name);
        textView1 = (TextView) findViewById(R.id.score_sheet_column_score);
    }


    public void setPlayers(Player player) {
            textView.setText(player.getName());
        int sum = 0;
        for (Integer score : player.getGameScore()) {
            sum += score;
        }
        textView1.setText(player.getGameScore().toString() + " -> " + sum);
    }

}
