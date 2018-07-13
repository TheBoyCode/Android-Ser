package com.example.a1.androidser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class PageService {
    private DB db = DB.getIntence();
    private static int CurrentPage=0;
    private User user =User.getIntence();
    public void AddPage(String question,Context context)
    {
        ValidatorService validatorService = new ValidatorService();
        if(validatorService.Valid_Question(question))
        {
            db.AddNew(question,context);
        }
    }
    public void ToRating(float value)
    {
        db.ToRating(value,CurrentPage);
    }
    public void RatingLess()
    {
        db.RatingLess(CurrentPage-2);
    }

    public boolean PositionFirst()
    {
       if(CurrentPage == 1)return true;
       else return false;
    }
    public void CurentLess()
    {
        CurrentPage--;
    }
    private  String getNextQuestion()
    {
        if(CurrentPage>=user.point.Questions.size())return null;
        return  user.point.Questions.get(CurrentPage).QestionText;
    }
    public void NewCurrentPage()
    {
        CurrentPage=0;
    }
    public  void CreatePage(Context context)
    {
        String BuffQuestion;
        BuffQuestion = getNextQuestion();
        if(BuffQuestion!=null)
        {
            CurrentPage++;
            Intent intent = new Intent(context, ScoreFrom.class);
            intent.putExtra("Question",BuffQuestion);
            context.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(context.getApplicationContext(), ThanksForm.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public  void ToFirst(Context context)
    {
        NewCurrentPage();
        String question = getNextQuestion();
        Intent intent = new Intent(context,ScoreFrom.class);
        intent.putExtra("Question",question);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public  boolean DbIsEmpty()
    {
        return db.isEmpty();
    }
    public static void ToHome(Context context)
    {
        Intent intent = new Intent(context,StartEditCreateForm.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void ChangeQuestion(String newText,int oldText)
    {
        db.ChangeQuestion(newText,oldText);
    }
    public  void DeleteFromDb(int question,Context context)
    {
        db.Delete(question,context);
    }

    public void SizeScrean(String question, Activity activity) {
        TextView text =activity.findViewById(R.id.textView);
        text.setText(question);
        text.setMovementMethod(new ScrollingMovementMethod());
        if((question.length()>90 && isLandscapeMode(activity)||
                (isLandscapeMode(activity) && (countEnter(question)>3) ))) {
            if(activity.getWindowManager().getDefaultDisplay().getHeight()<800) {
                text.setHeight(200);
            }
            else {
                text.setHeight(300);
            }
        }
        else if((question.length()>90 && !(isLandscapeMode(activity))) ||
                (!(isLandscapeMode(activity)) && (countEnter(question)>3))) {
            if(activity.getWindowManager().getDefaultDisplay().getHeight()<1300) {
                text.setHeight(300);
            }
            else  text.setHeight(500);
        }
        else {
            text.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
        }
    }
    private boolean isLandscapeMode(Activity activity) {
        int width =
                activity.getWindowManager().getDefaultDisplay().getWidth();
        int height =
                activity.getWindowManager().getDefaultDisplay().getHeight();

        boolean isLandscape = width > height;
        return isLandscape;
    }
    private int countEnter(String s) {
        int count=0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='\n') {
                count++;
            }
        }
        return count;
    }
}
