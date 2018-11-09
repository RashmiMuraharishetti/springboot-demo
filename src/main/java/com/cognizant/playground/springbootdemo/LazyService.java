package com.cognizant.playground.springbootdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LazyService {
    public List<Lesson> getLessons(Date date) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        Lesson lesson2 = new Lesson();

        lesson1.setTitle("test title 1");
        lesson2.setTitle("test title 2");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lesson1.setDeliveredOn(simpleDateFormat.parse("2018-10-10"));
            lesson2.setDeliveredOn(simpleDateFormat.parse("2019-10-10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return lessons;
    }
}
