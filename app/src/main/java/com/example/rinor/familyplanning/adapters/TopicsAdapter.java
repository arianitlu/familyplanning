package com.example.rinor.familyplanning.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.model.Topics;

import java.util.HashMap;
import java.util.List;

public class TopicsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Topics> topics;
    private HashMap<Topics,String> listDataChild;


    public TopicsAdapter(Context context, List<Topics> topics, HashMap<Topics, String> listDataChild) {
        this.context = context;
        this.topics = topics;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this.topics.size();
    }

    @Override
    public int getChildrenCount(int i) {
        //return this.listDataChild.get(this.topics.get(i)).size();
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this.topics.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        topics.get(i).getTopicCategory();
        return this.getChild(i,i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Topics list = topics.get(i);

        String headerTitle = list.getTopicName();

        if( view == null) {

            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group,null);
        }

        TextView listHeader = (TextView)view.findViewById(R.id.listheader);
        listHeader.setTypeface(null,Typeface.BOLD);
        listHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {


        String childText=listDataChild.get(topics.get(i));

        // final Topics list = (Topics)getChild(i,i1);

        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item,null);
        }

        TextView txtListChild = (TextView)view.findViewById(R.id.topiclistitem);

        txtListChild.setText(childText);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
