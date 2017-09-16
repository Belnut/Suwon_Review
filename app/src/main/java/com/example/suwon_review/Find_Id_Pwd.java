package com.example.suwon_review;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by 연일 on 2017-04-11.
 */

public class Find_Id_Pwd extends Activity{

    AlertDialog mAlterDialog = null;

    Button pwd_ok, pwd_cancel, id_ok;
    EditText pwd_input_id, pwd_input_mail, id_nickname, id_input_mail;
    GmailSender sender;
    String temp_pwd;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id_pwd);

        id_input_mail = (EditText)findViewById(R.id.find_id_email);
        id_nickname = (EditText)findViewById(R.id.find_id_nickname);
        pwd_input_id = (EditText)findViewById(R.id.input_id);
        pwd_input_mail = (EditText)findViewById(R.id.input_email);

        pwd_ok = (Button)findViewById(R.id.find_pwd_ok_btn);
        id_ok = (Button)findViewById(R.id.find_id_ok_btn);
        pwd_cancel = (Button)findViewById(R.id.find_pwd_cancel_btn);
    }

    //아이디 정규표현식 ( 학번이랑 아이디 두개 존재 )
    /*protected boolean checkId(String str)
    {
        boolean result = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$", str);

        if(str.length() > 0 && result)
            return true;
        else
            return false;
    }*/

    //이메일 정규 표현식
    protected boolean checkMail(String str)
    {
        boolean result = Pattern.matches("^[0-9a-zA-Z_\\-]+@[.0-9a-zA-Z_\\-]+$", str);

        if(str.length() > 0 && result)
            return true;
        else
            return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_id_ok_btn:  {
                if(id_input_mail.getText().toString().equals("tkad527@naver.com") && id_nickname.getText().toString().equals("안연일"))
                {
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case android.app.AlertDialog.BUTTON_POSITIVE:
                                    break;
                            }
                        }
                    };
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    mAlterDialog = builder.setTitle("아이디는 '12050030' 입니다.").
                            setPositiveButton("확인", listener).show();
                }
                else
                {
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case android.app.AlertDialog.BUTTON_POSITIVE:
                                    break;
                            }
                        }
                    };
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    mAlterDialog = builder.setTitle("입력하신 내용과 일치하는 정보가 없습니다.").
                            setPositiveButton("확인", listener).show();
                }
                break;
            }
            case R.id.find_pwd_ok_btn: {
                int[] a = new int[6];
                int i;
                for (i = 0; i < 6; i++)
                    a[i] = (int) (Math.random() * 10);

                //정규표현식 틀렸을 경우
                /*if(pwd_input_id.getText().equals("12050030")) {
                    Toast.makeText(getApplicationContext(), "아이디를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!(checkMail(pwd_input_mail.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "이메일을 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                */
                if (pwd_input_id.getText().toString().equals("12050030") && (pwd_input_mail.getText().toString().equals("tkad527@naver.com"))) {
                    temp_pwd = "suwon" + a[0] + a[1] + a[2] + a[3] + a[4] + a[5]; //임시 비밀번호 값
                    //디비에 비밀번호 변경 추가
                    sender = new GmailSender("it402lab@gmail.com", "ylcho402");

                    try {
                        sender.sendMail("수원대 오늘 뭐먹지!? 임시 비밀번호 지급 메일입니다.",
                                pwd_input_id.getText().toString() + "님의 임시 비밀번호는" + temp_pwd + "입니다.",
                                "it402lab@gmail.com",
                                pwd_input_mail.getText().toString());
                        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                            @Override
                            //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case AlertDialog.BUTTON_POSITIVE:
                                        finish();
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        mAlterDialog = builder.setTitle("임시 비밀번호 발송을 완료하였습니다.").
                                setPositiveButton("확인", listener).show();
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }

                } else {
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case android.app.AlertDialog.BUTTON_POSITIVE:
                                    break;
                            }
                        }
                    };
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    mAlterDialog = builder.setTitle("입력하신 내용과 일치하는 정보가 없습니다.").
                            setPositiveButton("확인", listener).show();
                }
                break;
            }
            case R.id.find_pwd_cancel_btn: {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case AlertDialog.BUTTON_POSITIVE:
                                finish();
                                break;
                            case AlertDialog.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                mAlterDialog = builder.setTitle("비밀번호 찾기를 종료하시겠습니까?").
                        setPositiveButton("확인", listener).setNegativeButton("취소", listener).show();
                break;
            }
        }
    }

    public void onBackPressed()
    {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            //dialoginterface = 취소, 종료를 위한 인터페이스 제공 // which = 어떤 버튼 혹은 아이템을 클릭 했는지 구분
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case AlertDialog.BUTTON_POSITIVE:
                        finish();
                        break;
                    case AlertDialog.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mAlterDialog = builder.setTitle("비밀번호 찾기를 종료하시겠습니까?").
                setPositiveButton("확인", listener).setNegativeButton("취소", listener).show();
    }
}
