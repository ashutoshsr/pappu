package com.example.ashsrivast.pappu.scoresheet.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.listItem.ScoreRowItem;
import com.example.ashsrivast.pappu.services.SharedBackend;

import java.util.List;

/**
 * Created by ashsrivast on 13/04/16.
 */
public class ListScoreSheetPlayersAdapter extends ArrayAdapter<Player> implements ListAdapter {
    SharedBackend shared = SharedBackend.getShared();

    public ListScoreSheetPlayersAdapter(Context c, int resource, List<Player> players) {
        super(c, resource, players);
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Player player = getItem(position);
        if (player != null) {
            convertView = createFromPlayer(player, (ScoreSheetColumItem) convertView);
        }
        return convertView;
    }

    private View createFromPlayer(final Player player, ScoreSheetColumItem convertView) {
        final ScoreSheetColumItem item = convertView == null ? new ScoreSheetColumItem(getContext()) : convertView;
        item.setPlayers(player);
        return item;
    }
}
