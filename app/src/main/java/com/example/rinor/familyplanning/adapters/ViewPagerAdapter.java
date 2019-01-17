package com.example.rinor.familyplanning.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }


    public int [] slideImages = {R.drawable.welcomelogo,R.drawable.welcomelogo,R.drawable.welcomelogo,R.drawable.welcomelogo};

    public String [] introCounter = {"Intro 1","Intro 2","Intro 3","Intro 4"};

    public String [] introDescription ={"Lorem ipsum dolor sit amet, modus iriure recteque pri cu. Case porro noluisse an est","Lorem ipsum dolor sit amet, modus iriure recteque pri cu. Case porro noluisse an est","Lorem ipsum dolor sit amet, modus iriure recteque pri cu. Case porro noluisse an est","Lorem ipsum dolor sit amet, modus iriure recteque pri cu. Case porro noluisse an est"};

    @Override
    public int getCount() {
        return slideImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView)view.findViewById(R.id.imageView2);
        TextView txtIntroNum = (TextView)view.findViewById(R.id.textView);
        TextView txtDescription = (TextView)view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slideImages[position]);
        txtIntroNum.setText(introCounter[position]);

        txtDescription.setText(introDescription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
