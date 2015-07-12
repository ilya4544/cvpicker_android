package com.example.ilya.cvpicker;

/**
 * Created by Ilya on 12.07.2015.
 */
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Downloader extends DefaultHandler {
    private List<CV> articleList;
    private String title;
    private String link;
    private StringBuilder sb = new StringBuilder();
    private FeedParsed feedParsed;

    public Downloader(FeedParsed feedParsedCallback, String url) {
        articleList = new ArrayList<CV>();
        this.feedParsed = feedParsedCallback;
        new GetFeedTask().execute(url);
    }

    class GetFeedTask extends AsyncTask<String, Void, CV[]> {

        String url = "http://cvpicker.mybluemix.net/";

        @Override
        protected CV[] doInBackground(String... arg) {
            try {

                String json = Jsoup.connect(url + "dashboard").ignoreContentType(true).execute().body();
                Gson gson = new Gson();
                articleList = Arrays.asList(gson.fromJson(json, CV[].class));

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(CV[] aVoid) {
            feedParsed.onFeedParsed(articleList);
        }
    }
}
