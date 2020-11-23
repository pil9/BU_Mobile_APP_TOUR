package bu.mobile.app.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    static int useridx;
    private final String tableName = "member";

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;//쿼리문 수행용
    //SQLiteDatabase sampleDB = null;

    EditText Id;
    EditText Pass;

    Button btnInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnInit=(Button)findViewById(R.id.btnInit);

        Id = (EditText) findViewById(R.id.idText);
        Pass = (EditText) findViewById(R.id.editTextTextPassword);

        //Id = idText.getText().toString();
        //Pass = editTextTextPassword.getText().toString();

        myHelper=new myDBHelper(this);
        //sqlDB=myHelper.getReadableDatabase();


    }

    private static final String TAG = "LoginActivity";//Log사용을 위해서 로그 태그 설정
    public void gomypage(View v){
        sqlDB=myHelper.getReadableDatabase();//읽기모드로 디비 오픈
        Cursor cursor;
        cursor=sqlDB.rawQuery("SELECT * FROM "+tableName+" WHERE id='"+Id.getText().toString()+"' and pw='"+Pass.getText().toString()+"'  ;",null);//select문 실행
        //입력한 아이디와 비밀번호를 select문으로 조회하여서 회원가입되어 있는지 확인
        Log.d(TAG,"회원검색 카운트 "+cursor.getCount());
        if(cursor.getCount() == 0){//getCount가 0이면 일치하는 정보없음 로그인 실패
            Log.d(TAG,"아이디 없다 ");
            cursor.close();
            sqlDB.close();
            Toast.makeText(getApplicationContext(),"등록되지 않음 정보입니다 .",Toast.LENGTH_LONG).show();
        }
        else{//getCount가 0이 아니면 일치하는 정보있음 로그인 성공
            cursor.moveToFirst();
            useridx = cursor.getInt(0);
            Log.d(TAG,"로그인 유저 인덱스값: "+useridx);
            cursor.close();
            sqlDB.close();
            Toast.makeText(getApplicationContext(),"로그인 성공 .",Toast.LENGTH_LONG).show();

            /*마이페이지로 화면이동*/
            Intent i1;
            i1 = new Intent(this, MypageActivity.class);
            startActivity(i1);

        }

    }

    public void gojoin(View v){
        Intent i1;
        i1 = new Intent(this, JoinActivity.class);
        startActivity(i1);
    }

    public void viewmemberlog(View v){
        sqlDB=myHelper.getReadableDatabase();
        Cursor cursor;
        cursor=sqlDB.rawQuery("SELECT * FROM member;",null);//select문 실행
        String strNames="idx"+"\r\n"+"-----------------------"+"\r\n";
        String strNumbers="id"+"\r\n"+"------------------------"+"\r\n";
        String strNumbers2="pw"+"\r\n"+"------------------------"+"\r\n";
        while (cursor.moveToNext()){//다음 레코드가 있을동안 수행
            strNames+=cursor.getString(0)+"\r\n";
            strNumbers+=cursor.getString(1)+"\r\n";
            strNumbers2+=cursor.getString(2)+"\r\n";

        }
        Toast.makeText(getApplicationContext(),"로그확인.idx: "+strNames+" id:"+strNumbers+" pw: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor.close();
        sqlDB.close();
    }
    public void viewnote(View v){
        sqlDB=myHelper.getReadableDatabase();
        Cursor cursor;
        cursor=sqlDB.rawQuery("SELECT * FROM note;",null);//select문 실행
        String strNames="title"+"\r\n"+"-----------------------"+"\r\n";
        String strNumbers="context"+"\r\n"+"------------------------"+"\r\n";
        String strNumbers2="img"+"\r\n"+"------------------------"+"\r\n";
        while (cursor.moveToNext()){//다음 레코드가 있을동안 수행
            strNames+=cursor.getString(0)+"\r\n";
            strNumbers+=cursor.getString(1)+"\r\n";
            strNumbers2+=cursor.getString(2)+"\r\n";

        }
        Toast.makeText(getApplicationContext(),"로그확인.idx: "+strNames+" id:"+strNumbers+" pw: "+strNumbers2+"",Toast.LENGTH_LONG).show();
        cursor.close();
        sqlDB.close();
    }