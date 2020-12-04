package bu.mobile.app.tour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import android.util.Log;

import com.google.android.material.navigation.NavigationView;
import static bu.mobile.app.tour.LoginActivity.useridx;

public class MypageActivity extends Fragment {
    private static final String TAG = "MypageActivity";
    bu.mobile.app.tour.LoginActivity.myDBHelper myHelper;
    SQLiteDatabase sqlDB2;//쿼리문 수행용

    TextView totalSam;
    TextView userName;

    ListView listView;
    myAdapters adapter;
    String[] fruits = {"각원사 스탬프", "아라리오갤러리 스탬프","독립기념관 스탬프","유관순 사열지 스탬프", "보탑사 스탬프"};
    String[] price = {"가맹점 20% 할인", "티켓 10% 할인","500원 기프트콘","전통시장 5% 할인", "적립 +10%"};


    @Override
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mypage, container, false);
        listView = (ListView) v.findViewById(R.id.conlist);
        adapter = new myAdapters();
        listView.setAdapter(adapter);

        userName = (TextView) v.findViewById(R.id.username);

        myHelper=new bu.mobile.app.tour.LoginActivity.myDBHelper(getActivity());

        //useridx = 1;//로그인 페이지와 연동 후 확인 필요 임시값 입력함
        sqlDB2=myHelper.getReadableDatabase();
        Cursor cursor2;
        cursor2=sqlDB2.rawQuery("SELECT * FROM member WHERE midx='"+useridx+"' ;",null);//select문 실행
        cursor2.moveToFirst();
        String id;
        Log.d(TAG,"회원확인 : "+cursor2.getString(1));
        Toast.makeText(getActivity(),"로그확인:"+cursor2.getString(1),Toast.LENGTH_LONG).show();
        id = cursor2.getString(1);
        userName.setText(id+"님의 마이페이지");
        //Toast.makeText(getApplicationContext(),"로그확인.one: "+strNames+" two:"+strNumbers+" tree: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor2.close();
        sqlDB2.close();


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