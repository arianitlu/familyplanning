package com.example.rinor.familyplanning.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.InstitutionCategory;

import java.util.List;

public class InstitutionCategoryAdapter extends RecyclerView.Adapter<InstitutionCategoryAdapter.MyViewHolder>{

    private List<InstitutionCategory> categoriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            category = view.findViewById(R.id.textView);
            image = view.findViewById(R.id.heart_1);

        }
    }

    public InstitutionCategoryAdapter(List<InstitutionCategory> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.institutions_category_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        InstitutionCategory categories = categoriesList.get(i);

        myViewHolder.category.setText(categories.getCategoryName());
        myViewHolder.image.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}