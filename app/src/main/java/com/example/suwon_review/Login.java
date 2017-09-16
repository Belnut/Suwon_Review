package com.example.suwon_review;

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
            case R.id.student_login_btn:            // 학생 로그인 상황
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
            case R.id.resident_login_btn:           // 거주민 로그인 상황
            {
                member_type = 2;
                /*if(id.getText().equals("yeonil") && pwd.toString().equals("1q2w3e4r"))       //아이디 : 12050030, 비번 : 1q2w3e4r 로 고정
                {
                    Intent goToMain = new Intent(this, Filter_Main.class);
                    goToMain.putExtra("id", id.getText().toString());
                    goToMain.putExtra("pwd", pwd.getText().toString());

                    데이터 집어 넣는 부분 추가 필요함

                    startActivity(goToMain);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                    id.setText("");
                    pwd.setText("");
                }*/
                break;
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
