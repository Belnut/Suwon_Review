package com.example.suwon_review;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 김형욱 on 2017-05-15.
 */

public class CustomDialog extends Dialog{
    private TextView txtTitle;
    private TextView txtScore;
    private TextView txtContents;
    private ImageView imgTitle;
    private ImageView imgScore;
    private Button btnReview;
    private Button btnCancel;
    private String title;
    private String score;
    private String contents;
    private Drawable imgT;
    private Drawable imgS;

    View.OnClickListener reviewClickListener;
    View.OnClickListener cancelClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.custom_dialog);

        txtTitle = (TextView)findViewById(R.id.textViewTitle);
        txtScore = (TextView)findViewById(R.id.textViewScore);
        txtContents = (TextView)findViewById(R.id.textViewContents);
        imgTitle = (ImageView)findViewById(R.id.imageViewTitle);
        imgScore = (ImageView)findViewById(R.id.imageViewScore);
        btnReview = (Button)findViewById(R.id.buttonReview);
        btnCancel = (Button)findViewById(R.id.buttonCancel);

        // 제목과 내용을 생성자에서 셋팅한다.
        txtTitle.setText(title);
        txtScore.setText(score);
        txtContents.setText(contents);
        imgTitle.setImageDrawable(imgT);

        if (reviewClickListener != null && cancelClickListener != null) {
            btnReview.setOnClickListener(reviewClickListener);
            btnCancel.setOnClickListener(cancelClickListener);
        } else if (reviewClickListener != null
                && cancelClickListener == null) {
            btnReview.setOnClickListener(reviewClickListener);
        } else {
        }
    }

    public CustomDialog(Context context, String title, String score, String content, Drawable imgTitle, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.title = title;
        this.score = score;
        this.contents = content;
        this.imgT = imgTitle;
        this.reviewClickListener = leftListener;
        this.cancelClickListener = rightListener;
    }
}
