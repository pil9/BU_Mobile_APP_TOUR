package bu.mobile.app.tour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class MypageActivity extends AppCompatActivity {

    ListView listView;
    myAdapters adapter;
    String[] fruits = {"자몽", "포도","사과","배"};
    String[] price = {"200", "300","400","500"};

    private DrawerLayout mDrawerLayout;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        listView = (ListView) findViewById(R.id.conlist);

        adapter = new myAdapters();
        listView.setAdapter(adapter);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.menuimg_foreground); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.marckgo){
                    Toast.makeText(context, title + ": 천안 랜드마크를 확인합니다.", Toast.LENGTH_SHORT).show();
                   /* Intent main = new Intent(this, MainActivity.class);
                    startActivity(main);*/
                }
                else if(id == R.id.Tourists){
                    Toast.makeText(context, title + ": 오늘의 추천 관광지 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.quizlistgo){
                    Toast.makeText(context, title + ": 퀴즈 이벤트를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.QRgo){
                    Toast.makeText(context, title + ": QR 목록 ㄱ", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.account){
                    Toast.makeText(context, title + ": 마이페이지 목록 ㄱ", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout){
                    Toast.makeText(context, title + ": 로그아웃 시도중", Toast.LENGTH_SHORT).show();
                }


                return true;
            }
        });














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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }



}