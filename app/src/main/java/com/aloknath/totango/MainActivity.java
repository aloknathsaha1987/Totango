package com.aloknath.totango;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aloknath.totango.HttpManager.HttpManager;
import com.aloknath.totango.Objects.ParsedJsonObjects;

import java.util.List;

import static com.aloknath.totango.JsonParser.JsonParserGson.jsonParserGson;


public class MainActivity extends ActionBarActivity {

    private List<ParsedJsonObjects> items;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);

        getAsyncData data = new getAsyncData();
        data.execute();

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

        Toast.makeText(this, "The Display Name: " + items.get(5).getDisplayName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "The Display Name: " + items.get(500).getDisplayName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "The Display Name: " + items.get(999).getDisplayName(), Toast.LENGTH_SHORT).show();

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
