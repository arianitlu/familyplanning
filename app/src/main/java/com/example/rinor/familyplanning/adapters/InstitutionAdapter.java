package com.example.rinor.familyplanning.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.Institution;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.MyViewHolder> {

        private List<Institution> listInstitution;
        private Context ctx;

        public InstitutionAdapter(List<Institution> listInstitution, Context ctx) {
            this.listInstitution = listInstitution;
            this.ctx = ctx;
            }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView name,description;
            public ImageView image,logo;

            public MyViewHolder(View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);

                name = itemView.findViewById(R.id.txtName);
                description = itemView.findViewById(R.id.txtDescription);

                image = itemView.findViewById(R.id.imageView);
                logo = itemView.findViewById(R.id.logo);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Institution list = listInstitution.get(position);

//                Intent intent = new Intent(ctx, ListLocationsDetailsActivity.class);
//                intent.putExtra("name", list.getName());
//                intent.putExtra("type", list.getType());
//                intent.putExtra("describtion", list.getDescribtion());
//                intent.putExtra("latitude",list.getmLatitude());
//                intent.putExtra("longitude",list.getmLongitude());
                //ctx.startActivity(intent);
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

//            Picasso.get()
//                    .load(list.getImage())
//                    .into(holder.image);
//            Picasso.get()
//                    .load(list.getLogo())
//                    .into(holder.logo);

        }

    @Override
    public int getItemCount() {
        return listInstitution.size();
    }
}
