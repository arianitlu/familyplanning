package com.example.rinor.familyplanning;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rinor.familyplanning.adapters.ViewPagerAdapter;

public class SwipableIntro extends AppCompatActivity {

    private ViewPager slideView;
    private ViewPagerAdapter slideAdapter;
    private TextView [] mdots;

    private LinearLayout mDotLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipable_intro);


        slideView = findViewById(R.id.viewPager);

        mDotLayout = findViewById(R.id.linearLayout2);

        slideAdapter = new ViewPagerAdapter(this);

        slideView.setAdapter(slideAdapter);

        addDots(0);

        slideView.addOnPageChangeListener(viewListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_menu,menu);
        return true;

    }

    public void addDots(int position) {

        mdots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i =0; i<mdots.length; i++) {

            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mdots[i]);


        }

        if(mdots.length>0) {

            mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
               addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
