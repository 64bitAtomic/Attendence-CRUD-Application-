package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SheetActivity extends AppCompatActivity {
    private ListView sheetList;
    private ArrayAdapter adapter;
    private ArrayList<String> listItems = new ArrayList<>();
    private long cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        cid =  getIntent().getLongExtra("cid",-1);
        loadListItem();
        sheetList = findViewById(R.id.sheetList);
        adapter = new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(adapter);

        sheetList.setOnItemClickListener((parent,view,position,id) -> openSheetActivity(position));

    }

    private void openSheetActivity(int position) {
        long[] idArray = getIntent().getLongArrayExtra("idArray");
        int[] rollArray = getIntent().getIntArrayExtra("rollArray");
        String [] nameArray = getIntent().getStringArrayExtra("nameArray");
        Intent intent = new Intent(this,sheetActivity2.class);
        intent.putExtra("idArray",idArray);
        intent.putExtra("nameArray",nameArray);
        intent.putExtra("rollArray",rollArray);
        intent.putExtra("month",listItems.get(position));

        startActivity(intent);
    }

    private void loadListItem() {
        Cursor cursor = new DbHelper(this).getDistinctMonths(cid);

        while(cursor.moveToNext()){
            int st = cursor.getColumnIndex(DbHelper.DATE_KEY);
            String date = cursor.getString(st);
            listItems.add(date.substring(3));
        }
    }
}