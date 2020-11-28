package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class QuizfirstActivity extends AppCompatActivity {

    ImageView Oneimg;
    Button Qbtn1 = (Button)findViewById(R.id.qbtn1);
    Button Qbtn2 = (Button)findViewById(R.id.qbtn2);
    Button Qbtn3 = (Button)findViewById(R.id.qbtn3);
    String result;

    Integer finalch = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizfirst);

        Random random = new Random();
        int[] check = {};
        int type = random.nextInt(3);
        check[0] = type;



        if(type == 1){
            result ="p1";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p1);

        }else if(type == 2){
            result ="p2";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p2);

        }else if(type == 3){
            result ="p3";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p3);

        }


        Qbtn1.setText("각원사");
        Qbtn2.setText("독립기념관");
        Qbtn3.setText("아라리오갤러리");



    }


    public void btn1(View v){
        Button btn=(Button)findViewById(R.id.qbtn1);
        String usercheck=btn.getText().toString();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);
        myIntent.putExtra("정답갯수", finalch);
        Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
    }
    public void btn2(View v){
        Button btn=(Button)findViewById(R.id.qbtn2);
        String usercheck=btn.getText().toString();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);
        myIntent.putExtra("정답갯수", finalch);
        Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
    }
    public void btn3(View v){
        Button btn=(Button)findViewById(R.id.qbtn3);
        String usercheck=btn.getText().toString();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);
        myIntent.putExtra("정답갯수", finalch);
        Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
    }


}