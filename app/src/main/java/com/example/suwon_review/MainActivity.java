package com.example.suwon_review;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec; // 탭의 메뉴와 컨텐츠 삭제
        Intent intent;

        //배달
        intent = new Intent().setClass(this, DeliveryActivity.class);
        spec = tabHost.newTabSpec("delivery").setIndicator("",getResources().getDrawable(R.drawable.delivery)).setContent(intent);
        tabHost.addTab(spec);

        //맛집
        intent = new Intent().setClass(this, ResActivity.class);
        spec = tabHost.newTabSpec("restaurant").setIndicator("",getResources().getDrawable(R.drawable.restaurant2)).setContent(intent);
        tabHost.addTab(spec);

        //홈
        intent = new Intent().setClass(this, HomeActivity.class);
        spec = tabHost.newTabSpec("main").setIndicator("홈").setContent(intent);
        tabHost.addTab(spec);

        //추천
        intent = new Intent().setClass(this, RecommendActivity.class);
        spec = tabHost.newTabSpec("recommend").setIndicator("",getResources().getDrawable(R.drawable.recommend)).setContent(intent);
        tabHost.addTab(spec);

        //리뷰
        intent = new Intent().setClass(this, VerticalSampleActivity.class);
        spec = tabHost.newTabSpec("review").setIndicator("",getResources().getDrawable(R.drawable.review)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}
