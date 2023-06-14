package com.demoqa.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    public static final String URL;
    public static final String BASE_URL;
    public static final String FIRST_USERID;
    public static final String SECOND_USERID;
    public static final String THIRD_USERID;
    public static final String FIRST_USER_NAME;
    public static final String FIRST_USER_PASSWORD;
    public static final String SECOND_USER_NAME;
    public static final String SECOND_USER_PASSWORD;
    public static final String THIRD_USER_NAME;
    public static final String THIRD_USER_PASSWORD;
    public static final String FIRST_USER_TOKEN;



    static{
        Properties properties = null;
        String environment = System.getProperty("environment") != null ? environment = System.getProperty("environment") : ConfigurationReader.getProperty("environment");
        try {

            String path = System.getProperty("user.dir") + "/src/test/resources/Environment/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = properties.getProperty("url");
        BASE_URL = properties.getProperty("base_url");
        FIRST_USERID = properties.getProperty("first_userID");
        FIRST_USER_NAME = properties.getProperty("first_userName");
        FIRST_USER_PASSWORD = properties.getProperty("first_password");
        FIRST_USER_TOKEN = properties.getProperty("first_userToken");
        SECOND_USERID = properties.getProperty("second_userID");
        SECOND_USER_NAME = properties.getProperty("second_userName");
        SECOND_USER_PASSWORD = properties.getProperty("second_password");
        THIRD_USERID = properties.getProperty("third_userID");
        THIRD_USER_NAME = properties.getProperty("third_userName");
        THIRD_USER_PASSWORD = properties.getProperty("third_password");



    }


}

