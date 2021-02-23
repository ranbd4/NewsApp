package com.ranapplications.newsapp;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetApiData extends AsyncTask<Void, Void, JSONObject>{

    private GetApiDataCallBack getApiDataCallBack;
    private String urlLink;

    public GetApiData(GetApiDataCallBack getApiDataCallBack, String urlLink) {
        this.getApiDataCallBack = getApiDataCallBack;
        this.urlLink = urlLink;
    }


    @Override
    protected void onPreExecute() {}

    @Override
    protected JSONObject doInBackground(Void... params)
    {

        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(urlLink);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
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

    @Override
    protected void onPostExecute(JSONObject response)
    {
        if(response != null){
            try {
                getApiDataCallBack.onDone(response);
                //Log.e("App", "Success: " + response.getString("yourJsonElement") );
            } catch (Exception e) {
                //Log.e("App", "Failure", ex);
            }
        }
    }

    public interface GetApiDataCallBack {
        void onDone(JSONObject response);

    }
}
