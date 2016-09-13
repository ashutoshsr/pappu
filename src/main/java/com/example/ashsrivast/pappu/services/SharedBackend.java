package com.example.ashsrivast.pappu.services;

import com.example.ashsrivast.pappu.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashsrivast on 12/04/16.
 */
public class SharedBackend {
    private static List<Player> players;
    private static SharedBackend shared = null;
    private String gameName = "null";

    private SharedBackend() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public static SharedBackend getShared() {
        if (shared == null) {
            shared = new SharedBackend();
        }
        return shared;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameName (String name) {
        this.gameName = name;
    }

    public String getGameName() {
        return gameName;
    }

    public boolean isGameSet() {
        return players.size() > 1;
    }
}
