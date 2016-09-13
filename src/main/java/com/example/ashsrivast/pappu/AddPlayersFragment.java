package com.example.ashsrivast.pappu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.services.SharedBackend;

public class AddPlayersFragment extends Fragment {
    public final static String EXTRA_STRING = "com.example.ashsrivast.pappu.STRING";

    SharedBackend sharedBackend = SharedBackend.getShared();

    // TODO: Rename and change types and number of parameters
    public static AddPlayersFragment newInstance() {
        AddPlayersFragment fragment = new AddPlayersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final SharedBackend sharedBackend = SharedBackend.getShared();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_players, container, false);
        return view;
    }
}
