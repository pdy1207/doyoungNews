package com.example.dynews.sql;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynews.R;
import com.google.android.material.textfield.TextInputLayout;

public class SQLiteExJoin extends AppCompatActivity {

    TextInputLayout ti_1,ti_2,ti_3,ti_4;
    //애니메이션
    LinearLayout llTitle;
    TextView tvId,tvPw,tvPw2,tvEhddml,tvName,tvPho;


    Animation aniLlTitle;
    Animation ani1,ani2,ani3,ani4,ani5,ani6;



    //SQLITE 데이타베이스 관련변수
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    String id,pw,pw2,name,number; //입력값을 변수에 저장해서 insert처리할변수 , 데이터

    Button btnJoin;
    EditText etId, etPw, etPw2, etName, etNumber;


    String TAG ="회원 정보";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sqlite_join);
        ti_1 = (TextInputLayout) findViewById(R.id.ti_1);
        ti_1.setCounterEnabled(true);
        ti_1.setCounterMaxLength(100);
        ti_2 = (TextInputLayout) findViewById(R.id.ti_2);
        ti_2.setCounterEnabled(true);
        ti_2.setCounterMaxLength(100);
        ti_3 = (TextInputLayout) findViewById(R.id.ti_3);
        ti_3.setCounterEnabled(true);
        ti_3.setCounterMaxLength(100);
        ti_4 = (TextInputLayout) findViewById(R.id.ti_4);
        ti_4.setCounterEnabled(true);
        ti_4.setCounterMaxLength(11);


//애니메이션
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        aniLlTitle = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.descend);
        llTitle.startAnimation(aniLlTitle);
        tvId = (TextView) findViewById(R.id.tvId);
        tvPw = (TextView) findViewById(R.id.tvPw);
        tvPw2 = (TextView) findViewById(R.id.tvPw2);
        tvEhddml = (TextView) findViewById(R.id.tvEhddml);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPho = (TextView) findViewById(R.id.tvPho);
        etPw2 = (EditText) findViewById(R.id.etPw2);
        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);

        ani1 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani2 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani3 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani4 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani5 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani6 = AnimationUtils.loadAnimation(SQLiteExJoin.this, R.anim.ascend_fast);
        ani1.setStartOffset(1300);
        ani2.setStartOffset(1600);
        ani3.setStartOffset(1900);
        ani4.setStartOffset(2200);
        ani5.setStartOffset(2500);
        ani6.setStartOffset(2800);
        tvId.startAnimation(ani1);
        tvPw.startAnimation(ani2);
        tvPw2.startAnimation(ani3);
        tvName.startAnimation(ani4);
        tvPho.startAnimation(ani5);
        tvEhddml.startAnimation(ani6);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelper(
                SQLiteExJoin.this, // 현재 화면의 context
                "login2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        btnJoin = (Button)findViewById(R.id.btnJoin);

        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);
        etPw2 = (EditText)findViewById(R.id.etPw2);
        etName = (EditText)findViewById(R.id.etName);
        etNumber = (EditText)findViewById(R.id.etNumber);
        btnJoin.setOnClickListener(mClickListener);

        etId.addTextChangedListener(idTextWatcher);
        etPw.addTextChangedListener(idTextWatcher2);
        etPw2.addTextChangedListener(idTextWatcher2);
        etNumber.addTextChangedListener(idTextWatcher3);



    }
//아이디 이메일 제한걸기
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
 //비밀번호 체크
    TextWatcher idTextWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!etPw.getText().toString().equals(etPw2.getText().toString())) {
                etPw2.setError("비밀번호가 일치하지 않습니다.");
            } else {
                etPw2.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {} //경고
    };
 //휴대폰 번호 제한걸기
    TextWatcher idTextWatcher3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>11) {ti_4.setError("형식에 맞춰 입력해주시기 바랍니다.");}
            else {ti_4.setError(null);}

        } //경고
    };
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnJoin:
                    //공백체크
                    if(etId.getText().toString().equals(""))
                    {
                        Toast.makeText(SQLiteExJoin.this, "아이디를 입력하세요.",  Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력잘되었을경우 변수에 저장
                    id = etId.getText().toString();

                    if(etPw.getText().toString().equals(""))
                    {
                        Toast.makeText(SQLiteExJoin.this, "비번을 입력하세요.",  Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력잘되었을경우 변수에 저장
                    pw = etPw.getText().toString();

                    if(etPw2.getText().toString().equals(""))
                    {
                        Toast.makeText(SQLiteExJoin.this, "비밀번호 확인란은 필수입니다!",  Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력잘되었을경우 변수에 저장
                    pw2 = etPw2.getText().toString();

                    if(etName.getText().toString().equals(""))
                    {
                        Toast.makeText(SQLiteExJoin.this, "닉네임을 입력하세요.",  Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력잘되었을경우 변수에 저장
                    name = etName.getText().toString();

                    if(etNumber.getText().toString().equals(""))
                    {
                        Toast.makeText(SQLiteExJoin.this, "전화번호를 입력하세요.",  Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //공백없이 입력잘되었을경우 변수에 저장
                    number = etNumber.getText().toString();

                    //회원정보를 다 입력하였을경우 데이타베이스에 insert를 호출
                    insert(id, pw, pw2, name, number);
                    dialogProfile();

                    //회원가입 후 저장정보 확인하기.
                    /*Intent intentJoinOk = new Intent(Ex9SQLiteExJoin.this, Ex9SQLiteExLogin.class);
                    intentJoinOk.putExtra("id", id);
                    intentJoinOk.putExtra("pw", pw);
                    intentJoinOk.putExtra("pw2", pw2);
                    intentJoinOk.putExtra("name", name);
                    intentJoinOk.putExtra("number", number);
                    startActivity(intentJoinOk);*/

                    break;
            }
        }
    };

    //데이타베이스 메서드 처리  ////////////////////////////
    public void insert(String id, String pw, String pw2, String name, String number) {

        db = helper.getWritableDatabase(); // db 객체를 얻어온다. 쓰기 가능

        //값들을 컨트롤 하려고 클래스 생성
        ContentValues values = new ContentValues();

        // db.insert의 매개변수인 values가 ContentValues 변수이므로 그에 맞춤
        // 데이터의 삽입은 put을 이용한다.
        values.put("id", id);
        values.put("pw", pw);
        values.put("pw2", pw2);
        values.put("name", name);
        values.put("number", number);
        db.insert("login", null, values); // 테이블/널컬럼핵/데이터(널컬럼핵=디폴트)

        // tip : 마우스를 db.insert에 올려보면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
        db.close();
        Toast.makeText(getApplicationContext(), name+"님 가입완료!", Toast.LENGTH_LONG).show();

        Log.d(TAG, id+"/"+pw+"/"+pw2+"/"+name+"/"+number+" 의 정보로 디비저장완료.");
    }

    public void dialogProfile() {
        Dialog dialog = new Dialog(SQLiteExJoin.this, R.style.DialogStyle);
        dialog.setContentView(R.layout.joinok_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
        dialog.setCancelable(false);

        // "x" 버튼
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //db에 데이터 입력

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션

                finish();
            }
        });


        // "확인" 버튼
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                        insert(id,pw,pw2,name,number);
                        Intent intentProfile = new Intent(SQLiteExJoin.this, SQLiteExLogin.class);
                        intentProfile.putExtra("id", id);
                        intentProfile.putExtra("pw", pw);
                        intentProfile.putExtra("pw2", pw2);
                        intentProfile.putExtra("name", name);
                        intentProfile.putExtra("number", number);
                        startActivity(intentProfile);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        dialog.show();
    }



}