package com.example.tp_labo5;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    ActionBar actionBar;
    String page;

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
        getMenuInflater().inflate(R.menu.menu, menu);

       /* MenuItem mi = menu.findItem(R.id.txtBuscar);
        Ser sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this); */

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}
