package com.example.view1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class ShowActivity extends AppCompatActivity {
    private  int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getIntent().getIntExtra("index",0);
        switch (mIndex){
            case 1:
                setContentView(R.layout.activity_dashbord);
                break;
            case 2:
                setContentView(R.layout.activity_piechart);
                break;
            case 3:
                setContentView(R.layout.activity_avatar);
                break;
                default:
                    setContentView(R.layout.activity_show);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
