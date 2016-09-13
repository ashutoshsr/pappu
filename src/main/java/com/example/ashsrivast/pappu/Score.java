package com.example.ashsrivast.pappu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ashsrivast.pappu.scoresheet.ScoreSheet;
import com.example.ashsrivast.pappu.services.SharedBackend;

public class Score extends AppCompatActivity {

    private SharedBackend sharedBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout scoreLayout = (LinearLayout) findViewById(R.id.scoreLayout);
        setSupportActionBar(toolbar);
        sharedBackend = SharedBackend.getShared();

        Intent intent = getIntent();

    }

    public void showGameSheet(View view) {
        if (sharedBackend.isGameSet()) {
            Intent intent = new Intent(this, ScoreSheet.class);
            startActivity(intent);
        } else {
            Snackbar.make(view, R.string.less, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
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
}
