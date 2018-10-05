package com.example.a503_12.androiddata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

//데이터베이스 클래스(SQLiteOpenHelper 상속)
class WordDBHelper extends SQLiteOpenHelper {
    //dafault constructor
    public WordDBHelper(Context context) {
        super(context, "engword.db", null, 1);
    }

    //처음 사용될 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블을 생성하는 sql 실행
        db.execSQL("create table dic(_id integer primary key autoincrement, eng text(20), kor text(20));");
    }

    //버전이 변경되면 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //테이블 삭제
        db.execSQL("drop table if exists dic");

        //테이블 다시 생성
        onCreate(db);
    }
}

public class DataTable1005 extends AppCompatActivity {
    private WordDBHelper dbHelper;
    private TextView txtresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_table1005);
        dbHelper = new WordDBHelper(this);
        txtresult = (TextView) findViewById(R.id.txtresult);


        //삭제 버튼을 눌렀을 때
        //eng 값이 orange인 데이터를 삭제
        findViewById(R.id.btndelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from dic where eng = 'orange'");
                dbHelper.close();
                txtresult.setText("삭제 성공");
            }
        });

        //갱신 버튼을 눌렀을 때
        //eng 값이 orange인 데이터의 kor 값을 귤로 바꾸기
        findViewById(R.id.btnupdate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update dic set kor = '귤' where eng = 'orange';");
                dbHelper.close();
                txtresult.setText("수정 성공");
            }
        });



        //조회 버튼을 눌렀을 때
        findViewById(R.id.btnselect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                //select 구문 실행
                Cursor cursor = db.rawQuery("select eng, kor from dic", null);

                String resultText ="";
                while(cursor.moveToNext()){
                    String eng = cursor.getString(0);
                    String kor = cursor.getString(1);
                    resultText = resultText + eng +": "+kor+"\n";
                }
                txtresult.setText(resultText);
                db.close();
            }
        });



        findViewById(R.id.btninsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //sql로 insert
               // db.execSQL("insert into dic(eng, kor) values('apple', '사과');");

                //sql 이용하지 않고 데이터를 삽입
                ContentValues values = new ContentValues();
                values.put("eng", "orange");
                values.put("kor", "오렌지");
                db.insert("dic", null, values);
                txtresult.setText("삽입 성공");
                dbHelper.close();

            }
        });
    }
}
