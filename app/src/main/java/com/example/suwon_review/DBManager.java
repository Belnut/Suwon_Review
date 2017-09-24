package com.example.suwon_review;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by whj on 2017-09-23.
 */

public class DBManager implements Serializable{
    //public ArrayList<GoodsList> goods_list;

    private String urlPath;
    public ArrayList<String> results;

    String myJSON;
    String out_name, out_mail, out_ph;
    private static final String TAG_RESULTS="result";
    JSONArray JSONm = null;


    /* PHP문 URL 주소들 */
    private final String PHP_REGISTER = "http://223.195.109.37/suwon_review/register.php";


    private final String PHP_LOGIN_VAILD_CHECK = "http://223.195.109.37/suwon_review/login_check.php";


    // 집어넣거나 뺄 데이터들
    private String user_id ,user_passwd, user_nicname, user_email;
    private String bulletin_title ,bulletin_goods_name, bulletin_userid, bulletin_content, bulletin_date;

    int comment_number;
    String comment_content, comment_userid, comment_date;





    //STAT 정의
    private final int STAT_REGISTER = 100;


    static int status = 100;
    // status 0 = 회원가입
    //
    static int get_status = 0;
    //상태에 따라서 DB실행


    //FLAG 정의
    private final int FLAG_LOGIN = 1000;

    int flag;


    class Manager extends AsyncTask<Void, Void, ArrayList<String>> {
        protected ArrayList<String> doInBackground(Void... voids) {

            if(status == STAT_REGISTER){
                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setRequestMethod("POST");

                    String param = "user_id=" + user_id + "&user_nicname=" + user_nicname + "&user_passwd=" + user_passwd + "&user_email=" + user_email;

                    OutputStream outputStream = con.getOutputStream();
                    outputStream.write(param.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    Log.i("DB 업데이트 상태", "STAT = " + status +", param = " + param);

                    BufferedReader rd = null;
                    rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                    String line = null;
                    while ((line = rd.readLine()) != null) {
                        Log.d("BufferdReader", line);
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.i("에러 발생" , "회원가입 데이터 입력 에러 (지원하지 않는 인코딩)");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.i("에러 발생" , "회원가입 데이터 입력 에러 (입출력)");
                    e.printStackTrace();
                }
            }

            return null;
        }

        protected void onPostExecute(ArrayList<String> qResults) {super.onPostExecute(qResults);}
    }

    //가입 데이터 입력
    protected ArrayList<String> inputRegisterData (String user_id, String user_nicname, String user_passwd, String user_email ) {

        urlPath = PHP_REGISTER;

        this.user_id = user_id;
        this.user_nicname = user_nicname;
        this.user_passwd = user_passwd;
        this.user_email = user_email;

        status = STAT_REGISTER;

        try {
            results = new Manager().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        return results;
    }













    //데이터 가져오는 부분
    //goods_detail_tbl

    //JSON class
    class GetDataJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();

                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String json;
                while((json = bufferedReader.readLine())!= null){
                    sb.append(json+"\n");
                }

                //Log.i("sb값", sb + "");
                return sb.toString().trim();

            }catch(Exception e){
                Log.i("GetDataJson 에러", "BackGround 실행중 오류");
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result){
            switch (flag) {
                //로그인 검사
                case FLAG_LOGIN: {
                    //myJSON = result;
                    //상태 값만 바꿔주면서 디비 실행 가능할 듯
                    checkLogin (result);
                    break;
                }

            }
        }
    }

    //작업예정 , 중복검사
    /*
    public void getFindSameUserInfo(String compare_data, int flag)
    {
        this.flag = flag;

        GetDataJSON g  = null;
        if(g != null)
            g= null;

        g = new GetDataJSON();


        if
        g.execute(url);
    }
    */

    //로그인 검사
    public void getLoginVaild(String user_id, String user_passwd)
    {
        this.flag = FLAG_LOGIN;

        GetDataJSON g  = null;
        if(g != null)
            g= null;

        g = new GetDataJSON();

        String url = PHP_LOGIN_VAILD_CHECK +"?user_id=" + user_id+ "&user_passwd=" + user_passwd;

        g.execute(url);
    }

    public void getData(String url, int flag){
        this.flag = flag;

        GetDataJSON g  = null;
        if(g != null)
            g= null;

        g = new GetDataJSON();
        g.execute(url);

    }


    private void checkLogin(String result)
    {
        if (result == null)
            Log.i("로그인", "데이터없음 (null)");

        else if (result.equals("ok"))
            Log.i("로그인", "로그인 성공");

        else if (result.equals("wrong pw"))
            Log.i("로그인", "로그인 실패(비밀번호 에러)");

        else
            Log.i("로그인", "로그인 실패(아이디 에러)");

    }

    /*
    // 디비 불러오기
    private void get_DB() {
        String user_id, user_pw, user_nicname, user_phone, user_sex, user_birth, user_mypage;
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONm = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0 ; i <  JSONm.length(); i++) {
                JSONObject c = JSONm.getJSONObject(i);

                user_id = c.getString("user_id");
                user_pw = c.getString("user_pw");
                user_nicname = c.getString("user_nicname");
                user_phone = c.getString("user_phone");
                user_sex = c.getString("user_sex");
                user_birth = c.getString("user_birth");
                user_mypage = c.getString("user_mypage");


                SignOutList tmp = new SignOutList(user_id, user_pw, user_nicname, user_phone, user_sex, user_birth, user_mypage);
                signout_list.add(tmp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logCheck();
    }
    */
}
