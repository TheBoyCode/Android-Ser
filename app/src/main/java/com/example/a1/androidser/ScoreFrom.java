
package com.example.a1.androidser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;

public class ScoreFrom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_from);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = getIntent();
        question=intent.getStringExtra("Question");
        pageService.SizeScrean(question,this);
        IsFirst();
    }
    private PageService pageService =   new PageService();
    private String question;
    public void  Click_Next(View view) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        pageService.ToRating( ratingBar.getRating());
        pageService.CreatePage(this);
    }
    public void Click_Back(View view) {
        pageService.RatingLess();
        pageService.CurentLess();
        finish();
    }
    private void IsFirst() {
        Button btn_back=findViewById(R.id.btn_back);
       if( pageService.PositionFirst()) {
           btn_back.setVisibility(View.INVISIBLE);
       }
       else {
            btn_back.setVisibility(View.VISIBLE);
       }
    }

}
