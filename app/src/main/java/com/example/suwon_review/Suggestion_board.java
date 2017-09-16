package com.example.suwon_review;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 연일 on 2017-05-15.
 */

public class Suggestion_board extends Activity {

    private ArrayList<ListviewItem> listViewItemList = new ArrayList<ListviewItem>() ;

    ListView listview;
    ListViewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_board);

        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.suggestion_board);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListviewItem item = (ListviewItem) parent.getItemAtPosition(position);

                final String titleStr = item.getTitle();
                final String descStr = item.getDesc();                    // 수정 권한 위한 값
            }
        });
        listview.setOnItemLongClickListener(new ListViewItemLongClickListener());
    }


    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.sug_add_btn: {
                final AlertDialog.Builder ad = new AlertDialog.Builder(Suggestion_board.this);

                ad.setTitle("Recommand_Restaurant");
                ad.setMessage("추천하시는 음식점 이름을 입력하세요");

                final EditText et = new EditText(Suggestion_board.this);
                ad.setView(et);

                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String res_name = et.getText().toString();
                        adapter.addItem(res_name, "안연일");

                        dialog.dismiss();
                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });

                ad.show();
                break;
            }
        }
    }

    private class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
             final ListviewItem item = (ListviewItem)parent.getItemAtPosition(position);

            final AlertDialog.Builder ad = new AlertDialog.Builder(Suggestion_board.this);

            ad.setTitle("Recommand_Restaurant");
            ad.setMessage("추천하시는 음식점 이름을 입력하세요");

            final EditText et = new EditText(Suggestion_board.this);
            ad.setView(et);

            ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String res_name = et.getText().toString();
                    item.setTitle(res_name);
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "제목이 수정되었습니다.", Toast.LENGTH_LONG).show();
                }
            });
            ad.setNegativeButton("Delete", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    adapter.delItem(position);
                    dialog.dismiss();
                }
            });
            ad.show();
            return false;
        }
    }
}
