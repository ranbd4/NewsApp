package com.ranapplications.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetArrayNews implements GetApiData.GetApiDataCallBack {

    private ArrayList<NewsClass> newsClasses = new ArrayList<>();
    private String urlLink;
    private GetArrayNewsCallBack callBack;

    public GetArrayNews(String urlLink, GetArrayNewsCallBack callBack) {
        this.urlLink = urlLink;
        this.callBack = callBack;

        GetApiData getApiData = new GetApiData(this,urlLink);
        getApiData.execute();
    }

    @Override
    public void onDone(JSONObject response) {
        JSONObject jsonObj = response;
        try {
            JSONArray articles = jsonObj.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {

                JSONObject c = articles.getJSONObject(i);
                String title = c.getString("title");
                String url = c.getString("url");
                String urlToImage = c.getString("urlToImage");
                String publishedAt = c.getString("publishedAt");
                String author = c.getString("author");
                String content = c.getString("content");
                String description = c.getString("description");

                JSONObject source = c.getJSONObject("source");
                String sourceName = source.getString("name");


                newsClasses.add(new NewsClass(author, title, description, url,urlToImage, publishedAt, content,sourceName));
            }

            callBack.onDone(newsClasses);

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface GetArrayNewsCallBack{
        void onDone(ArrayList<NewsClass> newsClasses);
    }
}
