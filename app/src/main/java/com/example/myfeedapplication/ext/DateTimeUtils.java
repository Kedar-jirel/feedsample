package com.example.myfeedapplication.ext;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    @SuppressLint("SimpleDateFormat")
    public  static  String convertStringToDesiredDateFormat(String d , String inputformat ,String outFormat){
        String d0 ="";
        SimpleDateFormat format = new SimpleDateFormat(inputformat);
        try {
           Date  d1 = format.parse(d);
           d0 = new SimpleDateFormat(outFormat).format(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
     return  d0;
    }
}
