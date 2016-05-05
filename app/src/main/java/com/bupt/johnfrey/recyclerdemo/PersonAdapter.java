package com.bupt.johnfrey.recyclerdemo;

/**
 *  Created by zhangfengyang on 16/5/5
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter {
    //Interface for the activity to implement(in order to handle event in activity)
    public static interface RecyclerViewImp {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private RecyclerViewImp recyclerViewImp;
    private List<Person> list;

    //Constructor
    public PersonAdapter(List<Person> list) {
        this.list = list;
    }

    //Specify the RecyclerView to listen to
    public void setOnRecyclerViewListener(RecyclerViewImp recyclerViewImp) {
        this.recyclerViewImp = recyclerViewImp;
    }

    //Method to be called whenever an item's layout is loaded
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adp_recycler, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    //Method to be called whenever an item's data info is loaded
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        Person person = list.get(i);
        holder.tvName.setText(person.getName());
        holder.tvAge.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //ViewHolder Class
    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public View rootView;
        public TextView tvName;
        public TextView tvAge;

        public PersonViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_person_name);
            tvAge = (TextView) itemView.findViewById(R.id.tv_item_person_age);
            rootView = itemView.findViewById(R.id.ll_item_person);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (null != recyclerViewImp) {
                recyclerViewImp.onItemClick(this.getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != recyclerViewImp) {
                return recyclerViewImp.onItemLongClick(this.getPosition());
            }
            return false;
        }
    }

}
