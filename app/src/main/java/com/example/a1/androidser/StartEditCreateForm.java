package com.example.a1.androidser;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartEditCreateForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_edit_create_form);
        if(!(pageService.DbIsEmpty())) {
            Button btn_start= findViewById(R.id.btn_start);
            btn_start.setTextColor(Color.BLACK);
        }
    }
    private PageService pageService =new PageService();
    public void Click_Start(View view) {
      // CreatePage(view);
        if(pageService.DbIsEmpty()) {
            Button btn_start= findViewById(R.id.btn_start);
            btn_start.setTextColor(Color.RED);
        }
        else pageService.CreatePage(this);
    }
    public void Click_Create(View view) {
        Intent intent =new Intent(this,CreateForm.class);
        startActivity(intent);
    }
    public void Click_Edit(View view) {
        Intent intent =new Intent(this,Edition.class);
        startActivity(intent);
    }
    public void Click_Delte(View view) {
        Intent intent = new Intent(this,DeleteForm.class);
        startActivity(intent);
    }

}
