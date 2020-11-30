package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private HttpConnection httpConn = HttpConnection.getInstance();
    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        testText = (TextView)findViewById(R.id.location);
        sendData(); // 웹 서버로 데이터 전송

        Button button = (Button)findViewById(R.id.weather);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"동동",Toast.LENGTH_SHORT).show();
//                sendData();
                testText.setText("ddd");
            }
        });
    }
//    final Handler handler = new Handler()
//    {
//        public void handleMessage(Message msg)
//        {
//            httpConn.requestWebServer(callback);
//        }
//    };
    /** 웹 서버로 데이터 전송 */
    public void sendData() {
// 네트워크 통신하는 작업은 무조건 작업스레드를 생성해서 호출 해줄 것
        new Thread() {
            public void run() {
                httpConn.requestWebServer(callback);
//                Message msg = handler.obtainMessage();
//                handler.sendMessage(msg);
            }
        }.start();;
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {}
        Thread.interrupted();


    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:"+body);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    jsonParser(body);
//                    testText.setText(body);
                }
            });


        }
    };
    void jsonParser(String body){
        try {
            JSONObject jsonObject = new JSONObject(body);
            String location = jsonObject.getString("name");
//            JSONArray jarray = new JSONArray(body);   // JSONArray 생성
//            String name="";
//            for(int i=0; i < jarray.length(); i++){
//                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
////                String address = jObject.getString("address");
//                name = jObject.getString("name");
////                int age = jObject.getInt("age");
//            Log.d(TAG, "왜 안나와"+location);
            if(location.equals("Cheonan")){
                location = "천안시";
            }
            testText.setText(location);
//            Toast.makeText(getBaseContext(),"동동",Toast.LENGTH_SHORT).show();
//            tv.setText(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}