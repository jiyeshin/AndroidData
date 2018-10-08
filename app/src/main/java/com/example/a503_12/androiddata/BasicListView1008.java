package com.example.a503_12.androiddata;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BasicListView1008 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list_view1008);

        //출력할 데이터 배열이나 list 생성
        //String [] month = {"1월", "2월", "3월", "4월", "5월", "6월", "7월","8월","9월","10월","11월","12월",};

        //위의 데이터를 가지고 Adapter 생성
        //첫번째는 매개변수 context
        //두번째는 출력할 셀의 모양: 제공되는 모야 사용
        //세번째는 출력할 데이터
        //ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, month);

        //ListView에 위의 Adaptor 연결
        ListView listView = (ListView)findViewById(R.id.listview);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter1);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable(Color.BLUE));
        listView.setDividerHeight(3);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            //첫번째 매개변수는 이벤트가 발생한 객체
            //두번째 매개변수는 항목 뷰: 선택한 항목의 뷰
            //세번째 매개변수는 클릭한 항목 뷰의 인덱스
            //네번째 매개변수는 항목 뷰의 아이디
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BasicListView1008.this, position + "번째 선택", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
