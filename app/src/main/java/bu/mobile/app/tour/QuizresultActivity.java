package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class QuizresultActivity extends AppCompatActivity {

    ImageView resultimg;
    int nowagree;
    int[] check;
    MypageActivity fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizresult);

        TextView anwcont = (TextView)findViewById(R.id.anwcont);
        resultimg = (ImageView) findViewById(R.id.resultimg);

        Intent secondIntent = getIntent();
        nowagree=secondIntent.getIntExtra("acount",0);
        check = secondIntent.getIntArrayExtra("mchch");

        anwcont.setText("3개 중 " +nowagree+"개 정답.!");
        if(nowagree == 1 || nowagree == 0){

            resultimg.setImageResource(R.drawable.lose);
        }else if(nowagree == 2){
            resultimg.setImageResource(R.drawable.soso);
        }else if(nowagree == 3){
            resultimg.setImageResource(R.drawable.win);
        }

        Log.d("배열확인: ", Arrays.toString(check));
        Log.d("답 갯: ", "value"+nowagree);

        Button button1 = (Button) findViewById(R.id.gomys) ;
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(myIntent);


            }
        });

    }

  /*  public void gohomes(View v){
        Intent myIntent = new Intent(this, MypageActivity.class);
        startActivity(myIntent);
    }*/


}