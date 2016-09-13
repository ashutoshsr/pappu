package com.example.ashsrivast.pappu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ashsrivast.pappu.services.SharedBackend;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class StartNewGame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    SharedBackend sharedBackend = SharedBackend.getShared();
    // TODO: Rename and change types of parameters


    public StartNewGame() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
           /* setContentView(R.layout.start_new_game);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            sharedBackend = SharedBackend.getShared();

            EditText editText = (EditText) findViewById(R.id.game_name);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.start_new_game, container, false);
    }
}
