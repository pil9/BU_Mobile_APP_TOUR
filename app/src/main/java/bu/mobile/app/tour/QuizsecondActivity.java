package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class QuizsecondActivity extends AppCompatActivity {

    ImageView Twoimg;

    String result;
    String resulttext;
    int nowagree;
    int[] check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizsecond);

        TextView textch = (TextView)findViewById(R.id.chtext);


        Intent secondIntent = getIntent();
        nowagree=secondIntent.getIntExtra("acount",0);
        check = secondIntent.getIntArrayExtra("mchch");

        //Toast.makeText(getApplicationContext(),"mon"+check,Toast.LENGTH_SHORT).show();


        Random random = new Random();
        int type = 0;
        while(true){
            type = random.nextInt(3);
            if(check[0] != type){
                check[1] = type;
                break;
            }
        }

        Log.d("배열확인: ", Arrays.toString(check));
        Log.d("답 갯: ", "value"+nowagree);

        if(type == 0){
            result ="각원사";
            resulttext = "ㄱㅇㅅ";

            Twoimg = (ImageView) findViewById(R.id.twoimg);
            Twoimg.setImageResource(R.drawable.p1);
            textch.setText(resulttext);

        }else if(type == 1){
            result ="독립기념관";
            resulttext = "ㄷㄹㄱㄴㄱ";

            Twoimg = (ImageView) findViewById(R.id.twoimg);
            Twoimg.setImageResource(R.drawable.p2);
            textch.setText(resulttext);

        }else if(type == 2){
            result ="아라리오갤러리";
            resulttext = "ㅇㄹㄹㅇㄱㄹㄹ";

            Twoimg = (ImageView) findViewById(R.id.twoimg);
            Twoimg.setImageResource(R.drawable.p3);
            textch.setText(resulttext);

        }


    }

    public void twobtn(View v){
        TextView usettext = (TextView)findViewById(R.id.usertext);
        String usercheck=usettext.getText().toString();
        Log.d("사용자 입력값 : ", usercheck);
        Log.d("문제 정답 : ", result);
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(usercheck.equals(result)){
            //정답
            Log.d("2번 문제 정답!",result);
            nowagree++;
        }
        Intent myIntent = new Intent(this, QuizthirdActivity.class);
        myIntent.putExtra("acount", nowagree);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+nowagree,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }





}