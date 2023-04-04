package com.example.a2b.Network;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ConnectURI {
    private String USER_AGENT = "Mozilla/5.0";
    private Context mcontext;
    public static URL buildURL(String urlQuery){
        URL url=null;
        try {
            url = new URL(urlQuery.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI" +url);
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput =scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            }else{
                return null;
            }
        } finally{
            urlConnection.disconnect();
        }
    }
}