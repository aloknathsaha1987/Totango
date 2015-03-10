package com.aloknath.totango;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aloknath.totango.HttpManager.HttpManager;
import com.aloknath.totango.Objects.Hits;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DonutSeries;
import com.shinobicontrols.charts.DonutSeriesStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import static com.aloknath.totango.JsonParser.JsonParserJackson.parse;

/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class UserProfileActivity extends ActionBarActivity {

    private DonutSeriesStyle style;
    private Toolbar toolbar;
    private Hits hit;
    private ProgressDialog progressDialog;
    private View relative_layout_view;
    private ShinobiChart shinobiChart;
    private ShinobiChart shinobiChart1 ;
    private ShinobiChart shinobiChart2;
    private ShinobiChart shinobiChart3 ;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shinobi_layout);

        relative_layout_view = findViewById(R.id.layout2);

        toolbar = (Toolbar)findViewById(R.id.include);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View cView = getLayoutInflater().inflate(R.layout.header, null);
        getSupportActionBar().setCustomView(cView);
        progressDialog = new ProgressDialog(UserProfileActivity.this);

        ChartFragment chartFragment =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);

        ChartFragment chartFragment1 =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.chart1);

        ChartFragment chartFragment2 =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.chart2);

        ChartFragment chartFragment3 =
                (ChartFragment) getFragmentManager().findFragmentById(R.id.chart3);


        shinobiChart = chartFragment.getShinobiChart();
        shinobiChart1 = chartFragment1.getShinobiChart();
        shinobiChart2 = chartFragment2.getShinobiChart();
        shinobiChart3 = chartFragment3.getShinobiChart();

        shinobiChart.setLicenseKey("HEAmLOhVhSLadZ6MjAxNTA0MDdhbG9rNjk2OTY5QGdtYWlsLmNvbQ==bDrK4eTbUnTUnG19HjrcGpHdKyBhHzLGe2Byv+AggmvjOSuKyFdKGzgHbo9X3gK8QhekTSmtHxadRVTt9NLcl/EDy7Cv01aS8xsHwCRZrXU7iDYIGBEVZnLVXfqXoksat6XQua3zFQ33RFB1GW6gY+ctGSG0=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");
        shinobiChart1.setLicenseKey("HEAmLOhVhSLadZ6MjAxNTA0MDdhbG9rNjk2OTY5QGdtYWlsLmNvbQ==bDrK4eTbUnTUnG19HjrcGpHdKyBhHzLGe2Byv+AggmvjOSuKyFdKGzgHbo9X3gK8QhekTSmtHxadRVTt9NLcl/EDy7Cv01aS8xsHwCRZrXU7iDYIGBEVZnLVXfqXoksat6XQua3zFQ33RFB1GW6gY+ctGSG0=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");
        shinobiChart2.setLicenseKey("HEAmLOhVhSLadZ6MjAxNTA0MDdhbG9rNjk2OTY5QGdtYWlsLmNvbQ==bDrK4eTbUnTUnG19HjrcGpHdKyBhHzLGe2Byv+AggmvjOSuKyFdKGzgHbo9X3gK8QhekTSmtHxadRVTt9NLcl/EDy7Cv01aS8xsHwCRZrXU7iDYIGBEVZnLVXfqXoksat6XQua3zFQ33RFB1GW6gY+ctGSG0=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");
        shinobiChart3.setLicenseKey("HEAmLOhVhSLadZ6MjAxNTA0MDdhbG9rNjk2OTY5QGdtYWlsLmNvbQ==bDrK4eTbUnTUnG19HjrcGpHdKyBhHzLGe2Byv+AggmvjOSuKyFdKGzgHbo9X3gK8QhekTSmtHxadRVTt9NLcl/EDy7Cv01aS8xsHwCRZrXU7iDYIGBEVZnLVXfqXoksat6XQua3zFQ33RFB1GW6gY+ctGSG0=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");


        if (isOnline()) {
            GetAsyncData data = new GetAsyncData();
            data.execute();

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

    private class GetAsyncData extends AsyncTask<Void , Void, Hits > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data and Creating Objects");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected Hits doInBackground(Void... voids) {
            String content = HttpManager.getData("https://appem.totango.com/api/v1/search/accounts/health_dist");

            Hits hit_returned = parse(content);

            return hit_returned;
        }

        @Override
        protected void onPostExecute(Hits hit_returned) {
            super.onPostExecute(hit_returned);
            progressDialog.hide();
            hit = hit_returned;
            displayData();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void displayData() {


        int[] crustColors = new int[]{Color.RED, Color.GREEN, Color.YELLOW};
        int[] crustColor1 = new int[]{Color.RED};
        int[] crustColor2 = new int[]{Color.GREEN};
        int[] crustColor3 = new int[]{Color.YELLOW};

        DataAdapter<String, Integer> dataAdapter = new SimpleDataAdapter<String, Integer>();
        dataAdapter.add(new DataPoint<String, Integer>("", hit.getHealth().getRed().getTotal_hits()));
        dataAdapter.add(new DataPoint<String, Integer>("" ,hit.getHealth().getGreen().getTotal_hits()));
        dataAdapter.add(new DataPoint<String, Integer>("", hit.getHealth().getYellow().getTotal_hits()));

        DonutSeries donutSeries = new DonutSeries();
        donutSeries.setInnerRadius(0.75f);
        donutSeries.setOuterRadius(0.85f);
        donutSeries.setDataAdapter(dataAdapter);
        shinobiChart.addSeries(donutSeries);

        style = donutSeries.getStyle();
        style.setCrustShown(true);
        style.setLabelsShown(true);
        style.setCrustColors(crustColors);
        style.setCrustThickness(1.5f);
        style.setFlavorColors(crustColors);


        DataAdapter<String, Integer> dataAdapter1 = new SimpleDataAdapter<String, Integer>();
        dataAdapter1.add(new DataPoint<String, Integer>("", hit.getHealth().getRed().getTotal_hits()));

        DonutSeries donutSeries1 = new DonutSeries();
        donutSeries1.setInnerRadius(0.55f);
        donutSeries1.setOuterRadius(0.65f);
        donutSeries1.setDataAdapter(dataAdapter1);
        shinobiChart1.addSeries(donutSeries1);

        style = donutSeries1.getStyle();
        style.setCrustColors(crustColor1);
        style.setCrustThickness(1.5f);
        style.setFlavorColors(crustColor1);

        DataAdapter<String, Integer> dataAdapter2 = new SimpleDataAdapter<String, Integer>();
        dataAdapter2.add(new DataPoint<String, Integer>("", hit.getHealth().getGreen().getTotal_hits()));
        DonutSeries donutSeries2 = new DonutSeries();
        donutSeries2.setInnerRadius(0.55f);
        donutSeries2.setOuterRadius(0.65f);
        donutSeries2.setDataAdapter(dataAdapter2);
        shinobiChart2.addSeries(donutSeries2);
        style = donutSeries2.getStyle();
        style.setCrustColors(crustColor2);
        style.setCrustThickness(1.5f);
        style.setFlavorColors(crustColor2);

        DataAdapter<String, Integer> dataAdapter3 = new SimpleDataAdapter<String, Integer>();
        dataAdapter3.add(new DataPoint<String, Integer>("", hit.getHealth().getYellow().getTotal_hits()));
        DonutSeries donutSeries3 = new DonutSeries();
        donutSeries3.setInnerRadius(0.55f);
        donutSeries3.setOuterRadius(0.65f);
        donutSeries3.setDataAdapter(dataAdapter3);
        shinobiChart3.addSeries(donutSeries3);
        style = donutSeries3.getStyle();
        style.setCrustColors(crustColor3);
        style.setCrustThickness(1.5f);
        style.setFlavorColors(crustColor3);


        TextView textView = (TextView)relative_layout_view.findViewById(R.id.edit_accounts_text);
        textView.setText(String.valueOf(hit.getTotal_hits()));


        double d = hit.getContract_value().getAvg()/1000;
        d = Math.round(d*100)/100.0d;

        textView = (TextView)relative_layout_view.findViewById(R.id.edit_contract_text);
        textView.setText("$" + String.valueOf(d) + "M");


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void clickEvent(View item) {

        switch (item.getId()) {

            case R.id.imageView:
                Toast.makeText(this,"Person Icon Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView2:
                Toast.makeText(this,"Search Icon Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_portfolio:
                Toast.makeText(this,"Portfolio Icon Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_attention:
                Toast.makeText(this,"Attention Icon Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_notification:
                Toast.makeText(this,"Notification Icon Clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

}
