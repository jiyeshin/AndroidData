package com.example.a503_12.androiddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomCell1010 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_cell1010);

        //출력할 데이터 생성
        List<VO1010> list = new ArrayList<>();
        VO1010 vo = new VO1010();
        vo.setIcon(R.drawable.maps);
        vo.setName("지구");
        list.add(vo);

        vo = new VO1010();
        vo.setIcon(R.drawable.skype);
        vo.setName("스카이프");
        list.add(vo);

        vo = new VO1010();
        vo.setIcon(R.drawable.twitter);
        vo.setName("트위터");
        list.add(vo);

        //데이터를 출력할 Adapter 만들기
        MyAdapter1010 adapter = new MyAdapter1010(this, list, R.layout.icontext_customcell1010);

        //ListView에 데이터 연결
        ListView listView = (ListView)findViewById(R.id.listV);
        listView.setAdapter(adapter);

        //ListView에 적용할 Animation 객체 만들기
        AnimationSet set =
                new AnimationSet(true);
        //수평 방향에서 날아오기
        Animation rtl =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF,0.0f);
        rtl.setDuration(1000);
        set.addAnimation(rtl);

        Animation alpha =
                new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(1000);
        set.addAnimation(alpha);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.5f);
        listView.setLayoutAnimation(controller);


    }
}
