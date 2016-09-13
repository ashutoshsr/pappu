package com.example.ashsrivast.pappu;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ashsrivast.pappu.services.SharedBackend;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class LandingPage extends AppCompatActivity implements LandingPageFragment.OnLandingPageFragmentInteractionListener, PlayersListFragment.OnPlayersListFragmentInteractionListener {
    public final static String EXTRA_STRING = "com.example.ashsrivast.pappu.STRING";

    SharedBackend sharedBackend = SharedBackend.getShared();
    ScoringFragment scoringFragment;
    FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_landing_page);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            sharedBackend = SharedBackend.getShared();
            showLandingPage(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    public void showLandingPage(View view) {
        LandingPageFragment landingFragment = new LandingPageFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.landing, landingFragment).commit();
    }

    public void startNewGame(View view) {
        StartNewGame scoringFragment = new StartNewGame();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.landing, scoringFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game: {
                setContentView(R.layout.start_new_game);
            }
            return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void saveGameName(View view) {
        scoringFragment = new ScoringFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.landing, scoringFragment);
        transaction.show(scoringFragment).commit();
    }

    public void addPlayerToGame(View view) {
        scoringFragment.addPlayerToGame();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.landing, scoringFragment);
        transaction.show(scoringFragment).commit();
    }

    public void showGameSheet(View view) throws IOException {
        scoringFragment.showGameSheet(view);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        // do-nothing
    }
}
