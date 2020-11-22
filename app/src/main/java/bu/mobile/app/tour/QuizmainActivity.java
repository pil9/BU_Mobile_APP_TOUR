package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class QuizmainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmain);
    }

    public void qzstart(View v){
        Toast.makeText(getApplicationContext(),"gogo",Toast.LENGTH_LONG).show();
    }
}