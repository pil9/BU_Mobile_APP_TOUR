package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends Fragment {

    private static final String TAG = "pil9";
    private HttpConnection httpConn = HttpConnection.getInstance();
    TextView location_now;
    TextView weather_now;
    TextView temp_now;
    ImageView imageView;
    Bitmap bitmap;
    ImageView best_img;
    ImageView worst_img;
    TextView best_name;
    TextView best_address;
    TextView worst_name;
    TextView worst_address;
    TextView rest_rain;
    TextView sun_rain;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_weather, container, false);
        location_now = (TextView)v.findViewById(R.id.location);
        weather_now = (TextView)v.findViewById(R.id.weather);
        temp_now = (TextView)v.findViewById(R.id.temp);
        imageView =(ImageView)v.findViewById(R.id.weatherimg);
        best_img = (ImageView)v.findViewById(R.id.iv_image);
        worst_img = (ImageView)v.findViewById(R.id.iv_image2);
        best_name = (TextView)v.findViewById(R.id.tv_name);
        best_address = (TextView)v.findViewById(R.id.tv_address);
        worst_name = (TextView)v.findViewById(R.id.tv_name2);
        worst_address = (TextView)v.findViewById(R.id.tv_address2);
        rest_rain = (TextView)v.findViewById(R.id.rest_rain);
        sun_rain = (TextView)v.findViewById(R.id.sun_rain);
        sendData();
        return v;
    }

    public void getWeatherImg(String imgid) {
        new Thread() {
            public void run() {
                try{
                    URL url = new URL("http://openweathermap.org/img/wn/"+imgid+"@4x.png");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                    conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Thread.interrupted();
    }

    public void sendData() {
        new Thread() {
            public void run() {
                httpConn.requestWebServer(callback);
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Thread.interrupted();
    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:" + body);
//            jsonParser(body);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    jsonParser(body);
                }
            });


        }
    };

    void jsonParser(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            String location = jsonObject.getString("name");

            //배열 들어간 json 처리할 때
            JSONArray jArrObject = jsonObject.getJSONArray("weather");
            String weather_name = "";
            String Weather_icon = "";
            for (int i = 0; i < jArrObject.length(); i++) {
                JSONObject jObject = jArrObject.getJSONObject(i);
                String Weather_id = jObject.getString("id");
                Weather_icon = jObject.getString("icon");

                //비올 때 테스트
                Weather_id = "200";
                Weather_icon = "09d";

                weather_name = wDescEngToKor(Integer.parseInt(Weather_id));
                if (Integer.parseInt(Weather_id) > 701){
                    best_img.setImageResource(R.drawable.best_1);
                    best_name.setText("광덕사");
                    best_address.setText("충청남도 천안시 동남구 광덕면 광덕사길 30 (광덕면)");
                    worst_img.setImageResource(R.drawable.worst_1);
                    worst_name.setText("천안향교");
                    worst_address.setText("충청남도 천안시 동남구 향교1길 89 (유량동)");
                }
                else{
                    sun_rain.setText("(비가 올 때 가기 좋은 곳!)");
                    best_img.setImageResource(R.drawable.worst_2);
                    best_name.setText("아라리오갤러리");
                    best_address.setText("충청남도 천안시 동남구 만남로 43 (신부동) 신세계백화점 내");

                    rest_rain.setText("(비가 와서 놀기 힘들어요!)");
                    worst_img.setImageResource(R.drawable.best_2);
                    worst_name.setText("소노벨 천안 오션파크");
                    worst_address.setText("충청남도 천안시 동남구 성남면 종합휴양지로 200 (대명리조트)");
                }

            }

            String temp_main = jsonObject.getString("main");
            JSONObject jsonObject1 = new JSONObject(temp_main);
//            Log.d(TAG, (jsonObject1.getInt("temp")));
            String temp = String.format("%.1f", Double.valueOf(jsonObject1.getString("temp")))+"°C";
            Log.d(TAG, "여기"+temp);
            getWeatherImg(Weather_icon);


            if (location.equals("Cheonan")) {
                location = "천안시";
            }
            location_now.setText(location);
            weather_now.setText(weather_name);
            temp_now.setText(temp);
            imageView.setImageBitmap(bitmap);
//            Toast.makeText(getBaseContext(),"동동",Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //API 결과 값 한글로 번역 -__-
    String wDescEngToKor(int w_id) {
        int[] w_id_arr = {201, 200, 202, 210, 211, 212, 221, 230, 231, 232,
                300, 301, 302, 310, 311, 312, 313, 314, 321, 500,
                501, 502, 503, 504, 511, 520, 521, 522, 531, 600,
                601, 602, 611, 612, 615, 616, 620, 621, 622, 701,
                711, 721, 731, 741, 751, 761, 762, 771, 781, 800,
                801, 802, 803, 804, 900, 901, 902, 903, 904, 905,
                906, 951, 952, 953, 954, 955, 956, 957, 958, 959,
                960, 961, 962};
        String[] w_kor_arr = {"가벼운 비를 동반한 천둥구름", "비를 동반한 천둥구름", "폭우를 동반한 천둥구름", "약한 천둥구름",
                "천둥구름", "강한 천둥구름", "불규칙적 천둥구름", "약한 연무를 동반한 천둥구름", "연무를 동반한 천둥구름",
                "강한 안개비를 동반한 천둥구름", "가벼운 안개비", "안개비", "강한 안개비", "가벼운 적은비", "적은비",
                "강한 적은비", "소나기와 안개비", "강한 소나기와 안개비", "소나기", "악한 비", "중간 비", "강한 비",
                "매우 강한 비", "극심한 비", "우박", "약한 소나기 비", "소나기 비", "강한 소나기 비", "불규칙적 소나기 비",
                "가벼운 눈", "눈", "강한 눈", "진눈깨비", "소나기 진눈깨비", "약한 비와 눈", "비와 눈", "약한 소나기 눈",
                "소나기 눈", "강한 소나기 눈", "박무", "연기", "시야흐림", "모래 먼지", "안개", "모래", "먼지", "화산재", "돌풍",
                "토네이도", "구름 한 점 없는 맑은 하늘", "약간의 구름이 낀 하늘", "드문드문 구름이 낀 하늘", "구름이 거의 없는 하늘",
                "구름으로 뒤덮인 흐린 하늘", "토네이도", "태풍", "허리케인", "한랭", "고온", "바람부는", "우박", "바람이 거의 없는",
                "약한 바람", "부드러운 바람", "중간 세기 바람", "신선한 바람", "센 바람", "돌풍에 가까운 센 바람", "돌풍",
                "심각한 돌풍", "폭풍", "강한 폭풍", "허리케인"};
        String Weather = "";
        for (int i = 0; i < w_id_arr.length; i++) {
            if (w_id_arr[i] == w_id) {
                Weather = w_kor_arr[i];
                break;
            }
        }
        return Weather;
    }
}