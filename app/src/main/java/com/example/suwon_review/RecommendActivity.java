package com.example.suwon_review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class RecommendActivity extends AppCompatActivity {
    ImageView imgRandom;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        imgRandom = (ImageView)findViewById(R.id.imageViewRandom);
        btnStart = (Button)findViewById(R.id.button);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnStart.getText() == "주사위 굴리기") {
                    imgRandom.startAnimation(anim);
                    btnStart.setText("스탑");
                }
                else
                {
                    imgRandom.startAnimation(anim2);
                    btnStart.setText("주사위 굴리기");
                }
            }
        });
    }
}
