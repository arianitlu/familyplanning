package com.example.rinor.familyplanning.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.Language;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    private List<Language> languageList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView language_icon;
        public TextView language_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            language_icon = itemView.findViewById(R.id.language_imageview);
            language_text = itemView.findViewById(R.id.language_text);
        }
    }

    public LanguageAdapter(List<Language> languages) {
        this.languageList = languages;
    }

    @Override
    public LanguageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Language language = languageList.get(i);

        myViewHolder.language_text.setText(language.getLanguageName());
        Picasso.get().load(language.getLanguageFlag()).into(myViewHolder.language_icon);
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

}
