package com.bupt.johnfrey.recyclerdemo;

/**
 *  Created by zhangfengyang on 16/5/5
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends Activity implements PersonAdapter.OnRecyclerViewListener {
    private List<Person> personList = new ArrayList<>();
    private int i;
    private PersonAdapter adapter;
    private RecyclerView rvRecycler;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_recycler);
        rvRecycler = (RecyclerView) findViewById(R.id.rv_recycler);
        rvRecycler.setHasFixedSize(true);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                Person person = new Person(i, "JohnF" + i, 10 + i);
                personList.add(1, person);
                adapter.notifyItemInserted(1);
                adapter.notifyItemRangeChanged(1, adapter.getItemCount());
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecycler.setLayoutManager(layoutManager);

        initData();
        adapter = new PersonAdapter(personList);
        adapter.setOnRecyclerViewListener(this);
        rvRecycler.setAdapter(adapter);

    }


    private void initData() {
        for (; i < 30; ++i) {
            Person person = new Person(i, "JohnF" + i, 10 + i);
            personList.add(person);
        }
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "short clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(int position) {
        Toast.makeText(this, "long clicked: " + position, Toast.LENGTH_SHORT).show();
        personList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        return true;
    }
}

