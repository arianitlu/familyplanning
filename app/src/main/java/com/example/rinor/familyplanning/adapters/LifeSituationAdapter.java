package com.example.rinor.familyplanning.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.LifeSituation;

import java.util.List;

public class LifeSituationAdapter extends RecyclerView.Adapter<LifeSituationAdapter.MyViewHolder> {

    private List<LifeSituation> lifeSituations;

    public LifeSituationAdapter(List<LifeSituation> lifeSituations) {
        this.lifeSituations = lifeSituations;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewLifeSituation);
            imageView = itemView.findViewById(R.id.imageViewLifeSituation);

        }
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.life_situation_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LifeSituation list = lifeSituations.get(position);

        holder.name.setText(list.getLifeSituationName());
        /*holder.imageView.setImageResource(list.getIcon());*/

    }

    @Override
    public int getItemCount() {
        return lifeSituations.size();
    }


}
