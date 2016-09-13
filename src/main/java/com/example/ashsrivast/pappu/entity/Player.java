package com.example.ashsrivast.pappu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashsrivast on 12/04/16.
 */
public class Player implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    boolean isPaplu;
    int paplu = 1;

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isPaplu() {
        return isPaplu;
    }

    public void setIsPaplu(boolean isPaplu) {
        this.isPaplu = isPaplu;
    }

    public void setPaplu(int paplu) {
        this.paplu = paplu;
    }

    public int getPaplu() {
        return paplu;
    }

    boolean winner;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    int score;

    public List<Integer> getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore.add(gameScore);
    }

    public void setPaplu(boolean paplu) {
        isPaplu = paplu;
    }

    List<Integer> gameScore;


    public Player(String name) {
        this.name = name;
        this.score = 0;
        gameScore = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + " has score > " + score;
    }
}
