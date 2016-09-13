package com.example.ashsrivast.pappu.listItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.ashsrivast.pappu.entity.Player;

import java.util.List;

/**
 * Created by ashsrivast on 13/04/16.
 */
public class ListPlayersAdapter extends ArrayAdapter<Player> implements ListAdapter {

    public ListPlayersAdapter(Context context, int resource, List<Player> players) {
        super(context, resource, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Player player = getItem(position);
        if (player != null) {
            convertView = createFromPlayer(player, (PlayerRowItem) convertView);
        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private View createFromPlayer(final Player player, PlayerRowItem convertView) {
        final PlayerRowItem item = convertView == null ? new PlayerRowItem (getContext()) : convertView;
        item.setTag(player);
        item.setPlayer(player);
        return item;
    }
}
