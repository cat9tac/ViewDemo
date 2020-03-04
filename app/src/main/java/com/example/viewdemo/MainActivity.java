package com.example.viewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.viewdemo.measureAndXfermode.MesureAndXfermodeActivity;
import com.example.viewdemo.motionlayout.MotionLayoutActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private ArrayList<ViewActivity> mList = new ArrayList<ViewActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViewActivity();
        init();
    }


    private void init() {
        mRecyclerView = findViewById(R.id.rv_main);
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addViewActivity() {
        mList.add(new ViewActivity("图形的位置测量及Xfermode的使用", MesureAndXfermodeActivity.class));
        mList.add(new ViewActivity("MotionLayout", MotionLayoutActivity.class));
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_main_recycler_item);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<ViewActivity> list = new ArrayList<ViewActivity>();

        public MyAdapter(ArrayList<ViewActivity> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_main_recycler, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.mButton.setText(list.get(position).getName());
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, mList.get(position).activityClass);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewActivity {
        private String name;
        private Class<? extends AppCompatActivity> activityClass;

        public ViewActivity(String name, Class<? extends AppCompatActivity> activityClass) {
            this.name = name;
            this.activityClass = activityClass;
        }

        public String getName() {
            return name;
        }

        public Class<? extends AppCompatActivity> getActivityClass() {
            return activityClass;
        }
    }
}
