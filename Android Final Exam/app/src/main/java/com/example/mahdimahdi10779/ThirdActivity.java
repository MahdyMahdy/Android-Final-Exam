package com.example.mahdimahdi10779;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        MyDBHelper dbHelper = new MyDBHelper(this);
        ArrayList<String> data = dbHelper.getAll();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}