package com.example.mahdimahdi10779;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] array = {"Pharmacy","Supermarket","Other"};
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,array);
        spinner.setAdapter(adapter);
        Button start = findViewById(R.id.start);
        Context context = this;
        Button hist = findViewById(R.id.hist);
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ThirdActivity.class);
                startActivity(intent);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText1);
                double max=0;
                try {
                    max = Double.parseDouble(editText.getText().toString());
                }catch (Exception e)
                {
                    return;
                }
                if(max>200)
                {
                    Toast.makeText(context,"Max is 200",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("place",spinner.getSelectedItem().toString());
                intent.putExtra("max",max);
                startActivity(intent);
            }
        });
    }
}