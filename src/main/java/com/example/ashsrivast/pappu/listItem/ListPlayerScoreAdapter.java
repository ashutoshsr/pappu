package com.example.ashsrivast.pappu.listItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.NumberPicker;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.listItem.ScoreRowItem;

import java.util.List;

/**
 * Created by ashsrivast on 13/04/16.
 */
public class ListPlayerScoreAdapter extends ArrayAdapter<Player> implements ListAdapter {

    ViewGroup parent;
    boolean isEnable;
    CheckBox myCheck;

    public ListPlayerScoreAdapter(Context context, int resource, List<Player> players) {
        super(context, resource, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.parent = parent;
        final Player player = getItem(position);
        if (player != null) {
            convertView = createFromPlayer(player, (ScoreRowItem) convertView);
        }
        bindPlayerScore(convertView, player);
        bindPlayerPapluSelection(convertView, player);
        bindPlayerWinnerSelection(convertView, player);
        bindPapluScoreChange(convertView, player);
        myCheck.setEnabled(player.isWinner() || !isEnable);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private void bindPlayerPapluSelection(final View convertView, final Player player) {
        CheckBox myCheck = (CheckBox) convertView.findViewById(R.id.paplu);
        myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NumberPicker papluScorePicker = (NumberPicker) convertView.findViewById(R.id.papluScore);
                papluScorePicker.setValue(1);
                player.setIsPaplu(isChecked);
            }
        });
    }

    private void bindPlayerWinnerSelection(final View convertView, final Player player) {
        myCheck = (CheckBox) convertView.findViewById(R.id.winner);
        myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                player.setWinner(isChecked);
                isEnable = isChecked;
                if (isChecked) {
//                    NumberPicker eachPlayerScore = (NumberPicker) convertView.findViewById(R.id.each_player_score);
//                    eachPlayerScore.setValue(0);
                    player.setScore(0);
                }
                notifyDataSetChanged();
            }
        });
    }

    private void bindPapluScoreChange(final View convertView, final Player player) {
        NumberPicker papluScore = (NumberPicker) convertView.findViewById(R.id.papluScore);
        papluScore.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                player.setPaplu(picker.getValue());
            }
        });
    }

    private void bindPlayerScore(View convertView, final Player player) {
        NumberPicker eachPlayerScore = (NumberPicker) convertView.findViewById(R.id.each_player_score);
        eachPlayerScore.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                player.setScore(picker.getValue());
            }
        });
    }

    private View createFromPlayer(final Player player, ScoreRowItem convertView) {
        final ScoreRowItem item = convertView == null ? new ScoreRowItem(getContext()) : convertView;
        item.setTag(player);
        item.setPlayer(player);
        return item;
    }

/*    public void setNonEditable(ScoreRowItem convertView) {
        final ScoreRowItem item = convertView == null ? new ScoreRowItem(getContext()) : convertView;
        item.setNonEditable();
    }*/
}
