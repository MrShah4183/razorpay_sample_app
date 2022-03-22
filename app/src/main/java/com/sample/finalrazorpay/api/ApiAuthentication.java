package com.sample.finalrazorpay.api;

import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class ApiAuthentication {

    /*Authenticator.setDefault(new Authenticator(){
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("myuser","mypass".toCharArray());
    }});
HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
c.setUseCaches(false);
c.connect();*/

    public static String getAuthToken() {
        /*Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("myuser", "mypass".toCharArray());
            }
        });
        HttpURLConnection c = (HttpURLConnection) new URL("fasdf").openConnection();
        c.setUseCaches(false);
        try {
            c.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        byte[] data = new byte[0];
        try {
            data = ("rzp_test_goYqawczzlm2Yp" + ":" + "SOhszD6GYteU8ZNnNFTwppNu").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
        //final String basicAuth = "Basic " + Base64.encodeToString("rzp_test_goYqawczzlm2Yp:SOhszD6GYteU8ZNnNFTwppNu".getBytes(), Base64.NO_WRAP);
        return  "Basic " + Base64.encodeToString("rzp_test_goYqawczzlm2Yp:SOhszD6GYteU8ZNnNFTwppNu".getBytes(), Base64.NO_WRAP);
    }
}
