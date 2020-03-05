package com.example.viewdemo.motionlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewdemo.R;

public class MotionLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView;
    private boolean toggle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_layout_start);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.tv_a);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //属性动画
        // mTextView.animate().alpha(0).setDuration(1000).start();
        MotionLayout root = (MotionLayout) mTextView.getParent();
        //TransitionManager 过度动画使用方式1
      /*  TransitionManager.beginDelayedTransition(viewGroup);
        MotionLayout.LayoutParams params = (MotionLayout.LayoutParams) mTextView.getLayoutParams();
        params.bottomToBottom = MotionLayout.LayoutParams.PARENT_ID;
        mTextView.setLayoutParams(params);*/

        //TransitionManager 过度动画使用方式2
       /* Scene start = Scene.getSceneForLayout(viewGroup, R.layout.activity_motion_layout_start, MotionLayoutActivity.this);
        Scene end = Scene.getSceneForLayout(viewGroup, R.layout.activity_motion_layout_end, MotionLayoutActivity.this);
        if (toggle) {
            TransitionManager.go(end);
        } else {
            TransitionManager.go(start);
        }
        initView();//因为调用go 会清空父容器所有View 需要重新绑定数据*/

        //TransitionManager 结合ConstraintLayout
       /* TransitionManager.beginDelayedTransition(root);
        ConstraintSet constraintSet = new ConstraintSet();
        if(toggle){
            constraintSet.clone(getApplicationContext(),R.layout.activity_motion_layout_end);
        }else {
            constraintSet.clone(getApplicationContext(),R.layout.activity_motion_layout_start);
        }
        constraintSet.applyTo(root);*/

       //MotionLayout
        //见 xml文件中的 app:layoutDescription="@xml/motion_scene_cat"

        toggle = !toggle;
    }
}
