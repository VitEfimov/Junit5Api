package com.demoqa.user_suit;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("userID", 654654654);

        Map<String, Object> bookBody = new LinkedHashMap<>();
        bookBody.put("title","9781449325862");
        bookBody.put("subTitle","Git Pocket Guide");
        bookBody.put("author","A Working Introduction");
        bookBody.put("publish_date","Richard E. Silverman");
        bookBody.put("publisher","O'Reilly Media");
        bookBody.put("pages",234);
        bookBody.put("description","This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp");
        bookBody.put("website","http://chimera.labs.oreilly.com/books/1230000000561/index.html");

        requestBody.put("collectionOfIsbns", bookBody);


        System.out.println("requestBody = " + requestBody);
    }
}
