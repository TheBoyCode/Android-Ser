package com.example.a1.androidser;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;

public class Edition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        EditText text = findViewById(R.id.edittext_newname);
        text.setMovementMethod(new ScrollingMovementMethod());
    }

    private PageService pageService = new PageService();
    public void Click_Back(View view) {
        finish();
    }
    public void Click_Save(View view) {
        ValidatorService service = new ValidatorService();
        EditText oldName = findViewById(R.id.edittext_oldname);
        EditText newName = findViewById(R.id.edittext_newname);
        String newText = newName.getText().toString();
        ValidatorService validatorService = new ValidatorService();
        if(validatorService.IsNumber(oldName.getText().toString())) {
            int oldText = Integer.valueOf(oldName.getText().toString());
            if(service.Valid_IsQuestion(oldText-1)) {
                pageService.ChangeQuestion(newText,oldText-1);
                PageService.ToHome(this);
            }
            else {
                oldName.setTextColor(Color.RED);
            }
        }
         else {
            oldName.setTextColor(Color.RED);
        }
    }
}
