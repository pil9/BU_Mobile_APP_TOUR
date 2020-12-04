package bu.mobile.app.tour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.Random;

import static bu.mobile.app.tour.LoginActivity.useridx;

public class MypageActivity extends Fragment {
    private static final String TAG = "MypageActivity";
    bu.mobile.app.tour.LoginActivity.myDBHelper myHelper;
    SQLiteDatabase sqlDB2;//쿼리문 수행용

    TextView totalSam;
    TextView userName;

    TextView concoco;
    TextView concoco2;
    ListView listView;
    myAdapters adapter;
    //static String[] fruits = {"각원사 스탬프", "아라리오갤러리 스탬프","독립기념관 스탬프","유관순 사열지 스탬프", "백석대 스탬프"};
    //static String[] price = {"가맹점 20% 할인", "티켓 10% 할인","500원 기프트콘","전통시장 51% 할인", "적립 +10%"};
    static LinkedList<String> fruits = new LinkedList();
    static LinkedList<String> price = new LinkedList();
    static LinkedList<String> iimg = new LinkedList();

    private ArrayAdapter myAdapters;
    private Activity activity;

    @Override
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(fruits.size() <= 0){
            fruits.add("각원사 스탬프");
            fruits.add("아라리오갤러리 스탬프");
            fruits.add("독립기념관 스탬프");
            fruits.add("유관순 사열지 스탬프");
            fruits.add("백석대 스탬프");

            price.add("가맹점 20% 할인");
            price.add("티켓 10% 할인");
            price.add("500원 기프트콘");
            price.add("전통시장 5% 할인");
            price.add("적립 +10%");

            iimg.add("stamp1");
            iimg.add("stamp2");
            iimg.add("stamp3");
            iimg.add("stamp4");
            iimg.add("stamp5");
        }


        View v = inflater.inflate(R.layout.activity_mypage, container, false);
        listView = (ListView) v.findViewById(R.id.conlist);
        adapter = new myAdapters();
        adapter.notifyDataSetChanged();
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
        //Toast.makeText(getActivity(),"로그확인:"+cursor2.getString(1),Toast.LENGTH_LONG).show();
        id = cursor2.getString(1);
        userName.setText(id+"님의 마이페이지");
        //Toast.makeText(getApplicationContext(),"로그확인.one: "+strNames+" two:"+strNumbers+" tree: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor2.close();
        sqlDB2.close();
        Log.d(TAG,"카운트"+fruits.size());
        concoco = (TextView) v.findViewById(R.id.concount);
        concoco.setText(Integer.toString(fruits.size()));
        concoco2 = (TextView) v.findViewById(R.id.concount2);
        concoco2.setText(Integer.toString(fruits.size()));


        return v;
    }




    class myAdapters extends BaseAdapter{
        @Override
        public int getCount() {
            return fruits.size();
        }

        @Override
        public Object getItem(int position) {
            return fruits.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            conView view = new conView(activity.getApplicationContext());

            //TextView view = new TextView(getApplicationContext());
            //view.setText(fruits[position]);
            view.setFruit(fruits.get(position));
            view.setPrice(price.get(position));
            /*Random random = new Random();
            int type = random.nextInt(5);
            type++;
            if(type ==)
            */
            view.setiimg(iimg.get(position));
            //view.setTextSize(50.0f);
            //view.setTextColor(Color.BLUE);
            return view;
        }
    }

}