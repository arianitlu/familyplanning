package com.example.rinor.familyplanning.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.Institution;

import java.util.List;

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.MyViewHolder> {

    private List<Institution> listInstitution;

    public InstitutionAdapter(List<Institution> listInstitution) {
        this.listInstitution = listInstitution;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description;
        public ImageView image, logo;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            description = itemView.findViewById(R.id.txtDescription);

            image = itemView.findViewById(R.id.imageView);
            logo = itemView.findViewById(R.id.logo);
        }

    }

    @Override
    public InstitutionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.institution_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstitutionAdapter.MyViewHolder holder, int position) {
        Institution list = listInstitution.get(position);

        holder.name.setText(list.getName());
        holder.description.setText(list.getDescription());

    }

    public interface InstitutionClickHandler {
        void onClick(int id);
    }

    @Override
    public int getItemCount() {
        return listInstitution.size();
    }


}

