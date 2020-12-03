package bu.mobile.app.tour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

import static bu.mobile.app.tour.LoginActivity.useridx;

public class MypageActivity extends AppCompatActivity {

    LoginActivity.myDBHelper myHelper;
    SQLiteDatabase sqlDB2;

    TextView totalSam;
    TextView userName;
    ListView listView;
    myAdapters adapter;
   // String[] fruits = {"자몽", "포도","사과","배"};
    ArrayList<String> fruits = new ArrayList<>();
    //String[] price = {"200", "300","400","500"};
    ArrayList<String> price = new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        totalSam = (TextView) findViewById(R.id.totalsam);
        userName = (TextView) findViewById(R.id.username);
        listView = (ListView) findViewById(R.id.conlist);

        adapter = new myAdapters();
        listView.setAdapter(adapter);

        myHelper=new LoginActivity.myDBHelper(this);


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
                    //Toast.makeText(context, title + ": 퀴즈 이벤트를 확인합니다.", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(getApplicationContext(), QuizmainActivity.class);
                    startActivity(main);
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


        int num = 0 ;
        sqlDB2=myHelper.getReadableDatabase();
        Cursor cursor;
        cursor=sqlDB2.rawQuery("SELECT * FROM member;",null);//select문 실행
        String strNames="title"+"\r\n"+"-----------------------"+"\r\n";
        String strNumbers="context"+"\r\n"+"------------------------"+"\r\n";
        String strNumbers2="img"+"\r\n"+"------------------------"+"\r\n";
        while (cursor.moveToNext()){//다음 레코드가 있을동안 수행

            strNames+=cursor.getString(0)+"\r\n";
            strNumbers+=cursor.getString(1)+"\r\n";
            strNumbers2+=cursor.getString(2)+"\r\n";
            fruits.add(cursor.getString(0));
            price.add(cursor.getString(0));
            //fruits[num] = cursor.getString(0);
            //price[num] = cursor.getString(1);
            num++;

        }
        totalSam.setText("보유 스탬프 : "+num);
        Toast.makeText(getApplicationContext(),"로그확인.one: "+strNames+" two:"+strNumbers+" tree: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor.close();
        sqlDB2.close();

        useridx = 1;//로그인 페이지와 연동 후 확인 필요 임시값 입력함
        sqlDB2=myHelper.getReadableDatabase();
        Cursor cursor2;
        cursor2=sqlDB2.rawQuery("SELECT * FROM member WHERE midx='"+useridx+"' ;",null);//select문 실행
        cursor2.moveToFirst();
        String id;
        id = cursor2.getString(1);
        userName.setText("ID : "+id);
        //Toast.makeText(getApplicationContext(),"로그확인.one: "+strNames+" two:"+strNumbers+" tree: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor2.close();
        sqlDB2.close();










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
            conView view = new conView(getApplicationContext());

            //TextView view = new TextView(getApplicationContext());
            //view.setText(fruits[position]);
            view.setFruit(fruits.get(position));
            view.setPrice(price.get(position));
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