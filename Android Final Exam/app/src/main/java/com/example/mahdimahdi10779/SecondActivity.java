package com.example.mahdimahdi10779;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {
    double max;
    double initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String place = intent.getStringExtra("place");
        initial=intent.getDoubleExtra("max",0);
        max = initial;
        TextView title = findViewById(R.id.title);
        title.setText(place);
        TextView amount = findViewById(R.id.amount);
        amount.setText("The sum left is "+max);
        Button check = findViewById(R.id.check);
        Context context = this;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.forCheckPrice);
                double forCheck=0;
                try {
                    forCheck = Double.parseDouble(editText.getText().toString());
                }catch (Exception e)
                {
                    return;
                }
                if(forCheck==0)
                {
                    return;
                }
                if(forCheck>max)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("You can't buy this item");
                    alert.setPositiveButton("OK",null);
                    alert.create().show();
                }
                else
                {
                    if(max==0)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setMessage("You Have no more Money.....:(");
                        alert.setPositiveButton("OK",null);
                        alert.create().show();
                        return;
                    }
                    max-=forCheck;
                    amount.setText("The sum left is "+max);
                    MyDBHelper dbHelper = new MyDBHelper(context);
                    dbHelper.insert(new Date(),place,initial,forCheck);
                    if(max<initial/2)
                    {
                        amount.setTextColor(Color.RED);
                    }
                    if(max==0)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setMessage("You Have no more Money.....:(");
                        alert.setPositiveButton("OK",null);
                        alert.create().show();
                    }
                }
            }
        });
    }
}