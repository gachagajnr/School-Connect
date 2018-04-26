package com.example.eduh_mik.schoolconnect.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eduh_mik.schoolconnect.R;
import com.example.eduh_mik.schoolconnect.adapters.ActivitiesAdapter;
import com.example.eduh_mik.schoolconnect.interfaces.OnFragmentInteractionListener;
import com.example.eduh_mik.schoolconnect.models.Activities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eduh_mik on 4/21/2018.
 */

public class ActivitiesFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //Unbinder unbinder;
    private OnFragmentInteractionListener mListener;
    private ActivitiesAdapter activitiesAdapter;
    private List<Activities> activitiesList = new ArrayList<>();

    String children[] = {"All Students","John mark","Blue dark","Skyline best","Ken SCreept", "Yung Gani"};


    public ActivitiesFragment() {
        // Required empty public constructor
    }

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        ButterKnife.bind(this, view);
        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                showdialog();
            }
        });
        return view;
    }
    private void showdialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_activity, null);
        dialogBuilder.setView(dialogView);


        final AlertDialog b = dialogBuilder.create();
        b.show();



    }

    @Override
    public void onResume() {
        super.onResume();
        prepareActivitiesData();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activitiesAdapter = new ActivitiesAdapter(getActivity(), activitiesList);
        recyclerView.setAdapter(activitiesAdapter);
        prepareActivitiesData();
    }
    private void prepareActivitiesData() {
        Activities activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);
        activities = new Activities("Jayden Munene", "Littel Gems", "Swimming", "Musical Classes", "Physical Education");
        activitiesList.add(activities);

        activitiesAdapter.notifyDataSetChanged();
}

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
