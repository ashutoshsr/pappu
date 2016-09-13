package com.example.ashsrivast.pappu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.listItem.ListPlayersAdapter;
import com.example.ashsrivast.pappu.scoresheet.ScoreSheet;
import com.example.ashsrivast.pappu.services.SharedBackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ScoringFragment extends Fragment implements PlayersListFragment.OnPlayersListFragmentInteractionListener {

    SharedBackend sharedBackend = SharedBackend.getShared();

    // TODO: Rename and change types and number of parameters
    public static ScoringFragment newInstance() {
        ScoringFragment fragment = new ScoringFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final SharedBackend sharedBackend = SharedBackend.getShared();

    }

    AddPlayersFragment addPlayersFragment;
    SharedBackend shared = SharedBackend.getShared();
    FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoring, container, false);
        // Inflate the layout for this fragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add_player);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addPlayer(view);
                }
            });
        }
        addPlayer(null);
        return view;
    }

    public void addPlayer(View view) {
        addPlayersFragment = new AddPlayersFragment();
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (view == null) {
            transaction.add(R.id.scoring, addPlayersFragment).commit();
        } else {
            transaction.replace(R.id.scoring, addPlayersFragment).commit();
        }
    }

    private void showPlayers() {
        PlayersListFragment playersListFragment = new PlayersListFragment();
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.scoring, playersListFragment).commit();
    }

    public void showGameSheet(View view) throws IOException{
        if (shared.isGameSet()) {
            Intent intent = new Intent(getActivity(), ScoreSheet.class);
            startActivity(intent);

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

    public void addPlayerToGame() {
        EditText playerName = (EditText) addPlayersFragment.getView().findViewById(R.id.player_name);
        sharedBackend.addPlayer(new Player(playerName.getText().toString()));
        showPlayers();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
