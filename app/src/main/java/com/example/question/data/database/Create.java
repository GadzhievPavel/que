package com.example.question.data.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Create {
    public static void createQuestions(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        ContentValues valuesRates = new ContentValues();
        for (int i = 1; i < 101; i++) {
            values.put(DBHelper.QUESTION, "Question " + i);
            values.put(DBHelper.DONE, 0);
            database.insert(DBHelper.TABLE_QUESTIONS, null, values);
            values.clear();
            valuesRates.put(DBHelper.RATE,-1);
            valuesRates.put(DBHelper.QUESTION_ID,i);
            database.insert(DBHelper.TABLE_RATES,null,valuesRates);
            values.clear();
        }
    }
}
