package com.example.a503_12.androiddata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataCRUD1008 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_crud1008);

        final Button btninsert, btndelete, btnupdate, btnselect;
        final EditText price, name;
        final TextView id;

        btninsert = (Button)findViewById(R.id.btninsert);
        btndelete = (Button)findViewById(R.id.btndelete);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btnselect = (Button)findViewById(R.id.btnselect);

        price =  (EditText)findViewById(R.id.price);
         name = (EditText)findViewById(R.id.name);
         id = (TextView)findViewById(R.id._id);

        btninsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname = name.getText().toString();
                String inputprice = price.getText().toString();

                //디비연결
                DBOpenHelper dbhelper = new DBOpenHelper(DataCRUD1008.this);
                SQLiteDatabase sql = dbhelper.getWritableDatabase();

                ContentValues value = new ContentValues();
                value.put("name", inputname);
                value.put("price", inputprice);
                sql.insert("product", null, value);


               // sql.execSQL("insert into product(name, price) values (\'" + inputname + "\'," + inputprice +");", null);
                sql.close();
                id.setText("삽입 성공");
                name.setText(""); //에딧텍스트 비우기
                price.setText(""); //에딧텍스트 비우기
            }
        });

        btnselect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname = name.getText().toString();
                if(inputname.trim().length() ==0){
                    Toast.makeText(DataCRUD1008.this, "이름을 입력하시오", Toast.LENGTH_SHORT).show();
                    return;
                }
                //디비연결
                DBOpenHelper dbhelper = new DBOpenHelper(DataCRUD1008.this);
                SQLiteDatabase sql = dbhelper.getReadableDatabase();

                Cursor cursor = sql.rawQuery("select * from product where name='" + inputname + "';", null);

                if(cursor.moveToNext()){
                    Toast.makeText(DataCRUD1008.this, "if문 들어옴", Toast.LENGTH_SHORT).show();
                    id.setText(cursor.getInt(0)+"");
                    name.setText(cursor.getString(1));
                    price.setText(cursor.getString(2)+"");

                }else{
                    id.setText("조회된 데이터가 없습니다.");
                }
                dbhelper.close();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname = name.getText().toString();
                String inputprice = price.getText().toString();

                //디비연결
                DBOpenHelper dbhelper = new DBOpenHelper(DataCRUD1008.this);
                SQLiteDatabase sql = dbhelper.getWritableDatabase();

                ContentValues value = new ContentValues();

                value.put("price", Integer.parseInt(inputprice));

                sql.update("product", value, "name='" + inputname + "'", null);
                Toast.makeText(DataCRUD1008.this, "갱신 성공", Toast.LENGTH_SHORT).show();
                dbhelper.close();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname = name.getText().toString();


                //디비연결
                DBOpenHelper dbhelper = new DBOpenHelper(DataCRUD1008.this);
                SQLiteDatabase sql = dbhelper.getWritableDatabase();

                ContentValues value = new ContentValues();

                value.put("name", inputname);

                sql.delete("product","name='" + inputname + "'", null);
                Toast.makeText(DataCRUD1008.this, "삭제 성공", Toast.LENGTH_SHORT).show();
                dbhelper.close();
            }
        });



    }
}
