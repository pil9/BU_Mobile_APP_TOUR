package bu.mobile.app.tour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.material.navigation.NavigationView;

public class MypageActivity extends Fragment {

    ListView listView;
    myAdapters adapter;
    String[] fruits = {"자몽", "포도","사과","배"};
    String[] price = {"200", "300","400","500"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mypage, container, false);
        listView = (ListView) v.findViewById(R.id.conlist);
        adapter = new myAdapters();
        listView.setAdapter(adapter);
        return v;
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
            conView view = new conView(getActivity().getApplicationContext());

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