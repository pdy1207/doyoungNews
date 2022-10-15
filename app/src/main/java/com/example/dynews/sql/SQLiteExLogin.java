package com.example.dynews.sql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynews.BackHandler;
import com.example.dynews.MainActivity;
import com.example.dynews.R;
import com.google.android.material.textfield.TextInputLayout;

public class SQLiteExLogin extends AppCompatActivity {
    TextInputLayout ti_1;
    LinearLayout llTitle,llLogin;
    Animation ani1,ani2,ani3;

    EditText etId, etPw;
    //SQLITE 데이타베이스 관련변수
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    String name;

    Button btnLogin,btnJoin;
    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sqlite_login);
        ti_1 = (TextInputLayout) findViewById(R.id.ti_1);
        ti_1.setCounterEnabled(true);
        ti_1.setCounterMaxLength(100);

        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        llLogin = (LinearLayout) findViewById(R.id.llLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        ani1 = AnimationUtils.loadAnimation(SQLiteExLogin.this, R.anim.ascend_fast);
        ani2 = AnimationUtils.loadAnimation(SQLiteExLogin.this, R.anim.fadein);
        ani3 = AnimationUtils.loadAnimation(SQLiteExLogin.this, R.anim.descend_fast);
        ani1.setStartOffset(600);
        ani2.setStartOffset(1200);
        ani3.setStartOffset(1800);
        llTitle.startAnimation(ani1);
        llLogin.startAnimation(ani2);
        btnLogin.startAnimation(ani3);
        btnJoin.startAnimation(ani3);



        //데이베이스 생성.
        helper = new MySQLiteOpenHelper(
                SQLiteExLogin.this, // 현재 화면의 context
                "login2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);
        // 이전 페이지에서 데이터 받기
        Intent getData = getIntent();
        name = getData.getStringExtra("name");
        findViewById(R.id.btnLogin).setOnClickListener(mClick);
        findViewById(R.id.btnJoin).setOnClickListener(mClick);

        etId.addTextChangedListener(idTextWatcher);
    }
//이메일 형식으로 입력받기ㅁㄴ
    TextWatcher idTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                etId.setError("이메일 형식으로 입력해주세요.");    // 경고 메세지
            }
            else{
                etId.setError(null);
            }
        }
    };
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {

                case R.id.btnLogin:
                    String id = etId.getText().toString();//화면에 입력한 아이디 가져오기
                    String pw = etPw.getText().toString();//화면에 입력한 패스워드 가져오기
                    boolean loginCheck = selectAll(id,pw);//입력한 아이디패스워드로 디비체크

                    //true가 저장되었다면 로그인 성공
                    if(loginCheck ==true)
                    {

                        Intent login = new Intent(SQLiteExLogin.this,  MainActivity.class);
                        login.putExtra("name",name);
                        Toast.makeText(SQLiteExLogin.this, name+"환영합니다!",  Toast.LENGTH_SHORT).show();
                        //이부분 바꿔서 결과값 보기
                        startActivity(login);
                    }
                    else
                    {
                        Toast.makeText(SQLiteExLogin.this, "로그인 실패!(아이디/패스워드확인후다시하세요)",  Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.btnJoin:
                    Intent login = new Intent(SQLiteExLogin.this, SQLiteExJoin.class);
                    startActivity(login);
                    break;
            }


        }
    };
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        backHandler.onBackPressed();
    }
    public boolean selectAll(String loginId, String loginPw) {

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM login", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String id = c.getString(1);
            String pw = c.getString(2);
            String name = c.getString(3);
            String hp = c.getString(4);
            String addr = c.getString(5);

            if(loginId.equals(id))
            {
                if(loginPw.equals(pw))
                {
                    //아이디 패스워드를 최종적으로 잘 맞을경우 다 닫고 true값 리턴
                    c.close();
                    db.close();

                    return true;

                }
            }


        }
        c.close();
        db.close();

        return false;
    }
}