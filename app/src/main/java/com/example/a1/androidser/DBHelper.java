package com.example.a1.androidser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public  static  final  String DATABASE_NAME = "YService";

    public  static  final  String TABLE_CONTACTS ="users";
    public  static  final  String KEY_ID="user_id";
    public  static  final  String KEY_MAIL="mail";
    public  static  final  String KEY_PASSWORD="password";

    public  static  final  String TABLE_CONTACTS_POINTS ="points";
    public  static  final  String KEY_ID_POINT="point_id";
    public  static  final  String KEY_ID_POINT_ToUSER="user_id";
    public  static  final  String KEY_CODE_POINT="code";

    public  static  final  String TABLE_CONTACTS_QUESTION ="questions";
    public  static  final  String KEY_ID_QUESTION="question_id";
    public  static  final  String KEY_QuestionText="question";
    public  static  final  String KEY_POINTID_QUESTION="point_id";

    public static final String TABLE_CONTACTS_SCORE = "rating";
    public static final String KEY_SCOREID= "score_id";
    public static final String KEY_VALUE = "value";
    public static final String KEY_QuESTIONID = "questionid";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsers="CREATE TABLE "+ TABLE_CONTACTS+" ("+KEY_ID+" text PRIMARY KEY, "
                +KEY_MAIL +" text, "+KEY_PASSWORD+" text);";
        String createPoints = "CREATE TABLE "+ TABLE_CONTACTS_POINTS+" ("+ KEY_ID_POINT+" text " +
                " PRIMARY KEY, "+KEY_CODE_POINT+" text, "+KEY_ID_POINT_ToUSER+" text);";
        String createQuestion = "CREATE TABLE "+TABLE_CONTACTS_QUESTION+" ("+KEY_ID_QUESTION+
                " text PRIMARY KEY, "+KEY_QuestionText+" text, "+KEY_POINTID_QUESTION+
                " text);";
        String createRating = "CREATE TABLE "+ TABLE_CONTACTS_SCORE+" ("+KEY_SCOREID+" text PRIMARY KEY, "
                +KEY_VALUE +" real, "+ KEY_QuESTIONID+" text);";
        sqLiteDatabase.execSQL(createUsers);
        sqLiteDatabase.execSQL(createPoints);
        sqLiteDatabase.execSQL(createQuestion);
        sqLiteDatabase.execSQL(createRating);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE  "+TABLE_CONTACTS);
        sqLiteDatabase.execSQL("DROP TABLE  "+TABLE_CONTACTS_POINTS);
        sqLiteDatabase.execSQL("DROP TABLE  "+TABLE_CONTACTS_QUESTION);
        sqLiteDatabase.execSQL("DROP TABLE  "+TABLE_CONTACTS_SCORE);
        onCreate(sqLiteDatabase);
    }
}
