package com.example.suwon_review;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;


/**
 * Created by 연일 on 2017-04-11.
 *
 * Modified by WHJ on 2017-09-24.
 * DB 추가
 */

public class Register extends Activity {

    //DB매니저
    DBManager dbManager;

    static int member = 0;
    static int checked_same_id = 2;

    AlertDialog mAlterDialog = null;

    EditText input_id, input_pwd, input_check_pwd, input_nickname, input_email;

    static int checked_overlap_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        dbManager = (DBManager)( getIntent().getSerializableExtra("dbManager") ) ;

        input_id = (EditText) findViewById(R.id.reg_input_id);
        input_pwd = (EditText) findViewById(R.id.reg_pwd);
        input_check_pwd = (EditText) findViewById(R.id.reg_check_pwd);
        input_nickname = (EditText) findViewById(R.id.reg_nickname);
        input_email = (EditText) findViewById(R.id.reg_email);

        //아이디가 텍스트 입력할때
        input_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checked_overlap_id = 0;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //확인하는 비밀번호 변경시마다 텍스트 변화 가능
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_check_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //비밀번호랑 똑같음
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //각각 정규표현식
    protected boolean checkId(String str) {
        boolean result = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$", str);

        if (str.length() > 0 && result)
            return true;
        else
            return false;
    }

    protected boolean checkPw(String str) {
        boolean result = Pattern.matches("^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^*+=-])(?=.*).{8,16}$", str);

        if (str.length() > 0 && result)
            return true;
        else
            return false;
    }

    protected boolean checkName(String str) {
        boolean result = Pattern.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,5}$", str);

        if (str.length() > 0 && result)
            return true;
        else
            return false;
    }

    protected boolean checkMail(String str) {
        boolean result = Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+.[a-zA-Z]{2,3}$", str);

        if (str.length() > 0 && result)
            return true;
        else
            return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //아이디 중복 디비 확인
            /*
            case R.id.reg_check_id: {
                dbm.chk_Id(input_id.getText().toString());
                if (!checkId(input_id.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "아이디를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checked_same_id == 1) {
                    Toast.makeText(getApplicationContext(), "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
                    checked_same_id = 0;
                    return;
                } else if (checked_same_id == 2) {
                    Toast.makeText(getApplicationContext(), "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            }*/
            case R.id.reg_ok_btn: {
                if (!checkId(input_id.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "아이디를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;             //아이디 체크 디비
                } else if (!checkId(input_id.getText().toString())/* || checked_same_id != 2*/) {
                    Toast.makeText(getApplicationContext(), "아이디(학번) 중복 체크를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!checkName(input_nickname.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "별명을 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!checkPw(input_pwd.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!(input_check_pwd.getText().toString()).equals((input_pwd.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 재확인 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!(checkMail(input_email.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "이메일을 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.i("값 확인", input_id.getText().toString() + input_pwd.getText().toString() +
                        input_nickname.getText().toString() +  input_email.getText().toString());
                /*
                dbm.account(input_id.getText().toString(), input_id.getText().toString(), input_pwd.getText().toString(),
                        input_name.getText().toString(), input_birth, input_ph, input_email.getText().toString());
                dbm.account_table(input_id.getText().toString());
                input_ph = "";
                input_birth = "";
                Toast.makeText(getApplicationContext(), "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();
                */


                //회원가입 데이터 삽입
                dbManager.inputRegisterData(input_id.getText().toString(), input_nickname.getText().toString(),
                                              input_pwd.getText().toString(), input_email.getText().toString());

                finish();
                break;
            }
            case R.id.reg_cancel_btn: {
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
                mAlterDialog = builder.setTitle("회원가입을 종료하시겠습니까?").
                        setPositiveButton("확인", listener).setNegativeButton("취소", listener).show();
                break;
            }
        }
    }

    public void onBackPressed() {
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
        mAlterDialog = builder.setTitle("회원가입을 종료하시겠습니까?").
                setPositiveButton("확인", listener).setNegativeButton("취소", listener).show();
    }

}
