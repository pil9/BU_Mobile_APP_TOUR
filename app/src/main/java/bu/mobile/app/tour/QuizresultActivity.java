package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class QuizresultActivity extends AppCompatActivity {

    ImageView resultimg;
    int nowagree;
    int[] check;

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

    }
}