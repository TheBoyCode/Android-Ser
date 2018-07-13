package com.example.a1.androidser;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_form);
    }
    public void Click_Back(View view)
    {
        finish();
    }
    public void Click_Delete(View view) {
        PageService service = new PageService();
        ValidatorService validatorService = new ValidatorService();
        EditText question = findViewById(R.id.edittext_nameText);
        if(validatorService.IsNumber(question.getText().toString())) {
            service.DeleteFromDb(Integer.valueOf(question.getText().toString())-1,this);
            finish();
        }
        else {
            question.setTextColor(Color.RED);
        }
    }
}
