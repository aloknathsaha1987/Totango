package com.aloknath.totango;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.aloknath.totango.Adapters.CustomerAdapter;
import com.aloknath.totango.HttpManager.HttpManager;
import com.aloknath.totango.Objects.ParsedJsonListObject;

import java.util.List;

import static com.aloknath.totango.JsonParser.JsonParserGson.jsonParserGson;
import static com.aloknath.totango.JsonParser.JsonParserJackson.parse;

/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class TotangoActivity extends Activity {

    private List<ParsedJsonListObject> items;
    private ProgressDialog progressDialog;
    private ListView listView;
    private CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totango_list);
        progressDialog = new ProgressDialog(TotangoActivity.this);

        if (isOnline()) {
            GetAsyncData data = new GetAsyncData();
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

    private class GetAsyncData extends AsyncTask<Void , Void, List<ParsedJsonListObject>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data and Creating Objects");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected List<ParsedJsonListObject> doInBackground(Void... voids) {

            String content1 = HttpManager.getData1("https://appem.totango.com/api/v1/search/accounts");
            List<ParsedJsonListObject> itemsReturned = jsonParserGson(content1);

            return itemsReturned;
        }

        @Override
        protected void onPostExecute(List<ParsedJsonListObject> parsedJsonObjects) {
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
