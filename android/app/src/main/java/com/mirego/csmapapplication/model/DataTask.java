package com.mirego.csmapapplication.model;

import android.os.AsyncTask;
import android.util.Log;

import com.mirego.csmapapplication.activity.MainActivity;

import org.json.JSONObject;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Florian on 24/03/2018.
 */
public class DataTask extends AsyncTask<Void, Void, JSONObject>
{

    @Override
    protected JSONObject doInBackground(Void... params)
    {

        String str="https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return  new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}