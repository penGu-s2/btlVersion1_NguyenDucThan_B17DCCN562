package com.example.btlversion1.data;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Trangchu_html extends AsyncTask<String, Void ,String> {
    @Override
    protected String doInBackground(String... params) {
        StringBuffer buffer = new StringBuffer();
        try {
            Document document = (Document) Jsoup.connect(params[0]).get();
            Elements sub = document.select("article#article_body");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
