package com.example.dynews.sql;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.example.dynews.MainActivity;
import com.example.dynews.R;

public class SQLiteExEdit extends AppCompatActivity {
    //회원수정
    Button btnEditFind, btnEdit;
    EditText etId,  etPw, etPw2, etName,etNumber;

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    String editId, pw, pw2,name,number;

    //애니메이션
    LinearLayout llTitle;
    TextView tvId,tvPw,tvPw2,tvEhddml,tvName,tvPho;


    Animation aniLlTitle;
    Animation ani1,ani2,ani3,ani4,ani5,ani6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sqlite_main);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelper(
                SQLiteExEdit.this, // 현재 화면의 context
                "login2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        //회원수정
        btnEditFind = (Button)findViewById(R.id.btnEditFind);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);
        /*etPw2 = (EditText)findViewById(R.id.etPw2);*/
        etName = (EditText)findViewById(R.id.etName);
        etNumber = (EditText)findViewById(R.id.etNumber);


        findViewById(R.id.btnEdit).setOnClickListener(mClick);
        findViewById(R.id.btnEditFind).setOnClickListener(mClick);

        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        aniLlTitle = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.ascend);
        llTitle.startAnimation(aniLlTitle);
        tvId = (TextView) findViewById(R.id.tvId);
        tvPw = (TextView) findViewById(R.id.tvPw);
        tvPw2 = (TextView) findViewById(R.id.tvPw2);
        tvEhddml = (TextView) findViewById(R.id.tvEhddml);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPho = (TextView) findViewById(R.id.tvPho);
        ani1 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani2 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani3 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani4 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani5 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani6 = AnimationUtils.loadAnimation(SQLiteExEdit.this, R.anim.descend_fast);
        ani1.setStartOffset(1300);
        ani2.setStartOffset(1600);
        ani3.setStartOffset(1900);
        ani4.setStartOffset(2200);
        ani5.setStartOffset(2500);
        ani6.setStartOffset(2800);
        tvId.startAnimation(ani1);
        tvName.startAnimation(ani2);
        tvPw.startAnimation(ani3);
 /*       tvPw2.startAnimation(ani4);*/
        tvPho.startAnimation(ani5);

    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {

                case R.id.btnEdit:
                    if(etId.getText().toString().equals(""))  {
                        Toast.makeText(getApplicationContext(), "수정할 아이디를 입력해주세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String editId = etId.getText().toString();

                   /* if(etPw.getText().toString().equals(""))  {
                        Toast.makeText(getApplicationContext(), "수정할 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    String pw = etPw.getText().toString();

                   /* if(etPw2.getText().toString().equals(""))  {
                        Toast.makeText(getApplicationContext(), "수정할 연락처를 입력해주세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    /*String pw2 = etPw2.getText().toString();
                    if(etName.getText().toString().equals(""))  {
                        Toast.makeText(getApplicationContext(), "수정할 이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    String name = etName.getText().toString();


                    if(etNumber.getText().toString().equals(""))  {
                        Toast.makeText(getApplicationContext(), "수정할 주소를 입력해주세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String number = etNumber.getText().toString();
                    update(editId,pw,name,number);

                    Intent intentJoinOk = new Intent(SQLiteExEdit.this, MainActivity.class);
                    intentJoinOk.putExtra("id", editId);
                    intentJoinOk.putExtra("pw", pw);
                  /*  intentJoinOk.putExtra("pw2", pw2);*/
                    intentJoinOk.putExtra("name", name);
                    intentJoinOk.putExtra("number", number);
                    startActivity(intentJoinOk);
      /*              Toast.makeText(getApplicationContext(), name+"님 환영 합니다!", Toast.LENGTH_SHORT).show();*/


                case R.id.btnEditFind:
                   if(etId.getText().toString().equals(""))
                    {
                        /*Toast.makeText(getApplicationContext(), "수정할 아이디를 입력해주세요!", Toast.LENGTH_SHORT).show();*/
                        return;
                    }
                    String findId = etId.getText().toString();
                    selectEdit(findId);

                break;

            }

        }
    };

    ////////
    //SQlite 메서드 처리 구간

    // update
    public void update(String editId,String pw,String name, String number) {
        db = helper.getWritableDatabase(); //db 객체를 얻어온다. 쓰기가능

        ContentValues values = new ContentValues();
        values.put("pw", pw);
        values.put("pw2", pw2);
        values.put("name", name);
        values.put("number", number);
        db.update("login", values, "id='"+editId+"'", null);

        db.close();
        Toast.makeText(getApplicationContext(), "정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
        //수정완료후 초기화
        etId.setText("");
        etPw.setText("");
  /*      etPw2.setText("");*/
        etName.setText("");
        etNumber.setText("");
    }

    // 수정 아이디 찾기
    public void selectEdit(String editId) {

        // 1) db의 데이터를 읽어와서, 2) 결과 저장, 3)해당 데이터를 꺼내 사용
        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM login where id='"+editId+"'", null);

        String Result = "";
        boolean check=false;
        while (c.moveToNext()) {
            int idx = c.getInt(0);
            String id = c.getString(1);
            String pw = c.getString(2);
            String pw2 = c.getString(3);
            String name = c.getString(4);
            String number = c.getString(5);

         /*   etPw.setText(pw);
            etPw2.setText(pw2);*/
            etName.setText(name);
            etNumber.setText(number);

            check=true;
        }
        if(check==false)
        {
            etId.setText("");
            etPw.setText("");
            /*etPw2.setText("");*/
            etName.setText("");
            etNumber.setText("");
            Toast.makeText(getApplicationContext(), "찾는 대상이없습니다!", Toast.LENGTH_SHORT).show();
        }
        c.close();
        db.close();
    }
    public void dialogProfile() {
        Dialog dialog = new Dialog(SQLiteExEdit.this, R.style.DialogStyle);
        dialog.setContentView(R.layout.editok_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
        dialog.setCancelable(false);

        // "x" 버튼
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });


        // "확인" 버튼
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }
}