package com.example.a503_12.androiddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EditingListView1008 extends AppCompatActivity {
    private List<String> list;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_list_view1008);

        list = new ArrayList<>();
        list.add("Encapsulation(캡슐화)");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listView = (ListView)findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        Button insert = (Button)findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.word);
                //입력한 내용의 공백을 제거하고 가져오기
                String word = editText.getText().toString().trim();
                if(word.length() == 0) {
                    Toast.makeText(EditingListView1008.this, "삽입할 단어를 입력하시오.", Toast.LENGTH_SHORT).show();
                    return;
                }
                list.add(word);

                //리스트 뷰를 다시 출력
                adapter.notifyDataSetChanged();
                editText.setText("");
                Toast.makeText(EditingListView1008.this, "삽입 성공", Toast.LENGTH_SHORT).show();
            }
        });

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                //선택된 행 번호 가져오기
                int pos = listView.getCheckedItemPosition();

                //선택한 행 번호가 List 안의 번호인지 확인해서 리턴
                if(pos<0 || pos >=list.size()){
                    Toast.makeText(EditingListView1008.this, "선택 후 삭제 버튼을 누르시오.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //데이터 삭제
                list.remove(pos);
                */

                //선택 여부를 배열로 받아오기
                SparseBooleanArray sb = listView.getCheckedItemPositions();
                /*
                //순서대로 접근해서 삭제: 삭제되면 인덱스 계속 변경되므로 앞에서부터 순차적으로 접근하면 안됨. 뒤부터 세야 함
                for(int i =0; i<sb.size(); i=i+1){
                    if(sb.get(i)==true){
                        list.remove(i);
                    }
                    */
                for(int i =listView.getCount()-1; i>=0;i=i-1){
                    if(sb.get(i)){
                        list.remove(i);
                    }
                }

                //리스트 뷰를 다시 출력
                adapter.notifyDataSetChanged();
                listView.clearChoices();
                Toast.makeText(EditingListView1008.this, "삭제 성공", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
