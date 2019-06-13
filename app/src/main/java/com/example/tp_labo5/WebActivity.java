package com.example.tp_labo5;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.support.v7.widget.ShareActionProvider;


public class WebActivity extends AppCompatActivity {

    ActionBar actionBar;
    String page;
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent i = getIntent();

        this.page = (String) i.getStringExtra("page");
        Log.d("Pageee", this.page);

        this.actionBar = getSupportActionBar();

        actionBar.setTitle("Browser");
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView)super.findViewById(R.id.webView);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);

        webView.loadUrl(this.page);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        MenuItem item = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider)  MenuItemCompat.getActionProvider(item);
        Log.d("Paginitaaa", this.page);
        setShareActionIntent(this.page);

       /* MenuItem mi = menu.findItem(R.id.txtBuscar);
        Ser sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this); */

        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String myText){
        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(Intent.EXTRA_TEXT, myText);
        shareActionProvider.setShareIntent(myShareIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        this.finish();
        return super.onOptionsItemSelected(item);
    }


}
