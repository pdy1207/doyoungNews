package com.example.dynews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynews.fragment.MyAdapter;
import com.example.dynews.layoutall.layout;
import com.example.dynews.layoutall.layout2;
import com.example.dynews.layoutall.layout3;
import com.example.dynews.layoutall.layout4;
import com.example.dynews.layoutall.layout5;
import com.example.dynews.layoutall.layout6;
import com.example.dynews.layoutall.layout7;
import com.example.dynews.layoutall.layout8;
import com.example.dynews.layoutall.layout9;
import com.example.dynews.sql.MySQLiteOpenHelper;
import com.example.dynews.sql.SQLiteExEdit;
import com.example.dynews.sql.SQLiteExLogin;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;


    // DB
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    TextView NickName;
    String name;

    private View drawerView;
    private DrawerLayout drawerLayout;



    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);

    Animation aniTouch, aniLlMate;
    ImageButton btnHome, btnAccount, btnSearch, btnSetting, btnSidebar;
    //뉴스 기사 넘기기 버튼
    Button btnHomeTitle, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;
    ScrollView ScrollView;
    LinearLayout news1, news2, news3, news4, news5, news6, news7, news8, news9, news10;
    TextView Home, Popular, Edit, Search,Help,tra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

/*//        데이베이스 생성.
        helper = new MySQLiteOpenHelper(
                MainActivity.this, // 현재 화면의 context
                "login2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호*/
        Intent getData = getIntent();
        name = getData.getStringExtra("name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnSidebar).setOnClickListener(mClick);
        btnSidebar = (ImageButton) findViewById(R.id.btnSidebar);
        drawerLayout = (DrawerLayout) findViewById(R.id.sidemenu_layout);

        drawerLayout.setDrawerListener(drawerListener);
        drawerView = (View) findViewById(R.id.drawer);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }

        });
        tra = (TextView) findViewById(R.id.tra);
        tra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent act2 = new Intent(MainActivity.this, Papago.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션*/
                NickName = (TextView) findViewById(R.id.NickName);
                if (name == null) { //로그인 x
                    dialogProfile2();
                } else { //로그인을 하였을때
                    Intent act3 = new Intent(MainActivity.this, Papago.class);
                    startActivity(act3);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();

                }
            }
            private void dialogProfile2() { //로그인 안했을때는 로그인해달라는 다이얼로그 만들기
                Dialog dialog = new Dialog(MainActivity.this, R.style.DialogStyle);
                dialog.setContentView(R.layout.loginplss);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
                dialog.setCancelable(false);

                // 닫기 버튼
                Button btn = dialog.findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                dialog.show();

            };
        });
        Help = (TextView) findViewById(R.id.Help);
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProfile2();
            }

            private void dialogProfile2() {
                Dialog dialog = new Dialog(MainActivity.this, R.style.DialogStyle);
                dialog.setContentView(R.layout.thank);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
                dialog.setCancelable(false);

                // 닫기 버튼
                ImageView btnClose = dialog.findViewById(R.id.btn_close);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            };
        });
        Edit = (TextView) findViewById(R.id.Edit);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity.this, SQLiteExEdit.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        Home = (TextView) findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        Popular = (TextView) findViewById(R.id.Popular);
        Popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity.this, News_List.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        Search = (TextView) findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });

        news1 = (LinearLayout) findViewById(R.id.news1);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(300);
        news1.startAnimation(aniLlMate);

        news2 = (LinearLayout) findViewById(R.id.news2);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(600);
        news2.startAnimation(aniLlMate);

        news3 = (LinearLayout) findViewById(R.id.news3);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(900);
        news3.startAnimation(aniLlMate);

        news4 = (LinearLayout) findViewById(R.id.news4);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(1200);
        news4.startAnimation(aniLlMate);

        news5 = (LinearLayout) findViewById(R.id.news5);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(1500);
        news5.startAnimation(aniLlMate);

        news6 = (LinearLayout) findViewById(R.id.news6);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(1800);
        news6.startAnimation(aniLlMate);

        news7 = (LinearLayout) findViewById(R.id.news7);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(2100);
        news7.startAnimation(aniLlMate);

        news8 = (LinearLayout) findViewById(R.id.news8);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(2400);
        news8.startAnimation(aniLlMate);

        news9 = (LinearLayout) findViewById(R.id.news9);
        aniLlMate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniLlMate.setStartOffset(2800);
        news9.startAnimation(aniLlMate);
//터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
//스크롤뷰 상단
        findViewById(R.id.ScrollView).setOnClickListener(mClick);
        ScrollView = (ScrollView) findViewById(R.id.ScrollView);
//하단버튼  & 페이지넘어가기 필수들
        findViewById(R.id.btnAccount).setOnClickListener(mClick);
        findViewById(R.id.btnHome).setOnClickListener(mClick);
        findViewById(R.id.btnHomeTitle).setOnClickListener(mClick);
        findViewById(R.id.btnSearch).setOnClickListener(mClick);
        findViewById(R.id.btnSetting).setOnClickListener(mClick);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnHomeTitle = (Button) findViewById(R.id.btnHomeTitle);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);
//뉴스 넘어가기
        findViewById(R.id.textView2).setOnClickListener(mClick);
        textView2 = (Button) findViewById(R.id.textView2);

        findViewById(R.id.textView3).setOnClickListener(mClick);
        textView3 = (Button) findViewById(R.id.textView3);

        findViewById(R.id.textView4).setOnClickListener(mClick);
        textView4 = (Button) findViewById(R.id.textView4);

        findViewById(R.id.textView5).setOnClickListener(mClick);
        textView5 = (Button) findViewById(R.id.textView5);

        findViewById(R.id.textView6).setOnClickListener(mClick);
        textView6 = (Button) findViewById(R.id.textView6);

        findViewById(R.id.textView7).setOnClickListener(mClick);
        textView7 = (Button) findViewById(R.id.textView7);

        findViewById(R.id.textView8).setOnClickListener(mClick);
        textView8 = (Button) findViewById(R.id.textView8);

        findViewById(R.id.textView9).setOnClickListener(mClick);
        textView9 = (Button) findViewById(R.id.textView9);

        findViewById(R.id.textView10).setOnClickListener(mClick);
        textView10 = (Button) findViewById(R.id.textView10);
//ViewPager2
        mPager = findViewById(R.id.viewpager);

        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        /**
         * 이 부분 조정하여 처음 시작하는 이미지 설정.
         * 2000장 생성하였으니 현재위치 1002로 설정하여
         * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
         */

        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);

                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % num_page);

            }

        });
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnHomeTitle:
                    btnHomeTitle.startAnimation(aniTouch);
                    ScrollView.post(new Runnable() {
                        public void run() {
//                            ScrollView.fullScroll(ScrollView.FOCUS_DOWN); 최 하단
                            ScrollView.fullScroll(ScrollView.FOCUS_UP);
                        }
                    });
                    break;
                case R.id.btnHome:
                    Intent act2 = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(act2);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    btnHome.startAnimation(aniTouch);
                    break;
                case R.id.btnAccount:
                    Intent act3 = new Intent(MainActivity.this, News_List.class);
                    startActivity(act3);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    btnAccount.startAnimation(aniTouch);
                    Toast.makeText(MainActivity.this, "인기 뉴스 입니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnSearch:
                    Intent act4 = new Intent(MainActivity.this, NewsActivity.class);
                    startActivity(act4);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    btnSearch.startAnimation(aniTouch);
                    break;
                case R.id.btnSetting:
                    dialogProfile();
                    btnSetting.startAnimation(aniTouch);
                    break;
                case R.id.btnSidebar:
                    drawerLayout.openDrawer(drawerView);
                    break;
                //뉴스에 관한 버튼누르면 넘어가기
                case R.id.textView2:
                    Intent act5 = new Intent(MainActivity.this, layout.class);
                    startActivity(act5);
                    break;
                case R.id.textView3:
                    Intent act6 = new Intent(MainActivity.this, layout2.class);
                    startActivity(act6);
                    break;
                case R.id.textView4:
                    Intent act7 = new Intent(MainActivity.this, layout3.class);
                    startActivity(act7);
                    break;
                case R.id.textView5:
                    Intent act8 = new Intent(MainActivity.this, layout4.class);
                    startActivity(act8);
                    break;
                case R.id.textView6:
                    Intent act9 = new Intent(MainActivity.this, layout5.class);
                    startActivity(act9);
                    break;
                case R.id.textView7:
                    Intent act10 = new Intent(MainActivity.this, layout6.class);
                    startActivity(act10);
                    break;
                case R.id.textView8:
                    Intent act11 = new Intent(MainActivity.this, layout7.class);
                    startActivity(act11);
                    break;
                case R.id.textView9:
                    Intent act12 = new Intent(MainActivity.this, layout8.class);
                    startActivity(act12);
                    break;
                case R.id.textView10:
                    Intent act13 = new Intent(MainActivity.this, layout9.class);
                    startActivity(act13);
                    break;

            }
        }

        //개발자 다이얼로그!
        public void dialogProfile() {
            Dialog dialog = new Dialog(MainActivity.this, R.style.DialogStyle);
            dialog.setContentView(R.layout.profile_login);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
            dialog.setCancelable(false);

            // 닫기 버튼
            ImageView btnClose = dialog.findViewById(R.id.btn_close);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            // "네" 버튼
            Button btnYes = dialog.findViewById(R.id.btn_yes);
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intentProfile = new Intent(MainActivity.this, SQLiteExLogin.class);
                    startActivity(intentProfile);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션

                }
            });

            // "아니오" 버튼
            Button btnNo = dialog.findViewById(R.id.btn_no);
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    };
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            NickName = (TextView) findViewById(R.id.NickName);
            if (name == null) {
                NickName.setText("뉴스와 나를 잇다");
            } else {
                NickName.setText(name+"님");
            }

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
    // 뒤로가기 버튼 설정
    public void onBackPressed() {
        backHandler.onBackPressed();
    }

    /*public void selectAll() {

        // 1) db의 데이터를 읽어와서, 2) 결과 저장, 3)해당 데이터를 꺼내 사용
        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM login", null);

        String Result = "";
        String Nicname = "";
        while (c.moveToNext()) {
            int idx = c.getInt(0);
            String id = c.getString(1);
            String pw = c.getString(2);
            String name = c.getString(3);
            String hp = c.getString(4);
            String addr = c.getString(5);

            Result += "   "+id+" | "+name+" | "+hp+" | "+addr+"\n";
            Nicname += name;
        }
        NickName.setText(Nicname+"님");
        c.close();
        db.close();
    }*/

}