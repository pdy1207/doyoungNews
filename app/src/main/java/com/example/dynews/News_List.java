package com.example.dynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynews.layoutall.layout_1;
import com.example.dynews.layoutall.layout_10;
import com.example.dynews.layoutall.layout_2;
import com.example.dynews.layoutall.layout_3;
import com.example.dynews.layoutall.layout_4;
import com.example.dynews.layoutall.layout_5;
import com.example.dynews.layoutall.layout_6;
import com.example.dynews.layoutall.layout_7;
import com.example.dynews.layoutall.layout_8;
import com.example.dynews.layoutall.layout_9;

public class News_List extends AppCompatActivity {
    ImageButton btnHome,btnAccount,btnSearch,btnSidebar;
    //뉴스 기사 버튼
    Button btnHomeTitle;
    Animation aniTouch;
    ScrollView ScrollView;
    Animation aniLlMate,aniLlMate2,aniLlMate3,aniLlMate4,aniLlMate5
            ,aniLlMate6,aniLlMate7,aniLlMate8,aniLlMate9,aniLlMate10; //레이아웃 애니메이션 정의
    Animation anilCard,anilCard2,anilCard3,anilCard4,anilCard5,
            anilCard6,anilCard7,anilCard8,anilCard9,anilCard10; //카드뷰 애니메이션 정의
    Animation aniLlText,aniLlText2,aniLlText3,aniLlText4,aniLlText5,
            aniLlText6,aniLlText7,aniLlText8,aniLlText9,aniLlText10; //텍스트뷰 애니메이션 정의
    LinearLayout llMate,llMate2,llMate3,llMate4,llMate5,
            llMate6,llMate7,llMate8,llMate9,llMate10;
    TextView TextView_title,TextView_title2,TextView_title3,TextView_title4,TextView_title5,
            TextView_title6,TextView_title7,TextView_title8,TextView_title9,TextView_title10;
    CardView card_view,card_view2,card_view3,card_view4,card_view5,
            card_view6,card_view7,card_view8,card_view9,card_view10;
    ImageButton ImageView_title,ImageView_title2,ImageView_title3,ImageView_title4,ImageView_title5,
            ImageView_title6,ImageView_title7,ImageView_title8,ImageView_title9 ,ImageView_title10;
    private View drawerView;
    private DrawerLayout drawerLayout;
    TextView Home,Popular,Edit,Search;
    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        //사이드바 설정 하기
        findViewById(R.id.btnSidebar).setOnClickListener(mClick2);
        btnSidebar = (ImageButton) findViewById(R.id.btnSidebar);
        drawerLayout = (DrawerLayout) findViewById(R.id.sidemenu_layout);
        drawerView = (View) findViewById(R.id.drawer);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        Home = (TextView) findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(News_List.this, MainActivity.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        Popular = (TextView) findViewById(R.id.Popular);
        Popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(News_List.this, News_List.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        Search = (TextView) findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(News_List.this, NewsActivity.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        //터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(News_List.this, R.anim.scale);

        //버튼
        findViewById(R.id.btnAccount).setOnClickListener(mClick);
        findViewById(R.id.btnHome).setOnClickListener(mClick);
        findViewById(R.id.btnHomeTitle).setOnClickListener(mClick);
        findViewById(R.id.btnSearch).setOnClickListener(mClick);
        findViewById(R.id.btnSidebar).setOnClickListener(mClick2);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnHomeTitle = (Button) findViewById(R.id.btnHomeTitle);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnSidebar = (ImageButton) findViewById(R.id.btnSidebar);
        //스크롤뷰
        findViewById(R.id.ScrollView).setOnClickListener(mClick);
        ScrollView = (ScrollView) findViewById(R.id.ScrollView);

        //레이아웃 정의
        llMate = (LinearLayout) findViewById(R.id.llMate);
        llMate2 = (LinearLayout) findViewById(R.id.llMate2);
        llMate3 = (LinearLayout) findViewById(R.id.llMate3);
        llMate4 = (LinearLayout) findViewById(R.id.llMate4);
        llMate5 = (LinearLayout) findViewById(R.id.llMate5);
        llMate6 = (LinearLayout) findViewById(R.id.llMate6);
        llMate7 = (LinearLayout) findViewById(R.id.llMate7);
        llMate8 = (LinearLayout) findViewById(R.id.llMate8);
        llMate9 = (LinearLayout) findViewById(R.id.llMate9);
        llMate10 = (LinearLayout) findViewById(R.id.llMate10);


        //카드뷰 정의
        findViewById(R.id.card_view).setOnClickListener(mClick);
        findViewById(R.id.card_view2).setOnClickListener(mClick);
        findViewById(R.id.card_view3).setOnClickListener(mClick);
        findViewById(R.id.card_view4).setOnClickListener(mClick);
        findViewById(R.id.card_view5).setOnClickListener(mClick);
        findViewById(R.id.card_view6).setOnClickListener(mClick);
        findViewById(R.id.card_view7).setOnClickListener(mClick);
        findViewById(R.id.card_view8).setOnClickListener(mClick);
        findViewById(R.id.card_view9).setOnClickListener(mClick);
        findViewById(R.id.card_view10).setOnClickListener(mClick);

        //카드뷰 애니메이션
        card_view = (CardView) findViewById(R.id.card_view);
        card_view2 = (CardView) findViewById(R.id.card_view2);
        card_view3 = (CardView) findViewById(R.id.card_view3);
        card_view4 = (CardView) findViewById(R.id.card_view4);
        card_view5 = (CardView) findViewById(R.id.card_view5);
        card_view6 = (CardView) findViewById(R.id.card_view6);
        card_view7 = (CardView) findViewById(R.id.card_view7);
        card_view8 = (CardView) findViewById(R.id.card_view8);
        card_view9 = (CardView) findViewById(R.id.card_view9);
        card_view10 = (CardView) findViewById(R.id.card_view10);

        //텍스트뷰 정의
        findViewById(R.id.TextView_title).setOnClickListener(mClick);
        findViewById(R.id.TextView_title2).setOnClickListener(mClick);
        findViewById(R.id.TextView_title3).setOnClickListener(mClick);
        findViewById(R.id.TextView_title4).setOnClickListener(mClick);
        findViewById(R.id.TextView_title5).setOnClickListener(mClick);
        findViewById(R.id.TextView_title6).setOnClickListener(mClick);
        findViewById(R.id.TextView_title7).setOnClickListener(mClick);
        findViewById(R.id.TextView_title8).setOnClickListener(mClick);
        findViewById(R.id.TextView_title9).setOnClickListener(mClick);
        findViewById(R.id.TextView_title10).setOnClickListener(mClick);


        //텍스트뷰 애니메이션
        TextView_title = (TextView) findViewById(R.id.TextView_title);
        TextView_title2 = (TextView) findViewById(R.id.TextView_title2);
        TextView_title3 = (TextView) findViewById(R.id.TextView_title3);
        TextView_title4 = (TextView) findViewById(R.id.TextView_title4);
        TextView_title5 = (TextView) findViewById(R.id.TextView_title5);
        TextView_title6 = (TextView) findViewById(R.id.TextView_title6);
        TextView_title7 = (TextView) findViewById(R.id.TextView_title7);
        TextView_title8 = (TextView) findViewById(R.id.TextView_title8);
        TextView_title9 = (TextView) findViewById(R.id.TextView_title9);
        TextView_title10 = (TextView) findViewById(R.id.TextView_title10);

        //뉴스넘어가기 정의
        findViewById(R.id.ImageView_title).setOnClickListener(mClick);
        ImageView_title = (ImageButton) findViewById(R.id.ImageView_title);

        findViewById(R.id.ImageView_title2).setOnClickListener(mClick);
        ImageView_title2 = (ImageButton) findViewById(R.id.ImageView_title2);

        findViewById(R.id.ImageView_title3).setOnClickListener(mClick);
        ImageView_title3 = (ImageButton) findViewById(R.id.ImageView_title3);

        findViewById(R.id.ImageView_title4).setOnClickListener(mClick);
        ImageView_title4 = (ImageButton) findViewById(R.id.ImageView_title4);

        findViewById(R.id.ImageView_title5).setOnClickListener(mClick);
        ImageView_title5 = (ImageButton) findViewById(R.id.ImageView_title5);

        findViewById(R.id.ImageView_title6).setOnClickListener(mClick);
        ImageView_title6 = (ImageButton) findViewById(R.id.ImageView_title6);

        findViewById(R.id.ImageView_title7).setOnClickListener(mClick);
        ImageView_title7 = (ImageButton) findViewById(R.id.ImageView_title7);

        findViewById(R.id.ImageView_title8).setOnClickListener(mClick);
        ImageView_title8 = (ImageButton) findViewById(R.id.ImageView_title8);

        findViewById(R.id.ImageView_title9).setOnClickListener(mClick);
        ImageView_title9 = (ImageButton) findViewById(R.id.ImageView_title9);

        findViewById(R.id.ImageView_title10).setOnClickListener(mClick);
        ImageView_title10 = (ImageButton) findViewById(R.id.ImageView_title10);


        //레이아웃 애니메이션
        aniLlMate = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate.setStartOffset(300);
        llMate.startAnimation(aniLlMate);

        aniLlMate2 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate2.setStartOffset(600);
        llMate2.startAnimation(aniLlMate2);

        aniLlMate3 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate3.setStartOffset(900);
        llMate3.startAnimation(aniLlMate3);

        aniLlMate4 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate4.setStartOffset(1200);
        llMate4.startAnimation(aniLlMate4);

        aniLlMate5 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate5.setStartOffset(1500);
        llMate5.startAnimation(aniLlMate5);

        aniLlMate6 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate6.setStartOffset(1800);
        llMate6.startAnimation(aniLlMate6);

        aniLlMate7 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate7.setStartOffset(2100);
        llMate7.startAnimation(aniLlMate7);

        aniLlMate8 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate8.setStartOffset(2400);
        llMate8.startAnimation(aniLlMate8);

        aniLlMate9 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate9.setStartOffset(2700);
        llMate9.startAnimation(aniLlMate9);

        aniLlMate10 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlMate10.setStartOffset(3000);
        llMate10.startAnimation(aniLlMate10);

        //텍스트 애니메이션
        aniLlText = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText.setStartOffset(500);
        TextView_title.startAnimation(aniLlText);

        aniLlText2 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText2.setStartOffset(1000);
        TextView_title2.startAnimation(aniLlText2);

        aniLlText3 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText3.setStartOffset(1500);
        TextView_title3.startAnimation(aniLlText3);

        aniLlText4 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText4.setStartOffset(2000);
        TextView_title4.startAnimation(aniLlText4);

        aniLlText5 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText5.setStartOffset(2500);
        TextView_title5.startAnimation(aniLlText5);

        aniLlText6 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText6.setStartOffset(3000);
        TextView_title6.startAnimation(aniLlText6);

        aniLlText7 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText7.setStartOffset(3000);
        TextView_title7.startAnimation(aniLlText7);

        aniLlText8 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText8.setStartOffset(3000);
        TextView_title8.startAnimation(aniLlText8);

        aniLlText9 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText9.setStartOffset(3000);
        TextView_title9.startAnimation(aniLlText9);

        aniLlText10 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        aniLlText10.setStartOffset(3000);
        TextView_title10.startAnimation(aniLlText10);


        //카드뷰 애니메이션
        anilCard = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard.setStartOffset(300);
        card_view.startAnimation(anilCard);

        anilCard2 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard2.setStartOffset(600);
        card_view2.startAnimation(anilCard2);

        anilCard3 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard3.setStartOffset(900);
        card_view3.startAnimation(anilCard3);

        anilCard4 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard4.setStartOffset(1200);
        card_view4.startAnimation(anilCard4);

        anilCard5 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard5.setStartOffset(1500);
        card_view5.startAnimation(anilCard5);

        anilCard6 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard6.setStartOffset(1800);
        card_view6.startAnimation(anilCard6);

        anilCard7 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard7.setStartOffset(1800);
        card_view7.startAnimation(anilCard7);

        anilCard8 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard8.setStartOffset(1800);
        card_view8.startAnimation(anilCard8);

        anilCard9 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard9.setStartOffset(1800);
        card_view9.startAnimation(anilCard9);

        anilCard10 = AnimationUtils.loadAnimation(News_List.this, R.anim.fadein);
        anilCard10.setStartOffset(1800);
        card_view10.startAnimation(anilCard10);


    }
    View.OnClickListener mClick2 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSidebar:
                drawerLayout.openDrawer(drawerView);
            }
        }
    };
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
                    Intent act2 = new Intent(News_List.this, MainActivity.class);
                    startActivity(act2);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    btnHome.startAnimation(aniTouch);
                    break;
                case R.id.btnAccount:
                    Intent act3 = new Intent(News_List.this, News_List.class);
                    startActivity(act3);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    btnAccount.startAnimation(aniTouch);
                    break;
                case R.id.btnSearch:
                    Intent act4 = new Intent(News_List.this, NewsActivity.class);
                    startActivity(act4);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    btnSearch.startAnimation(aniTouch);
                    Toast.makeText(News_List.this, "", Toast.LENGTH_SHORT).show();
                    break;
                    //뉴스 액티비티
                case R.id.ImageView_title:
                    Intent act5 = new Intent(News_List.this, layout_1.class);
                    startActivity(act5);
                    break;
                case R.id.ImageView_title2:
                    Intent act6 = new Intent(News_List.this, layout_2.class);
                    startActivity(act6);
                    break;
                case R.id.ImageView_title3:
                    Intent act7 = new Intent(News_List.this, layout_3.class);
                    startActivity(act7);
                    break;
                case R.id.ImageView_title4:
                    Intent act8 = new Intent(News_List.this, layout_4.class);
                    startActivity(act8);
                    break;
                case R.id.ImageView_title5:
                    Intent act9 = new Intent(News_List.this, layout_5.class);
                    startActivity(act9);
                    break;
                case R.id.ImageView_title6:
                    Intent act10 = new Intent(News_List.this, layout_6.class);
                    startActivity(act10);
                    break;
                case R.id.ImageView_title7:
                    Intent act11 = new Intent(News_List.this, layout_7.class);
                    startActivity(act11);
                    break;
                case R.id.ImageView_title8:
                    Intent act12 = new Intent(News_List.this, layout_8.class);
                    startActivity(act12);
                    break;
                case R.id.ImageView_title9:
                    Intent act13 = new Intent(News_List.this, layout_9.class);
                    startActivity(act13);
                    break;
                case R.id.ImageView_title10:
                    Intent act14 = new Intent(News_List.this, layout_10.class);
                    startActivity(act14);
                    break;

            }
        }

    };
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() { backHandler.onBackPressed();
    }

}