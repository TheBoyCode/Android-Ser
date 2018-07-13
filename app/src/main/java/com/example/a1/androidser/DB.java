package com.example.a1.androidser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.Mms.Part.FILENAME;

public  class DB {
    private static List<UserModel> users = new ArrayList<>();
    private User user = User.getIntence();
    private static DB Intence;
    private void ToQuestionScore(Score score) {
        for (int i=0; i<users.size();i++) {
            for(int j=0;j<users.get(i).points.size();j++) {
               for(int k =0; k<users.get(i).points.get(j).Questions.size();k++) {
                   if(users.get(i).points.get(j).Questions.get(k).id.equals(score.QuectionId)) {
                       users.get(i).points.get(j).Questions.get(k).scores.add(score);
                   }
               }
            }
        }
    }
    private void ToPointQuestion(Question question) {
        for (int i=0; i<users.size();i++) {
           for(int j=0;j<users.get(i).points.size();j++) {
               if(users.get(i).points.get(j).id.equals(question.PointId)) {
                   users.get(i).points.get(j).Questions.add(question);
               }
           }
        }
    }
    private void ToUserPoint(PointCode point) {
        for (int i=0; i<users.size();i++) {
            if(users.get(i).id.equals(point.UserId)) {
                users.get(i).points.add(point);
            }
        }
    }
    private DB(){}
    public static DB getIntence() {
        if(Intence==null) {
            Intence = new DB();
        }
        return Intence;
    }
    public void AddToDb(UserModel model,Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqldb = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(sqldb,1,1);

        ContentValues contentValuesUser = new ContentValues();
        contentValuesUser.put(dbHelper.KEY_MAIL,model.Email);
        contentValuesUser.put(dbHelper.KEY_PASSWORD,model.Password);
        contentValuesUser.put(dbHelper.KEY_ID,model.id);
        sqldb.insert(dbHelper.TABLE_CONTACTS,null,contentValuesUser);

        for ( int i = 0; i < model.points.size(); i++ ){
            SQLiteDatabase sq = dbHelper.getWritableDatabase();
            ContentValues contentValuesPoint = new ContentValues();
            contentValuesPoint.put(dbHelper.KEY_ID_POINT,model.points.get(i).id);
            contentValuesPoint.put(dbHelper.KEY_CODE_POINT,model.points.get(i).Code);
            contentValuesPoint.put(dbHelper.KEY_ID_POINT_ToUSER,model.points.get(i).UserId);
            sq.insert(dbHelper.TABLE_CONTACTS_POINTS,null,contentValuesPoint);
            sq.close();

            for (int j = 0; j<model.points.get(i).Questions.size(); j++){
                SQLiteDatabase sqlQuest = dbHelper.getWritableDatabase();
                ContentValues contentValuesOuestion = new ContentValues();
                contentValuesOuestion.put(dbHelper.KEY_ID_QUESTION,model.points.get(i).Questions.get(j).id);
                contentValuesOuestion.put(dbHelper.KEY_POINTID_QUESTION,model.points.get(i).Questions.get(j).PointId);
                contentValuesOuestion.put(dbHelper.KEY_QuestionText,model.points.get(i).Questions.get(j).QestionText);
                sqlQuest.insert(dbHelper.TABLE_CONTACTS_QUESTION,null,contentValuesOuestion);
                sqlQuest.close();

                for ( int k = 0; k<model.points.get(i).Questions.get(j).scores.size(); k++){
                    SQLiteDatabase sqlScore = dbHelper.getWritableDatabase();
                    ContentValues contentValuesScore = new ContentValues();
                    contentValuesScore.put(dbHelper.KEY_SCOREID,model.points.get(i).Questions.get(j).scores.get(k).id);
                    contentValuesScore.put(dbHelper.KEY_VALUE,model.points.get(i).Questions.get(j).scores.get(k).Value);
                    contentValuesScore.put(dbHelper.KEY_QuESTIONID,model.points.get(i).Questions.get(j).scores.get(k).QuectionId);
                    sqlScore.insert(dbHelper.TABLE_CONTACTS_SCORE,null,contentValuesScore);
                    sqlScore.close();
                }
            }
        }
        sqldb.close();
    }
    public void ReadDb(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqldb = dbHelper.getWritableDatabase();
        Cursor cursor = sqldb.query(dbHelper.TABLE_CONTACTS,null,null,
                null,null,null,null,null);

        if(cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(dbHelper.KEY_ID);
            int MailIndex = cursor.getColumnIndex(dbHelper.KEY_MAIL);
            int PasswordIndex = cursor.getColumnIndex(dbHelper.KEY_PASSWORD);
            do{
                UserModel model = new UserModel();
                model.points= new ArrayList<>();
                model.id = cursor.getString(idIndex);
                model.Email = cursor.getString(MailIndex);
                model.Password = cursor.getString(PasswordIndex);
                users.add(model);
            }while (cursor.moveToNext());
        }
        ////// All Users
        Cursor cursorPoint = sqldb.query(dbHelper.TABLE_CONTACTS_POINTS,null,null,
                null,null,null,null,null);
        if(cursorPoint.moveToFirst()) {
            int idIndexP = cursorPoint.getColumnIndex(dbHelper.KEY_ID_POINT);
            int CodeIndex = cursorPoint.getColumnIndex(dbHelper.KEY_CODE_POINT);
            int UserIndex = cursorPoint.getColumnIndex(dbHelper.KEY_ID_POINT_ToUSER);
            do{
                PointCode point = new PointCode();
                point.Questions=new ArrayList<>();
                point.id = cursorPoint.getString(idIndexP);
                point.UserId = cursorPoint.getString(UserIndex);
                point.Code= cursorPoint.getString(CodeIndex);
                ToUserPoint(point);
            }while (cursorPoint.moveToNext());
        }
        /////ALL POINTS
        Cursor cursorQuestion = sqldb.query(dbHelper.TABLE_CONTACTS_QUESTION,null,null,
                null,null,null,null,null);
        if (cursorQuestion.moveToFirst()){
            int idIndexQ = cursorQuestion.getColumnIndex(dbHelper.KEY_ID_QUESTION);
            int TextIndex = cursorQuestion.getColumnIndex(dbHelper.KEY_QuestionText);
            int PointIdIndex = cursorQuestion.getColumnIndex(dbHelper.KEY_POINTID_QUESTION);
            do{
                Question question = new Question();
                question.scores = new ArrayList<>();
                question.id = cursorQuestion.getString(idIndexQ);
                question.QestionText = cursorQuestion.getString(TextIndex);
                question.PointId = cursorQuestion.getString(PointIdIndex);
                ToPointQuestion(question);
            }while (cursorQuestion.moveToNext());
        }
        //All QUESTIONS
        Cursor cursorScore = sqldb.query(dbHelper.TABLE_CONTACTS_SCORE,null,null,
                null,null,null,null,null);
        if(cursorScore.moveToFirst()) {
            int idIndexS = cursorScore.getColumnIndex(dbHelper.KEY_SCOREID);
            int ValueIndex = cursorScore.getColumnIndex(dbHelper.KEY_VALUE);
            int QuestionId = cursorScore.getColumnIndex(dbHelper.KEY_QuESTIONID);
            do{
                Score score = new Score();
                score.id= cursorScore.getString(idIndexS);
                score.Value= cursorScore.getFloat(ValueIndex);
                score.QuectionId = cursorScore.getString(QuestionId);
                ToQuestionScore(score);
            }while (cursorScore.moveToNext());
        }
        cursorScore.close();
        cursor.close();
        cursorPoint.close();
        sqldb.close();
    }
    public void AddNew(String question,Context context) {
        Question newQuestion = new Question();
        newQuestion.QestionText=question;
        newQuestion.PointId=user.point.id;
        newQuestion.id =  UUID.randomUUID().toString();
        Score score = new Score();
        score.QuectionId=newQuestion.id;
        score.id=UUID.randomUUID().toString();
        score.Value=0f;
        newQuestion.scores = new ArrayList<>();
        newQuestion.scores.add(score);
        user.point.Questions.add(newQuestion);
        UpdateDb(context);
    }
    public  boolean isEmpty() {
        return user.point.Questions.isEmpty();
    }
    public boolean IsUser(UserModel model) {
        for (UserModel userModel: users) {
            if(model.Email.equals(userModel.Email) && model.Password.equals(userModel.Password)) {
                /*
                * Write to Singleton
                * */
                user.Email=userModel.Email;
                user.Password=userModel.Password;
                user.points=userModel.points;
                user.id=userModel.id;
                return true;
            }
        }
        return false;
    }
    public void ToRating(float value, int currentPage) {
        Score score = new Score();
        score.Value = value;
        user.point.Questions.get(currentPage-1).scores.add(score);
    }
    public void RatingLess(int currentPage) {
        user.point.Questions.get(currentPage).scores.remove(
                user.point.Questions.get(currentPage).scores.size()-1);
    }
    public void ChangeQuestion(String newText,int oldText) {
        if(user.point.Questions.size()>oldText) {
            user.point.Questions.get(oldText).QestionText=newText;
        }

    }
    public void Delete(int question,Context context) {
        if(user.point.Questions.size()>question) {
            user.point.Questions.remove(question);
            UpdateDb(context);
        }
    }
    public void UpdateDb(Context context) {
        int count=users.size();
        for(int i=0;i<users.size();i++) {
            if(user.id.equals(users.get(i).id)) {
                for(int j =0;j<users.get(i).points.size();j++) {
                    if(users.get(i).points.get(j).id.equals(user.point.id)) {
                        users.get(i).points.remove(j);
                        users.get(i).points.add(user.point);
                    }
                }
            }
        }
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqldb = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(sqldb,1,1);
        for(int i=0;i<count;i++) {
            AddToDb(users.get(i),context);
        }
    }
}
