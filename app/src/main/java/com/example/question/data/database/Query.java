package com.example.question.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.question.data.Question;

import java.util.ArrayList;

public class Query {
    private static Query instance;

    private static DBHelper dbHelper;
    private static SQLiteDatabase database;

    private Query(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public static Query getInstance(Context context){
        if(instance==null){
            instance=new Query(context);
        }
        return instance;
    }
public static ArrayList<Question> getQuestionsWithoutAnswer(){
    ArrayList<Question> questions = new ArrayList<>();
    Cursor cursor = database.rawQuery("select questions._id, questions.done,questions.question, rates.rate from questions,rates where rates._id = questions._id  and questions.done = 0 LIMIT 10",null);
    cursor.moveToNext();
    for (int i = 0; !cursor.isAfterLast();i++){
        Question question = new Question();
        question.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
        question.setDone(cursor.getInt(cursor.getColumnIndex(DBHelper.DONE)));
        question.setText(cursor.getString(cursor.getColumnIndex(DBHelper.QUESTION)));
        question.setRate(cursor.getInt(cursor.getColumnIndex(DBHelper.RATE)));
        questions.add(question);
        cursor.moveToNext();
    }
    cursor.moveToNext();
    cursor.close();
    return questions;
}
public static ArrayList<Question> getQuestions(){
        ArrayList<Question> questions =new ArrayList<>();
        Cursor cursor = database.rawQuery("select questions._id, questions.done,questions.question, rates.rate from questions,rates where rates._id = questions._id",null);
        cursor.moveToNext();
        for(int i = 0;!cursor.isAfterLast();i++){
            Question question = new Question();
            question.setText(cursor.getString(cursor.getColumnIndex(DBHelper.QUESTION)));
            question.setDone(cursor.getInt(cursor.getColumnIndex(DBHelper.DONE)));
            question.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
            question.setRate(cursor.getInt(cursor.getColumnIndex(DBHelper.RATE)));
            questions.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        return questions;
}

public static void updateRate(int rate,int id){
        database.execSQL("UPDATE "+DBHelper.TABLE_RATES+" SET "+DBHelper.RATE+"="+rate +" where "+DBHelper.ID+"="+id);
}
public static void updateDone(int i,int id){
        database.execSQL("UPDATE "+DBHelper.TABLE_QUESTIONS+" SET "+DBHelper.DONE+"="+ i +" where "+DBHelper.ID+"="+id );
}
public static Integer countDoneQuestion(){
        Cursor cursor = database.rawQuery("select count (questions._id) from questions where done=1",null);
        cursor.moveToNext();
        return cursor.getInt(0);
}
public static void close(){
        database.close();
}
}










