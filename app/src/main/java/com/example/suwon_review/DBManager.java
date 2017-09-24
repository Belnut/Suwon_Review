package com.example.suwon_review;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

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




    // 집어넣거나 뺄 데이터들
    private String user_id ,user_passwd, user_nicname, user_email;
    private String bulletin_title ,bulletin_goods_name, bulletin_userid, bulletin_content, bulletin_date;

    int comment_number;
    String comment_content, comment_userid, comment_date;


    //Context mContext;

    private final int STAT_REGISTER = 100;


    static int status = 100;
    // status 0 = 회원가입
    //
    static int get_status = 0;
    //상태에 따라서 DB실행


    /*
    public DBManager(Context val)
    {
        mContext = val;
    }
    */

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
}
