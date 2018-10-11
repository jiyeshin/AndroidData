package com.example.a503_12.androiddata;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter1010 extends BaseAdapter {
    //Context 변수
    Context context;

    //출력할 데이터의 List
    List<VO1010> data;

    //하나의 셀을 만들 레이아웃의 id
    int layout;

    //layout을 화면에 출력하기 위해서 전개할 inflater
    LayoutInflater inflater;

    //생성자
    public  MyAdapter1010(Context context, List<VO1010> data, int layout){
        this.context = context;
        this.data = data;
        this.layout = layout;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //이 메소드가 리턴한 개수만큼 ListView는 항목을 출력
    @Override
    public int getCount() {
        return data.size();
    }

    //항목의 이름을 설정하는 메소드
    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    //항목의 아이디를 설정하는 메소드
    @Override
    public long getItemId(int position) {
        return position;
    }

    //항목 뷰를 만드는 메소드 ***중요***
    //첫번째 매개변수는 출력되는 뷰의 인덱스: getCount 메소드에서 얻은 개수만큼의 크기
    //두번째 매개변수는 이전 셀의 참고
    //세번째 매개변수는 출력되는 AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position은 지역변수라서 annoymous class에서 사용할 수 없기 때문에 final로 변환.
        final int pos = position;

        //이 전에 넘어온 뷰가 없으면 생성
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        //짝수 행과 홀수 행의 배경색을 다르게 설정
        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.CYAN);
        }else {
            convertView.setBackgroundColor(Color.GRAY);
        }

        //이미지 출력
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imgV);
        imageView.setImageResource(data.get(pos).getIcon());

        //텍스트 출력
        TextView textView = (TextView)convertView.findViewById(R.id.textV);
        textView.setText(data.get(pos).getName());

        //버튼을 클릭했을 때 수행할 내용 작성
        Button btn = (Button)convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = data.get(pos).getName() + "선택";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
