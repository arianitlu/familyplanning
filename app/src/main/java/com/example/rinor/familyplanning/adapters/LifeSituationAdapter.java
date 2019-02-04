package com.example.rinor.familyplanning.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.HomeActivity;
import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.model.LifeSituation;

import java.util.ArrayList;
import java.util.List;

public class LifeSituationAdapter extends RecyclerView.Adapter<LifeSituationAdapter.MyViewHolder> {

    private List<LifeSituation> lifeSituations;
    Context ctx;
    SharedPreferences sharedPreferences;
    String MY_PREF = "PREFERENCE";
    SharedPreferences.Editor editor;

    public LifeSituationAdapter(List<LifeSituation> lifeSituations,Context ctx) {
        this.lifeSituations = lifeSituations;
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.cardview_text);
            imageView = itemView.findViewById(R.id.card_view_image);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            LifeSituation lifeSituation = lifeSituations.get(position);

            editor.putInt("lifeSituationId",lifeSituation.getId());
            editor.commit();

            ctx.startActivity(new Intent(ctx,HomeActivity.class));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lifesituation_card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LifeSituation list = lifeSituations.get(position);

        holder.name.setText(list.getLifeSituationName());
        holder.imageView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return lifeSituations.size();
    }


}

