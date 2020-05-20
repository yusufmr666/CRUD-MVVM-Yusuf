package com.example.tugascuy.activity.ui.training;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugascuy.R;
import com.example.tugascuy.adapter.TrainingAdapter;
import com.example.tugascuy.model.TrainingModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment {

    private String TAG = "mvvm";
    private RecyclerView rv;
    private TrainingAdapter trainingAdapter;

    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_training, container, false);
        List<TrainingModel> trainingModels = new ArrayList<>();
        rv = root.findViewById(R.id.rv);
        rv.setAdapter(trainingAdapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        TrainingViewModel trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        trainingViewModel.getTraining().observe(getViewLifecycleOwner(), new Observer<List<TrainingModel>>() {
            @Override
            public void onChanged(List<TrainingModel> trainingModels) {
                trainingAdapter = new TrainingAdapter(getActivity(), trainingModels);
                rv.setAdapter(trainingAdapter);
            }
        });
        return root;
    }
}

