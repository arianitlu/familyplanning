package com.example.rinor.familyplanning.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.InstitutionAdapter;
import com.example.rinor.familyplanning.model.Institution;

import java.util.ArrayList;
import java.util.List;

public class FragmentInstitution extends Fragment implements InstitutionAdapter.InstitutionClickHandler {

    private static final String URL = "192.168.0.169/familyplanning/readInstitutions.php";
    private static final String LOGCAT_TAG="InstitutionsList";
    RecyclerView institutionRecyclerView;
    InstitutionAdapter adapter;
    List<Institution> list = new ArrayList<>();

    Dialog myDialog;

    TextView txtname, txtDescription, txtServices, txtWeb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_instution, container, false);

        myDialog = new Dialog(getActivity());

        institutionRecyclerView = view.findViewById(R.id.recyclerview_instutiton);
        institutionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new InstitutionAdapter(getInstitutionList(),this);

        institutionRecyclerView.setAdapter(adapter);

        myDialog.setCanceledOnTouchOutside(true);


        return view;
    }

    public List<Institution> getInstitutionList(){


        list.add(new Institution(1,"Kompani Programimit",
                "","","",1,2,"",""));

        list.add(new Institution(1,"Kompani Programimit",
                "","","",1,2,"",""));

        list.add(new Institution(1,"Kompani Programimit",
                "","","",1,2,"",""));

        list.add(new Institution(1,"Kompani Programimit",
                "","","",1,2,"",""));

        list.add(new Institution(1,"Kompani Programimit",
                "","","",1,2,"",""));

        return list;
    }

    @Override
    public void onClick(int id) {

        myDialog.setContentView(R.layout.popup_institution_name);

        TextView txtName = myDialog.findViewById(R.id.txtPopUpName);
        TextView txtDescription = myDialog.findViewById(R.id.txtPopUpDescription);

        txtName.setText(list.get(id).getName());
        txtDescription.setText(list.get(id).getDescription());

        myDialog.show();


        Institution objInstitution = list.get(id);

        //fillData(objInstitution);
    }

    public void fillData(Institution institution){
        txtname.setText(institution.getName());
        txtDescription.setText(institution.getDescription());
    }

   
}

