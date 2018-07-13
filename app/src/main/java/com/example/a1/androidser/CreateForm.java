package com.example.a1.androidser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
    }
    public void Click_Back(View view) {
        finish();
    }
    public void Click_Create(View view) {
        PageService pageService = new PageService();
        EditText text = findViewById(R.id.edtxtQuestion);
        pageService.AddPage(text.getText().toString(),this);
        PageService.ToHome(this);
        finish();
    }
}
