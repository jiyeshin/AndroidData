package com.example.a503_12.androiddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdaper1010 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adaper1010);

        //SimpleAdapter를 이용해서 출력할 데이터 생성
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "고건태");

        map.put("group", "JBJ95");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "김상균");

        map.put("group", "JBJ95");
        list.add(map);

        //데이터 출력을 위한 Adapter 생성
        //첫번째는 Context
        //두번째는 데이터
        //세번째는 셀의 출력 모양
        //네번째는 출력할 데이터의 키 배열
        //다섯번째는 출력할 셀 안의 id
        SimpleAdapter adaper = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, new String[]{"name", "group"}, new int[]{android.R.id.text1, android.R.id.text2, android.R.id.toggle});

        //ListView에 adapter를 연결해서 출력
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaper);
    }
}
