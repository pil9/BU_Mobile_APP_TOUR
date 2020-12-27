package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class QuizthirdActivity extends AppCompatActivity {

    ImageView Treeimg;

    String result;
    String selectname;
    int nowagree;
    int[] check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizthird);

        TextView tourname = (TextView)findViewById(R.id.tourname);

        Intent secondIntent = getIntent();
        nowagree=secondIntent.getIntExtra("acount",0);
        check = secondIntent.getIntArrayExtra("mchch");

        //Toast.makeText(getApplicationContext(),"mon"+check,Toast.LENGTH_SHORT).show();

        Random random = new Random();
        int type = 0;
        while(true){
            type = random.nextInt(3);
            if(check[0] != type && check[1] != type ){
                check[2] = type;
                break;
            }
        }

        Log.d("배열확인: ", Arrays.toString(check));
        Log.d("답 갯: ", "value"+nowagree);

        if(type == 0){
            result ="각원사";


            Treeimg = (ImageView) findViewById(R.id.treeimg);
            Treeimg.setImageResource(R.drawable.p1);


        }else if(type == 1){
            result ="경복궁";


            Treeimg = (ImageView) findViewById(R.id.treeimg);
            Treeimg.setImageResource(R.drawable.p2);


        }else if(type == 2){
            result ="해운대";


            Treeimg = (ImageView) findViewById(R.id.treeimg);
            Treeimg.setImageResource(R.drawable.p3);


        }


        int qtype = random.nextInt(3);
        if(qtype == 0){
            selectname ="각원사";
        }else if(qtype == 1){
            selectname ="경복궁";
        }else if(qtype == 2){
            selectname ="해운대";
        }
        tourname.setText(selectname+" 이다.");


    }

    public void okokbtn(View v){//o버튼
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(selectname.equals(result) ){
            //정답
            nowagree++;
        }
        Intent myIntent = new Intent(this, QuizresultActivity.class);
        myIntent.putExtra("acount", nowagree);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+nowagree,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

    public void nobtn(View v){//x버튼
        //Toast.makeText(getApplicationContext(),"check"+usercheck,Toast.LENGTH_SHORT).show();
        if(!selectname.equals(result) ){
            //정답
            nowagree++;
        }
        Intent myIntent = new Intent(this, QuizresultActivity.class);
        myIntent.putExtra("acount", nowagree);
        myIntent.putExtra("mchch", check);
        //Toast.makeText(getApplicationContext(),"gogo"+nowagree,Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }






}