package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class QuizmainActivity extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quizmain);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_quizmain, container, false);
        ImageView button = (ImageView) v.findViewById(R.id.qzstart_btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i1;
                i1 = new Intent(getActivity(), QuizfirstActivity.class);
                startActivity(i1);
                // do something
            }
        });

        return v;
    }

   /* public void qzstart(View v){
        //Toast.makeText(getApplicationContext(),"gogo",Toast.LENGTH_SHORT).show();
        Intent i1;
        i1 = new Intent(getActivity(), QuizfirstActivity.class);
        startActivity(i1);
    }*/
}