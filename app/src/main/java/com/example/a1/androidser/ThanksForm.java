package com.example.a1.androidser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ThanksForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_form);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void ToFirst(View view) {
        DB db;
        db = DB.getIntence();
        db.UpdateDb(this);
        PageService service = new PageService();
        service.NewCurrentPage();
        service.CreatePage(this);
    }
}
