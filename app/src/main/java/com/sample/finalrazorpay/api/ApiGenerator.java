package com.sample.finalrazorpay.api;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGenerator {
    private static Retrofit retrofit = null;

    final String basicAuth = "Basic " + Base64.encodeToString("rzp_test_goYqawczzlm2Yp:SOhszD6GYteU8ZNnNFTwppNu".getBytes(), Base64.NO_WRAP);

    public static Retrofit getApi(String baseURL) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new ApiInterceptor())
                .build();
        //vaibhav bhai ip String finalBaseUrl = "http://192.168.175.41:9090/";
        String finalBaseUrl = "http://192.168.175.38:8080/";
        if (baseURL.trim().isEmpty()) {
            finalBaseUrl = finalBaseUrl;
        } else {
            finalBaseUrl = "";
            finalBaseUrl = baseURL;
        }

        retrofit = new Retrofit.Builder()
                //.baseUrl(baseURL)
                .baseUrl(finalBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }
}
