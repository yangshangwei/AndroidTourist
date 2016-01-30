package com.turing.base.activity.gridViewAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        AdapterView.OnItemClickListener {


    private ImageView imageView;
    private int[] resIds = new int[]
            {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4,
                    R.drawable.item5, R.drawable.item6, R.drawable.item7,
                    R.drawable.item8, R.drawable.item9, R.drawable.item10,
                    R.drawable.item11, R.drawable.item12, R.drawable.item13,
                    R.drawable.item14, R.drawable.item15, R.drawable.item16};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        initView();
    }

    private void initView() {

        GridView gridView = (GridView) findViewById(R.id.gridview);

        List<Map<String, Object>> cells = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < resIds.length; i++) {
            Map<String, Object> cell = new HashMap<String, Object>();
            cell.put("imageview", resIds[i]);
            cells.add(cell);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, cells,
                R.layout.cell, new String[]
                {"imageview"}, new int[]
                {R.id.imageview});
        gridView.setAdapter(simpleAdapter);
        imageView = (ImageView) findViewById(R.id.imageview);
        gridView.setOnItemSelectedListener(this);
        gridView.setOnItemClickListener(this);
        imageView.setImageResource(resIds[0]);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        imageView.setImageResource(resIds[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        imageView.setImageResource(resIds[position]);

    }

}
