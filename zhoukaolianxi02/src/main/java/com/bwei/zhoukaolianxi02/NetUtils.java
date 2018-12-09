package com.bwei.zhoukaolianxi02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtils {

    public static String getRequest(String dataUrl){
        String result="";
        try {
            URL url=new URL(dataUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                result=Stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String Stream2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (String tmp=bufferedReader.readLine();tmp!=null;tmp=bufferedReader.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }

}
