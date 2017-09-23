package com.example.suwon_review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    static int member_type;             //사용자 구분용 변수 (권한 등등 디비에서 사용 가능)
    EditText id;
    EditText pwd;

    Button student_login;
    Button resident_login;
    Button register;
    Button find_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = (EditText)findViewById(R.id.input_id);
        pwd = (EditText)findViewById(R.id.input_pwd);

        student_login = (Button)findViewById(R.id.student_login_btn);
        resident_login = (Button)findViewById(R.id.resident_login_btn);
        register = (Button)findViewById(R.id.register_btn);
        find_pwd = (Button)findViewById(R.id.find_pwd_btn);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.student_login_btn:            // 회원 로그인 상황
            {
                member_type = 1;
                if(id.getText().toString().equals("12050030") && pwd.getText().toString().equals("1q2w3e4r"))       //아이디 : 12050030, 비번 : 1q2w3e4r 로 고정
                {
                    Intent goToMain = new Intent(this, Button_Test.class);
                    goToMain.putExtra("id", id.getText().toString());
                    goToMain.putExtra("pwd", pwd.getText().toString());

                    //데이터 집어 넣는 부분 추가 필요함

                    startActivity(goToMain);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                    id.setText("");
                    pwd.setText("");
                }
                break;
            }
            case R.id.resident_login_btn:           // 비회원 로그인 상황
            {
                member_type = 2;
                final AlertDialog.Builder ad = new AlertDialog.Builder(Login.this);

                ad.setTitle("비회원 로그인 닉네임 설정");
                ad.setMessage("원하시는 닉네임을 입력하세요.");

                final EditText et = new EditText(Login.this);
                ad.setView(et);

                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToMain = new Intent(getApplicationContext(), Button_Test.class);
                        String temp_name = et.getText().toString();         // 요놈이 임시 닉네임입니다.

                        startActivity(goToMain);
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

                //수정
            }
            case R.id.register_btn:
            {
                Intent goToRegister = new Intent(Login.this, Register.class);
                startActivity(goToRegister);
                break;
            }
            case R.id.find_pwd_btn:
            {
                Intent goToFindIdPwd = new Intent(this, Find_Id_Pwd.class);
                startActivity(goToFindIdPwd);
                break;
            }
        }
    }
}
