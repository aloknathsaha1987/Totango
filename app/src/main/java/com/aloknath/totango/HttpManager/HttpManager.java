package com.aloknath.totango.HttpManager;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALOKNATH on 3/4/2015.
 */
public class HttpManager {

    private static HttpResponse response;

    public static String getData(String url){

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);

        JSONObject jsonobjQueryToSend = new JSONObject();

        JSONArray terms_array = new JSONArray();
        JSONArray group_fields_array = new JSONArray();

        JSONArray is_one_of = new JSONArray();
        is_one_of.put("mobile+testme@totango.com");

        JSONObject term1 = new JSONObject();
        try {
            term1.put("type", "totango_user_scope");
            term1.put("is_one_of", is_one_of);

            JSONObject field1 = new JSONObject();
            field1.put("type", "health");

            group_fields_array.put(field1);
            terms_array.put(term1);

            jsonobjQueryToSend.put("terms", terms_array );
            jsonobjQueryToSend.put("group_fields", group_fields_array );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("query", jsonobjQueryToSend.toString()));

            httpPost.setHeader("app-token", "1a1c626e8cdca0a80ae61b73ee0a1909941ab3d7mobile+testme@totango.com");
            httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

            if(params != null && !params.isEmpty()){
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            response = httpClient.execute(httpPost);

            InputStream ips  = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK)
            {
                try {
                    throw new Exception(response.getStatusLine().getReasonPhrase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            StringBuilder sb = new StringBuilder();
            String s;
            while(true )
            {
                s = buf.readLine();
                if(s==null || s.length()==0)
                    break;
                sb.append(s);

            }
            buf.close();
            ips.close();

            Log.i("Returned Data",sb.toString());

            return sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }

      return "";
    }


}
