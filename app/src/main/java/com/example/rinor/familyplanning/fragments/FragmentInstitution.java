package com.example.rinor.familyplanning.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.InstitutionAdapter;
import com.example.rinor.familyplanning.model.Institution;

import java.util.ArrayList;
import java.util.List;

public class FragmentInstitution extends Fragment {

    RecyclerView institutionRecyclerView;
    InstitutionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_instution, container, false);

        institutionRecyclerView = view.findViewById(R.id.recyclerview_instutiton);
        institutionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new InstitutionAdapter(getInstitutionList(),getContext());

        institutionRecyclerView.setAdapter(adapter);

        return view;
    }

    public List<Institution> getInstitutionList(){

        List<Institution> list = new ArrayList<>();

        list.add(new Institution("AdaptivIt","Kompani Programimit",
                "","","",""));

        list.add(new Institution("Qeveria Kosoves","Kompani Programimit",
                "","","",""));

        list.add(new Institution("Gjykata   ","Kompani Programimit",
                "","","",""));

        list.add(new Institution("ATK","Kompani Programimit",
                "","","",""));

        return list;
    }
}
