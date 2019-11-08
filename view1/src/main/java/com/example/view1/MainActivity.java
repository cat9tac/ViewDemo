package com.example.view1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mDashBordBtn;
    private Button mPieBordBtn;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashbord);
        setContentView(R.layout.activity_main0);
        mDashBordBtn = findViewById(R.id.button1);
        mDashBordBtn.setOnClickListener(this);
        mPieBordBtn = findViewById(R.id.button2);
        mPieBordBtn.setOnClickListener(this);
        mBtn = findViewById(R.id.button3);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ShowActivity.class);
        int index = 0;
        switch (v.getId()){
            case R.id.button1:
                index = 1;
                break;
            case R.id.button2:
                index = 2;
                break;

            case R.id.button3:
                index = 3;
                break;
        }
        intent.putExtra("index",index);
        startActivity(intent);
    }
}
