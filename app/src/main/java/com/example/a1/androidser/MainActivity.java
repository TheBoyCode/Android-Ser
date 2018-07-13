package com.example.a1.androidser;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text = findViewById(R.id.Text_Login);
        text.setTextColor(Color.BLACK);
        /*
        * Autofilling DataBAse for test
        * */
        autoFilling();
    }
    ValidatorService validatorService =new ValidatorService();
    boolean UserPassword=true,UserEmail=true;
    public void ClickOk(View view) {
        EditText textPassword = findViewById(R.id.Text_Password);
        EditText textEmail = findViewById(R.id.Text_Login);
        if(!(validatorService.Valid_Password(textPassword.getText().toString()))) {
            UserPassword=false;
            textPassword.setBackgroundColor(Color.RED);
        }
        else {
            UserPassword = true;
            textPassword.setBackgroundColor(Color.GREEN);
        }
        if(!(validatorService.Valid_Email(textEmail.getText().toString()))) {
            UserEmail =false;
            textEmail.setBackgroundColor(Color.RED);
        }
        else {
            UserEmail=true;
            textEmail.setBackgroundColor(Color.GREEN);
        }
        if(UserPassword && UserEmail) {
            String email = textEmail.getText().toString();
            String pass = textPassword.getText().toString();
            if(validatorService.Valid_IsUser(email,pass)) {
                Intent intent =new Intent(this,CheckPoint.class);
                startActivity(intent);
            }
        }
    }
    private void autoFilling() {
        DB db = DB.getIntence();
        UserModel user = new UserModel();
        user.Password="123123";
        user.Email="test6@gmail.com";
        user.id= UUID.randomUUID().toString();

        Question q = new Question();
        q.id=UUID.randomUUID().toString();
        q.QestionText="firs\nttg";
        q.scores = new ArrayList<>();
        q.PointId=UUID.randomUUID().toString();
        Score score = new Score();
        score.id=UUID.randomUUID().toString();
        score.QuectionId=q.id;
        score.Value = 0f;
        q.scores.add(score);

        Question q2 = new Question();
        q2.id=UUID.randomUUID().toString();
        q2.QestionText="second";
        q2.scores = new ArrayList<>();
        q2.PointId=q.PointId;
        Score score2 = new Score();
        score2.id=UUID.randomUUID().toString();
        score2.QuectionId=q2.id;
        score2.Value = 0f;
        q2.scores.add(score);

        Question q3 = new Question();
        q3.id=UUID.randomUUID().toString();
        q3.QestionText="3";
        q3.scores = new ArrayList<>();
        q3.PointId=q.PointId;
        Score score3 = new Score();
        score3.id=UUID.randomUUID().toString();
        score3.QuectionId=q3.id;
        score3.Value = 0f;
        q3.scores.add(score);

        PointCode point = new PointCode();
        point.Questions=new ArrayList<>();
        point.Questions.add(q);
       // point.Questions.add(q2);
        // point.Questions.add(q3);
        point.Code="343434";
        point.UserId=user.id;
        point.id=q.PointId;
        user.points = new ArrayList<>();
        user.points.add(point);

        db.AddToDb(user,this);
        db.ReadDb(this);
    }
}
