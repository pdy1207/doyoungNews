package com.example.dynews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Papago extends AppCompatActivity {

    TextView cityView;
    TextView weatherView;
    TextView tempView;
    LinearLayout weatherLay;
    static RequestQueue requestQueue;

    private EditText translationText;
    private Button translationButton,translationButton2;
    private TextView resultText;
    private String result;
    ImageButton btnSetting;
    private BackHandler backHandler = new BackHandler(this);
    Animation aniTouch,aniAsc,fadein,aniAsc2,aniAsc3,aniAsc4,anides;
    TextView dateView;
    // 백 그라운드에서 파파고 API와 연결하여 번역 결과를 가져옵니다.
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
        }

        @Override
        protected Integer doInBackground(Integer... arg0) {
            StringBuilder output = new StringBuilder();
            String clientId = "vf5VQOT3LMNPURkLJbaq"; // 애플리케이션 클라이언트 아이디 값";
            String clientSecret = "rYWX0LFsmo"; // 애플리케이션 클라이언트 시크릿 값";
            try {
                // 번역문을 UTF-8으로 인코딩합니다.
                String text = URLEncoder.encode(translationText.getText().toString(), "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

                // 파파고 API와 연결을 수행합니다.
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                // 번역할 문장을 파라미터로 전송합니다.
                String postParams = "source=en&target=ko&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                // 번역 결과를 받아옵니다.
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    output.append(inputLine);
                }
                br.close();
            } catch (Exception ex) {
                Log.e("SampleHTTP", "Exception in processing response.", ex);
                ex.printStackTrace();
            }
            result = output.toString();
            return null;
        }

        protected void onPostExecute(Integer a) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            if (element.getAsJsonObject().get("errorMessage") != null) {
                Log.e("번역 오류", "번역 오류가 발생했습니다. " +
                        "[오류 코드: " + element.getAsJsonObject().get("errorCode").getAsString() + "]");
            } else if (element.getAsJsonObject().get("message") != null) {
                // 번역 결과 출력
                resultText.setText(element.getAsJsonObject().get("message").getAsJsonObject().get("result")
                        .getAsJsonObject().get("translatedText").getAsString());
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// 시스템 날씨 가져오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        String getDay = simpleDateFormatDay.format(date);
        String getTime = simpleDateFormatTime.format(date);
        String getDate = getDay + "\n" + getTime;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);
        translationText = (EditText) findViewById(R.id.translationText);
        translationButton = (Button) findViewById(R.id.translationButton);
        translationButton2 = (Button) findViewById(R.id.translationButton2);

        weatherLay = (LinearLayout) findViewById(R.id.weatherLay);

        resultText = (TextView) findViewById(R.id.resultText);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);

        findViewById(R.id.btnSetting).setOnClickListener(mClick);


        aniTouch = AnimationUtils.loadAnimation(Papago.this, R.anim.scale);
        aniAsc = AnimationUtils.loadAnimation(Papago.this, R.anim.slide_in_bottom);
        aniAsc2 = AnimationUtils.loadAnimation(Papago.this, R.anim.slide_in_bottom);
        aniAsc3 = AnimationUtils.loadAnimation(Papago.this, R.anim.slide_in_bottom);
        aniAsc4 = AnimationUtils.loadAnimation(Papago.this, R.anim.slide_in_bottom);
        fadein = AnimationUtils.loadAnimation(Papago.this, R.anim.fadein);
        translationButton2.startAnimation(fadein);


        aniAsc.setStartOffset(1300);
        aniAsc2.setStartOffset(1800);
        aniAsc3.setStartOffset(2300);
        aniAsc4.setStartOffset(2800);
        fadein.setStartOffset(500);
//날씨 정보 가져오는 버튼
        translationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getVisibility() == View.VISIBLE)
                    translationButton2.setVisibility(v.INVISIBLE);
                else
                    translationButton2.setVisibility(v.VISIBLE);
                    weatherLay.setVisibility(v.VISIBLE);

                dateView = findViewById(R.id.dateView);
                dateView.setText(getDate);
                dateView.startAnimation(aniAsc);
                cityView = findViewById(R.id.cityView);
                weatherView = findViewById(R.id.weatherView);
                tempView = findViewById(R.id.tempView);
                cityView = (TextView) findViewById(R.id.cityView);
                weatherView = (TextView) findViewById(R.id.weatherView);
                tempView = (TextView) findViewById(R.id.tempView);
                cityView.startAnimation(aniAsc2);
                weatherView.startAnimation(aniAsc3);
                tempView.startAnimation(aniAsc4);
                //시간데이터와 날씨데이터 활용
                CurrentCall();

            }
        });
//번역 정보 가져오는 버튼
        translationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();

            }
        });
        //volley를 쓸 때 큐가 비어있으면 새로운 큐 생성하기
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    private void CurrentCall(){

        String url = "http://api.openweathermap.org/data/2.5/weather?q=Daejeon&appid=0a148ad677661072866c3ea06e34b3bf";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {

                    //System의 현재 시간(년,월,일,시,분,초까지)가져오고 Date로 객체화함
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);

                    //년, 월, 일 형식으로. 시,분,초 형식으로 객체화하여 String에 형식대로 넣음
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
                    String getDay = simpleDateFormatDay.format(date);
                    String getTime = simpleDateFormatTime.format(date);

                    //getDate에 개행을 포함한 형식들을 넣은 후 dateView에 text설정
                    String getDate = getDay + "\n" + getTime;
                    dateView.setText(getDate);

                    //api로 받은 파일 jsonobject로 새로운 객체 선언
                    JSONObject jsonObject = new JSONObject(response);


                    //도시 키값 받기
                    String city = jsonObject.getString("name");

                    cityView.setText(city);


                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);

                    String weather = weatherObj.getString("description");

                    weatherView.setText(weather);



                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));

                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);
                    tempView.setText(tempDo +  "°C");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }


    //뒤로가기
    public void onBackPressed() {
        backHandler.onBackPressed();
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSetting:
                    btnSetting.startAnimation(aniTouch);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    Intent act2 = new Intent(Papago.this, MainActivity.class);
                    startActivity(act2);
                    finish();
                    break;
            }
        }
    };

}