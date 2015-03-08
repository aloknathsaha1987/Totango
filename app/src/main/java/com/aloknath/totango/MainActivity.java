package com.aloknath.totango;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.aloknath.totango.Adapters.CustomerAdapter;
import com.aloknath.totango.HttpManager.HttpManager;
import com.aloknath.totango.Objects.ParsedJsonObjects;

import java.util.List;

import static com.aloknath.totango.JsonParser.JsonParserGson.jsonParserGson;


public class MainActivity extends ActionBarActivity {

    private List<ParsedJsonObjects> items;
    private ProgressDialog progressDialog;
    private ListView listView;
    private CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);

        if (isOnline()) {
            getAsyncData data = new getAsyncData();
            data.execute();
            listView = (ListView) findViewById(R.id.listView);

        }else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class getAsyncData extends AsyncTask<Void , Void, List<ParsedJsonObjects>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data and Creating Objects");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected List<ParsedJsonObjects> doInBackground(Void... voids) {
            String content = HttpManager.getData("https://appem.totango.com/api/v1/search/accounts/health_dist");
            String content1 = HttpManager.getData1("https://appem.totango.com/api/v1/search/accounts");
            List<ParsedJsonObjects> itemsReturned = jsonParserGson(content1);

            return itemsReturned;
        }

        @Override
        protected void onPostExecute(List<ParsedJsonObjects> parsedJsonObjects) {
            super.onPostExecute(parsedJsonObjects);
            progressDialog.hide();
            items = parsedJsonObjects;
            displayData();

        }
    }

    private void displayData() {

        // Set the adapter with the list items.

        adapter = new CustomerAdapter(this,R.layout.custom_adapter_layout, items);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
