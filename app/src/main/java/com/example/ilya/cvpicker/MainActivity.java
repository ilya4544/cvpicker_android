package com.example.ilya.cvpicker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity implements FeedParsed {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Downloader(this, "http://echo.msk.ru/interview/rss-fulltext.xml");
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        CV item = ((CV) listView.getAdapter().getItem(position));

       // Intent toFullArticle = new Intent(this, FullArticleActivity.class);
        //toFullArticle.putExtra(EXTRA_TITLE, item.getTitle());
        //toFullArticle.putExtra(EXTRA_URL, item.getLink());

        //startActivity(toFullArticle);
    }

    public static final String EXTRA_URL = "LINK";
    public static final String EXTRA_TITLE = "TITLE";

    @Override
    public void onFeedParsed(List<CV> articleList) {
        if (articleList == null) {
            Toast.makeText(this, "Error! Check your internet connection and try again.", Toast.LENGTH_LONG).show();
            return;
        }
        ArrayAdapter<CV> adapter = new ArrayAdapter<CV>(this, android.R.layout.simple_list_item_1, articleList);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
