package com.example.a503_12.androiddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class GridView1010 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view1010);
        GridView gridView = (GridView)findViewById(R.id.gridV);
        ImageAdapter1010 adapter = new ImageAdapter1010(this);
        gridView.setAdapter(adapter);
    }
}
