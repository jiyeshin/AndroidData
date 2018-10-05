package com.example.a503_12.androiddata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUse1005 extends AppCompatActivity {

    public void click(View v) {
        final EditText edtxt = (EditText) findViewById(R.id.edtxt);
        switch (v.getId()) {
            case R.id.btnsave:
                //입력한 내용 가져오기
                String str = edtxt.getText().toString();
                try (//기록하기 위한 파일 스트림 만들기
                     // try의 매개변수에 넣으면 close() 안해도 됨. try 안에서 자동으로 닫히므로.
                     FileOutputStream fos = openFileOutput("sample.txt", Context.MODE_PRIVATE)) {
                    fos.write(str.getBytes());
                    fos.flush();
                    edtxt.setText("파일에 문자열 기록 성공");
                } catch (Exception e) {
                }
                break;
            case R.id.btnread:
                try (FileInputStream fis = openFileInput("sample.txt")) {
                    //파일의 크기 만큼의 배열을 생성
                    byte[] b = new byte[fis.available()];

                    //파일 내용을 b에 저장
                    fis.read(b);

                    //파일 내용을 문자열로 변환해서 출력
                    edtxt.setText(new String(b));

                } catch (Exception e) {
                    edtxt.setText("파일이 존재하지 않습니다.");
                }
                break;
            case R.id.btndelete:
                boolean result = deleteFile("sample.txt");
                if (result == true)
                    edtxt.setText("삭제 성공");
                else edtxt.setText("삭제 실패");
                break;
            case R.id.btnreadres:
                try (InputStream is = getResources().openRawResource(R.raw.res);
                     //읽기 스트림을 가지고 문자열 단위로 읽을 수 있는 문자 읽기 스트림 만들기
                    BufferedReader br = new BufferedReader(new InputStreamReader(is))){

                    //읽은 내용을 저장할 수 있는 문자열 객체 생성.
                    //읽은 내용을 추가할 수 있도록 하기 위해서 생성.
                    StringBuilder sb = new StringBuilder();
                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line + "\n");
                    }
                    String msg = sb.toString();
                    edtxt.setText(msg);
                } catch (Exception e) {
                    edtxt.setText(e.getMessage());
                }
                break;
            case R.id.btnimgcopy:
                try(InputStream fis = getResources().openRawResource(R.raw.icecube);
                    FileOutputStream fos = openFileOutput("icecube.jpg", Context.MODE_PRIVATE)){

                        while(true){
                            byte [] b = new byte[1024];
                            //fis에서 읽어서 b에 저장
                            int r = fis.read(b);
                            //읽은게 없으면 종료
                            if(r<=0)break;
                            //읽은 내용이 있으면 fos에 기록
                            fos.write(b,0,r);
                        }
                }catch (Exception e){}
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_use1005);
    }
}
