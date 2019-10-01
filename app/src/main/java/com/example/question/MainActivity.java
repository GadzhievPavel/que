package com.example.question;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.question.data.Question;
import com.example.question.data.database.AdapterTest;
import com.example.question.data.database.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadActivity();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onUpdateScreen(View view){
        loadActivity();
    }
    private void loadActivity(){
        ProgressBar progressBar;
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.rec);
        ArrayList<Question> questions;
        Query.getInstance(this);
        progressBar.setProgress(Query.countDoneQuestion());
        questions = Query.getQuestionsWithoutAnswer();
        AdapterTest adapterTest = new AdapterTest(questions,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterTest);

    }
}
