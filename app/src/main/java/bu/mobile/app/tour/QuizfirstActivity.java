package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class QuizfirstActivity extends AppCompatActivity {

    ImageView Oneimg;

    String result;

    Integer finalch = 0;
    int[] check = {14,14,14};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizfirst);

        Button Qbtn1 = (Button)findViewById(R.id.qbtn1);
        Button Qbtn2 = (Button)findViewById(R.id.qbtn2);
        Button Qbtn3 = (Button)findViewById(R.id.qbtn3);


        Random random = new Random();

        int type = random.nextInt(3);
        check[0] = type;



        if(type == 0){
            result ="각원사";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p1);

        }else if(type == 1){
            result ="경복궁";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p2);

        }else if(type == 2){
            result ="해운대";

            Oneimg = (ImageView) findViewById(R.id.oneimg);
            Oneimg.setImageResource(R.drawable.p3);

        }

        //Toast.makeText(getApplicationContext(),"중복확인"+check,Toast.LENGTH_LONG).show();
        Log.d("배열확인: ", Arrays.toString(check));
        Qbtn1.setText("각원사");
        Qbtn2.setText("경복궁");
        Qbtn3.setText("해운대");



    }


    public void btn1(View v){
        Button btn=(Button)findViewById(R.id.qbtn1);
        String usercheck=btn.getText().toString();
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);

        myIntent.putExtra("acount", finalch);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void btn2(View v){
        Button btn=(Button)findViewById(R.id.qbtn2);
        String usercheck=btn.getText().toString();
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);
        myIntent.putExtra("acount", finalch);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void btn3(View v){
        Button btn=(Button)findViewById(R.id.qbtn3);
        String usercheck=btn.getText().toString();
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(usercheck == result){
            //정답
            finalch++;
        }
        Intent myIntent = new Intent(this, QuizsecondActivity.class);
        myIntent.putExtra("acount", finalch);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+finalch,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }


}