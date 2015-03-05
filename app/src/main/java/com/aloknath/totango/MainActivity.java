package com.aloknath.totango;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aloknath.totango.HttpManager.HttpManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAsyncData data = new getAsyncData();
        data.execute();

    }

    private class getAsyncData extends AsyncTask<Void , Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String content = HttpManager.getData("https://appem.totango.com/api/v1/search/accounts/health_dist");
            return null;
        }
    }


    //https://appem.totango.com/api/v1/search/accounts?app-token=1a1c626e8cdca0a80ae61b73ee0a1909941ab3d7mobile+testme@totango.com

   /*
   {
"request":{
"email":"a@b.com",
"old_passw":306,
"use_id":123,
"new_passw":456
}
}

JSONObject header = new JSONObject();
header.put("email", "a@b.com");
header.put("old_passw", "306");
header.put("use_id", "123");
header.put("new_passw", "456");


2 Create new jSON object (outer with key 'request' ) and put inner jSON object inside it as a value to the key "request".


JSONObject student1 = new JSONObject();
try {
    student1.put("id", "3");
    student1.put("name", "NAME OF STUDENT");
    student1.put("year", "3rd");
    student1.put("curriculum", "Arts");
    student1.put("birthday", "5/5/1993");

} catch (JSONException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

JSONObject student2 = new JSONObject();
try {
    student2.put("id", "2");
    student2.put("name", "NAME OF STUDENT2");
    student2.put("year", "4rd");
    student2.put("curriculum", "scicence");
    student2.put("birthday", "5/5/1993");

} catch (JSONException e) {

    e.printStackTrace();
}


{        "student":
          [

            {
                "id": 1,
                "name": "John Doe",
                "year": "1st",
                "curriculum": "Arts",
                "birthday": 3/3/1995

            },
            {
                "id": 2,
                "name": "Michael West",
                "year": "2nd",
                "curriculum": "Economic",
                "birthday": 4/4/1994
            }
        ]
    }

JSONArray jsonArray = new JSONArray();

jsonArray.put(student1);
jsonArray.put(student2);

JSONObject studentsObj = new JSONObject();
    studentsObj.put("Students", jsonArray);



String jsonStr = studentsObj.toString();

    System.out.println("jsonString: "+jsonStr);



JSONObject jsonobj = new JSONObject();
jsonobj.put("request", header);

****************************************************************************
*

   query =
   {
         "terms":
                  [
                    {
                       "type":"totango_user_scope",
                       "is_one_of":
                                  [
                                      “mobile+testme@totango.com"
                                  ]
                    }
                  ]
          ,

          "group_fields" :
                         [
                            {"type":"health"}
                         ]

   }



      Creating a JsonObject for the above query.

*************************************************************************
*
*


******************************************************************************************************






    */
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
