package com.example.ashsrivast.pappu.listItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;

/**
 * Created by ashsrivast on 13/04/16.
 */
public class PlayerRowItem extends LinearLayout {

    private final TextView playerName;
    private final TextView playerScore;

    public PlayerRowItem(Context context) {
        super(context);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.layout_player_row_item, this, true);
        playerName = (TextView) root.findViewById(R.id.players_name);
        playerScore = (TextView) root.findViewById(R.id.players_score);
    }

    public void setPlayer(Player player) {
        playerName.setText(player.getName());
        playerScore.setText(String.valueOf(player.getScore()));
    }

}
