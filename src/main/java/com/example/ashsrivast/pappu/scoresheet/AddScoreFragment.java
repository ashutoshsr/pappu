package com.example.ashsrivast.pappu.scoresheet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.listItem.ListPlayerScoreAdapter;
import com.example.ashsrivast.pappu.scoresheet.ScoreSheet;
import com.example.ashsrivast.pappu.scoresheet.adapters.ListScoreSheetPlayersAdapter;
import com.example.ashsrivast.pappu.services.SharedBackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class AddScoreFragment extends Fragment {

    private ListPlayerScoreAdapter adapter;
    private TextView finalScore;
    private SharedBackend shared = SharedBackend.getShared();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_score, container, false);
        ListView listContainer = (ListView) view.findViewById(R.id.player_score_list);
        finalScore = (TextView) view.findViewById(R.id.final_score);

        for (Player player : shared.getPlayers()) {
            player.setWinner(false);
            player.setScore(0);
            player.setPaplu(0);
            player.setIsPaplu(false);
        }
        adapter = new ListPlayerScoreAdapter(getActivity(), R.id.player_score_list, shared.getPlayers());
        listContainer.setAdapter(adapter);


        return view;
    }

    public int calculate(View view) {
        if (!validate()) {
            Snackbar.make(view, "Please validate the checkbox selections of the score",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            scoreNow();
        }
        return 0;
    }

    private void scoreNow() {
        Player winner = null;
        boolean hasPaplu = false;
        int numberOfPaplu = 0;
        int playersCount = adapter.getCount();
        int sum = 0;
        for (int i = 0; i < playersCount; i++) {
            Player player = adapter.getItem(i);
            if (player.isPaplu()) {
                hasPaplu = true;
                numberOfPaplu += player.getPaplu();
            }
            if (player.isWinner()) {
                player.setScore(0);
                winner = player;
            } else {
                player.setScore(-player.getScore());
                sum += -player.getScore();
            }

        }
        // No- PAPLU Each player's score is added and winner's score is evaluated
        if (hasPaplu) {
            sum = 0;
            for (int i = 0; i < playersCount; i++) {
                Player player = adapter.getItem(i);
                if (!player.isWinner()) {
                    player.setScore(player.isPaplu() ? (player.getPaplu()) * 2 + player.getScore() : player.getScore() - numberOfPaplu * 2);
                    sum += -player.getScore();
                }
            }
        }
        for (int i = 0; i < playersCount; i++) {
            Player player = adapter.getItem(i);
            if (player.isWinner()) {
                player.setScore(sum);
                break;
            }
        }
        finalScore.setText(String.valueOf(sum));
        winner.setScore(sum);
        for (Player player : shared.getPlayers()) {
            player.setGameScore(player.getScore());
        }
        adapter.notifyDataSetChanged();
    }

    private boolean validate() {
        boolean winner = false;
        int sumOfPaplu = 0;

        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            Player player = adapter.getItem(i);
            if (player.isPaplu()) {
                sumOfPaplu += player.getPaplu();
            }
            if (!winner) {
                winner = player.isWinner();
            }
        }

        return sumOfPaplu <= 2 && winner;
    }

    public void showGameSheet(View view) throws IOException {
        if (shared.isGameSet()) {

            for (Player player : shared.getPlayers()) {
                player.setGameScore(player.getScore());
            }

            File file = new File(view.getContext().getFilesDir(), "paplu_scoring");
            String filePath = file.getPath();
            FileOutputStream fileOutputStream = null;
            FileInputStream fileInputStream = null;
            try {
                fileOutputStream = getActivity().openFileOutput("paplu_scoring",
                        Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
                oos.writeObject(shared.getPlayers());
                fileInputStream = getActivity().openFileInput("paplu_scoring");
                ObjectInputStream ois = new ObjectInputStream(fileInputStream);
                List<Player> players =  (List<Player>) ois.readObject();
                for (Player player : players) {
                    String name = player.getName();
                }
            } catch (IOException | ClassNotFoundException exp) {
                StackTraceElement[] name = exp.getStackTrace();

            } finally {
                fileInputStream.close();
                fileOutputStream.close();
            }

        } else {
            Snackbar.make(view, R.string.less, Snackbar.LENGTH_SHORT).show();
        }
    }
}
