package com.example.question.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DBNAME = "proforientation";
    public static final int VERSION =1;

    public static final String ID = "_id";

    public static final String TABLE_QUESTIONS="questions";
    public static final String QUESTION = "question";
    public static final String DONE = "done";

    public static final String TABLE_RATES = "rates";
    public static final String RATE = "rate";
    public static final String QUESTION_ID="question_id";

    public DBHelper(Context context){
        super(context,DBNAME,null,VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_QUESTIONS+" ( " +
                ID +" integer not null primary key autoincrement unique, "+
                QUESTION +" text not null, " +
                DONE+" int not null)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_RATES+" ( "+
                ID +" integer not null primary key autoincrement unique, "+
                RATE + " Numeric, "+
                QUESTION_ID + " Numeric references "+TABLE_QUESTIONS +" ( "+ID+" )) ");
        Create.createQuestions(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i<i1){
            sqLiteDatabase.execSQL("drop table if exists "+ TABLE_QUESTIONS);
            sqLiteDatabase.execSQL("drop table if exists "+ TABLE_RATES);
            onCreate(sqLiteDatabase);
        }
    }
}
