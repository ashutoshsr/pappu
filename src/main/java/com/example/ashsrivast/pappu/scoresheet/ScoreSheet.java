package com.example.ashsrivast.pappu.scoresheet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.scoresheet.adapters.ListScoreSheetPlayersAdapter;
import com.example.ashsrivast.pappu.services.SharedBackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ScoreSheet extends AppCompatActivity implements ScoreSheetFragment.OnScoreSheetFragmentInteractionListener {

    SharedBackend shared = SharedBackend.getShared();
    AddScoreFragment addScoreFragment;
    ScoreSheetFragment scoreSheetFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_sheet);

        showGameSheet(null);
    }

    public void showScoreCalc(View view) {
        addScoreFragment = new AddScoreFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.scoringSheet, addScoreFragment).commit();
    }

    public void showGameSheet(View view) {
        if (view != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            scoreSheetFragment.showGameSheet();
            transaction.replace(R.id.scoringSheet, scoreSheetFragment).commit();
        } else {
            scoreSheetFragment = new ScoreSheetFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.scoringSheet, scoreSheetFragment).commit();
        }
    }

    public int calculate(View view) {
        addScoreFragment.calculate(view);
        return 0;
    }

    public void saveScore(View view) throws IOException {
        String dateFormat = "paplue_scoresheet";
        File file = new File(view.getContext().getExternalFilesDir(null), dateFormat);
        String filePath = file.getPath();
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileOutputStream = openFileOutput(dateFormat,
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(shared.getPlayers());
            fileInputStream = openFileInput(dateFormat);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            List<Player> players = (List<Player>) ois.readObject();
            for (Player player : players) {
                String name = player.getName();
            }
        } catch (IOException | ClassNotFoundException exp) {
            StackTraceElement[] name = exp.getStackTrace();

        } finally {
            fileInputStream.close();
            fileOutputStream.close();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
