package com.example.cheers.Activity.PersonActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheers.R;

public class MySchoolActivity extends AppCompatActivity {

    private String school , area, major, grade, hometown;


    private EditText mySchool;
    private EditText myArea;
    private EditText myMajor;
    private EditText myGrade;
    private EditText myHomeTown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_school);


        mySchool = (EditText) findViewById(R.id.my_school_edit);
        myArea = (EditText) findViewById(R.id.my_area_edit);
        myMajor = (EditText) findViewById(R.id.my_major_edit);
        myGrade = (EditText) findViewById(R.id.my_grade_edit);
        myHomeTown = (EditText) findViewById(R.id.my_hometown_edit);

        init();

        ImageView imageView = (ImageView) findViewById(R.id.my_school_back);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        TextView save = (TextView) findViewById(R.id.my_school_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                school = mySchool.getText().toString().trim();
                area = myArea.getText().toString().trim();
                major = myMajor.getText().toString().trim();
                grade = myGrade.getText().toString().trim();
                hometown = myHomeTown.getText().toString().trim();


                Toast toast=Toast.makeText(MySchoolActivity.this,"保存成功",Toast.LENGTH_SHORT    );
                toast.show();
            }
        });
    }

    private void init() {

        mySchool.setText(school);
        myArea.setText(area);
        myMajor.setText(major);
        myGrade.setText(grade);
        myHomeTown.setText(hometown);
    }
}
