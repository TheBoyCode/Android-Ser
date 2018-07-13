package com.example.a1.androidser;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class ValidatorService {
    private static final String EMAIL_PATTERN ="^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_" +
            "`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop" +
            "|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
    boolean Valid_Email(String email) {
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    boolean Valid_Password(String password) {
        if((password.length())>=6) return true;
        else return false;
    }
    public boolean Valid_IsQuestion(int buff) {
        User user = User.getIntence();
        if(user.point.Questions.size()>buff) {
            return true;
        }
        return false;
    }
    public boolean Valid_Question(String buff) {
        if(buff.length()>0)return true;
        return false;
    }
    public boolean Valid_IsUser(String email, String password) {
        DB db = DB.getIntence();
        UserModel usermodel = new UserModel();
        usermodel.Email=email;
        usermodel.Password=password;
        if(db.IsUser(usermodel)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean Valid_IsPoint(String code) {
        User user = User.getIntence();
        for (PointCode p:user.points) {
            if(p.Code.equals(code))
            {
                user.point= p;
                return true;
            }
        }
        return false;
    }
    public boolean IsNumber(String s) {
       return TextUtils.isDigitsOnly(s);
    }
}
