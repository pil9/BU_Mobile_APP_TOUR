package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MypageActivity extends AppCompatActivity {

    ListView listView;
    myAdapters adapter;
    String[] fruits = {"자몽", "포도","사과","배"};
    String[] price = {"200", "300","400","500"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        listView = (ListView) findViewById(R.id.conlist);

        adapter = new myAdapters();
        listView.setAdapter(adapter);

    }

    class myAdapters extends BaseAdapter{
        @Override
        public int getCount() {
            return fruits.length;
        }

        @Override
        public Object getItem(int position) {
            return fruits[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            conView view = new conView(getApplicationContext());

            //TextView view = new TextView(getApplicationContext());
            //view.setText(fruits[position]);
            view.setFruit(fruits[position]);
            view.setPrice(price[position]);
            //view.setTextSize(50.0f);
            //view.setTextColor(Color.BLUE);
            return view;
        }
    }



}