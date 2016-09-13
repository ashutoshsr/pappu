package com.example.ashsrivast.pappu.scoresheet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ashsrivast.pappu.R;
import com.example.ashsrivast.pappu.entity.Player;
import com.example.ashsrivast.pappu.scoresheet.adapters.ListScoreSheetPlayersAdapter;
import com.example.ashsrivast.pappu.services.SharedBackend;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnScoreSheetFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoreSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreSheetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedBackend sharedBackend = SharedBackend.getShared();
    ListView listContainer;
    FragmentActivity activity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnScoreSheetFragmentInteractionListener mListener;

    public ScoreSheetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreSheetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreSheetFragment newInstance(String param1, String param2) {
        ScoreSheetFragment fragment = new ScoreSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_sheet, container, false);
        listContainer = (ListView) view.findViewById(R.id.score_sheet_player_score_list);
        showGameSheet();
        return view;
    }

    public void showGameSheet() {

        if (activity == null) {
            activity = this.getActivity();
        }

        listContainer.setAdapter(new ListScoreSheetPlayersAdapter(activity, R.id.score_sheet_player_score_list, sharedBackend.getPlayers()));

        listContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnScoreSheetFragmentInteractionListener) {
            mListener = (OnScoreSheetFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnScoreSheetFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnScoreSheetFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
