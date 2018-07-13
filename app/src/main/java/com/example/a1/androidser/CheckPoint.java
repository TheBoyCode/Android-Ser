package com.example.a1.androidser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CheckPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_point);
    }
    public void Click_OK(View view) {
        ValidatorService validatorService =new ValidatorService();
        EditText pointCode=findViewById(R.id.editTextPointname);
        if(validatorService.Valid_IsPoint(pointCode.getText().toString())) {
          PageService.ToHome(this);
        }
    }

}
