package com.example.suwon_review;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {
    TextView textViewDeli;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        textViewDeli = (TextView) findViewById(R.id.textViewDeli);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        textViewDeli.setTypeface(typeface);

        ListView listview;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.listviewDeli);
        listview.setAdapter(adapter);

        /* // alert 다이얼로그
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 다이얼로그

                ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(i);

                AlertDialog.Builder alt = new AlertDialog.Builder(DeliveryActivity.this);
                alt.setMessage(item.getDesc()).setCancelable(false).setPositiveButton("리뷰 쓰러 가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //리뷰~
                    }
                }).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = alt.create();
                alert.setTitle(item.getTitle());
                alert.setIcon(item.getIcon());
                alert.show();
            }
        });
        */
        //커스텀 다이얼로그
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(i);

                customDialog = new CustomDialog(DeliveryActivity.this, item.getTitle(), "3.5", item.getDesc(), item.getIcon(), reviewListener, cancelListener);
                customDialog.show();
            }
        });

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.oakryu), "옥류관", "★★☆☆☆", "20년 전통 중화요리\n\n육개장 떡만두국 갈비탕 내장탕\n\n탕수육+짜장+만두 12000\n\n탕수육中+짜장+짬뽕+만두 16000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.easydon), "이찌돈", "★★★☆☆", "비교를 거부하는 맛!\n\n로스까스(부드러운 돈까스) 7000\n치즈돈까스 7000\n매콤돈까스 6500\n매콤치즈돈까스 7500");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.fox), "여빈여우 한식", "★★★★☆", "공기밥 + 기본반찬 5가지\n\n된장찌개 5000\n돼지김치찌개 6000\n참치김치찌개 6000\n어묵김치찌개 6000\n해물순두부찌개 6000");
    }

    View.OnClickListener reviewListener = new View.OnClickListener(){
        public void onClick(View v){
            //Toast.makeText(getApplicationContext(), "리뷰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DeliveryActivity.this, FeedBack.class);
            startActivity(intent);
        }
    };

    View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            customDialog.cancel();
        }
    };
}
