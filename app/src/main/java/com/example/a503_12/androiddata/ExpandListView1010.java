package com.example.a503_12.androiddata;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandListView1010 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view1010);

        //제목으로 사용할 문자열 배열 생성
        String[] main = {"JBJ", "JBJ95"};

        //제목을 누르면 보여질 문자열 배열 생성
        String[][] sub = {
                {"켄타", "김용국", "김상균", "노태현", "김동한", "권현빈"},
                {"고건태", "야마구치"}
        };

        //위의 데이터를 가지고 출력할 수 있는 데이터를 생성
        List<Map<String, String>> mainData = new ArrayList<>();
        List<List<Map<String, String>>> subData = new ArrayList<>();
        for (int i = 0; i < main.length; i = i + 1) {
            Map<String, String> mainMap = new HashMap<>();
            mainMap.put("main", main[i]);
            mainData.add(mainMap);

            List<Map<String, String>> subList = new ArrayList<>();
            for (int j = 0; j < sub[i].length; j = j + 1) {
                Map<String, String> subMap = new HashMap<>();
                subMap.put("sub", sub[i][j]);
                subList.add(subMap);
            }
            subData.add(subList);
        }
        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.exlistV);
        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
            this, mainData, android.R.layout.simple_expandable_list_item_1,
            new String []{"main"},
            new int [] {android.R.id.text1},
            subData,
            android.R.layout.simple_expandable_list_item_1,
            new String[]{"sub"},
            new int []{android.R.id.text1}
        );
        expandableListView.setAdapter(adapter);
        }
    }
