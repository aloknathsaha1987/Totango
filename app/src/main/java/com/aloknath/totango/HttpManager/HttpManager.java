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
    private static JSONObject jsonObj;

    public static String getData1(String url){

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);

        JSONObject jsonobjQueryToSend = new JSONObject();

        JSONObject combined_Json_Objects = new JSONObject();

        try {
            JSONArray terms_array = new JSONArray();
            JSONArray fields_array = new JSONArray();

            jsonobjQueryToSend.put("offset", 0);
            jsonobjQueryToSend.put("count", 1000);
            jsonobjQueryToSend.put("scope", "all");

            JSONArray in_list_array = new JSONArray();
            JSONArray is_one_of_array = new JSONArray();

            JSONObject term1 = new JSONObject();
            JSONObject term2 = new JSONObject();

            term1.put("type", "string");
            term1.put("term", "health");
            in_list_array.put("green");
            in_list_array.put("red");
            in_list_array.put("yellow");
            term1.put("in_list", in_list_array);

            term2.put("type", "totango_user_scope");
            is_one_of_array.put("mobile+testme@totango.com");
            term2.put("is_one_of", is_one_of_array);

            terms_array.put(term1);
            terms_array.put(term2);

            jsonobjQueryToSend.put("terms", terms_array);

            JSONObject field1 = new JSONObject();
            JSONObject field2 = new JSONObject();
            JSONObject field3 = new JSONObject();
            JSONObject field4 = new JSONObject();
            JSONObject field5 = new JSONObject();
            JSONObject field6 = new JSONObject();
            JSONObject field7 = new JSONObject();
            JSONObject field8 = new JSONObject();
            JSONObject field9 = new JSONObject();
            JSONObject field10 = new JSONObject();


            field1.put("type", "health_trend");
            field1.put("field_display_name", "Health last change");
            field1.put("desc", true);
            fields_array.put(field1);

            field2.put("type", "health_reason");
            fields_array.put(field2);

            field3.put("type", "date_attribute");
            field3.put("attribute", "Contract Renewal Date");
            field3.put("field_display_name", "Contract Renewal Date");
            fields_array.put(field3);

            field4.put("type", "number");
            field4.put("term", "contract_value");
            field4.put("field_display_name", "Value");
            fields_array.put(field4);

            field5.put("type", "string");
            field5.put("term", "status");
            field5.put("field_display_name", "Status");
            fields_array.put(field5);

            field6.put("type", "number");
            field6.put("term", "score");
            field6.put("field_display_name", "Engagement");
            fields_array.put(field6);

            field7.put("type", "on_attention");
            field7.put("user_id", "mobile+testme@totango.com");
            fields_array.put(field7);

            field8.put("type", "named_aggregation");
            field8.put("aggregation", "unique_users");
            field8.put("duration", 14);
            field8.put("field_display_name", "Active users (14d)");
            fields_array.put(field8);

            field9.put("type","number_metric_change");
            field9.put("metric", "unique_users" );
            field9.put("duration", 14);
            field9.put("relative_to", 14);
            field9.put("field_display_name", "Active users % change (14d)");
            fields_array.put(field9);

            field10.put("type", "last_touch");
            fields_array.put(field10);

            jsonobjQueryToSend.put("fields", fields_array);

            JSONObject date_term = new JSONObject();
            date_term.put("type", "date");
            date_term.put("term", "date");
            date_term.put("eq", 0);

            combined_Json_Objects.put("date_term", date_term);
            combined_Json_Objects.put("", jsonobjQueryToSend);


            /*

            hits object with the fields:
                private String name;
                private String display_name;
                private long int last_activity_time;
                private List<SelectedFields> selectedFields;

            Data 2:
             {"response":{
                "accounts":{
                    "hits":[
                        {"name":"TheBalmoralBahamas",
                         "display_name":"Electro Construction Corp.",
                         "last_activity_time":1422048357000,
                         "selected_fields":
                                [
                                    {
                                        "health":"green",
                                        "health_prev":"red",
                                        "change_date":1423810800000,
                                        "offset":1728000000
                                    }
                                    ,{
                                        "reasons":[
                                                        {
                                                            "value":"Requirements Defined",
                                                             "reason_term":{
                                                                            "type":"string_attribute",
                                                                            "attribute":"GetSat_LIfecycle",
                                                                            "in_list":
                                                                                ["Requirements Defined",
                                                                                 "Implementation Completed",
                                                                                 "Success Plan Completed",
                                                                                 "Implementation Started"]
                                                                         },
                                                             "term_passed":true
                                                         }
                                           ]
                                     }
                                    ,1434610800000
                                    ,36609.0
                                    ,"Paying"
                                    ,0
                                    ,{"on_attention":false,"date_added":null},0,0.0,null]
                                    ,"is_online":false},

                            {"name":"Intelisys","display_name":"Intelisys","last_activity_time":1425491350000,


                            "selected_fields":[

                            {"health":"green","health_prev":"yellow","change_date":1423638000000,"offset":1900800000}

                            ,{"reasons":[{"value":41,"reason_term":{"type":"named_aggregation","aggregation":"licenses_utilization","duration":90,"gte":20},"term_passed":true},{"value":540,"reason_term":{"type":"activity_aggregation","activities":["Add Task"],"duration":90,"gte":1},"term_passed":true},{"value":510,"reason_term":{"type":"activity_aggregation","activities":["Create Project"],"duration":90,"gte":1},"term_passed":true},{"value":4442,"reason_term":{"type":"named_aggregation","aggregation":"minutes_on_system","duration":90,"gte":1},"term_passed":true}]},

                            1455346800000,1200.0,"Paying-Basic",75,{"on_attention":false,"date_added":null},10,-0.09090909,{"totango_user_name":"cloudrumba@totango.com","touch_time":{"date":1423179887000}}],"is_online":false},
                            {"name":"RedAntenna-1","display_name":"RedAntenna-1","last_activity_time":1425491384000,"selected_fields":[{"health":"yellow","health_prev":"red","change_date":1423638000000,"offset":1900800000},{"reasons":[{"value":11,"reason_term":{"type":"named_aggregation","aggregation":"licenses_utilization","duration":90,"gte":20},"term_passed":false},{"value":450,"reason_term":{"type":"activity_aggregation","activities":["Add Task"],"duration":90,"gte":1},"term_passed":true},{"value":519,"reason_term":{"type":"activity_aggregation","activities":["Create Project"],"duration":90,"gte":1},"term_passed":true},{"value":3017,"reason_term":{"type":"named_aggregation","aggregation":"minutes_on_system","duration":90,"gte":1},"term_passed":true}]},1456988400000,1500.0,"Paying",75,{"on_attention":false,"date_added":null},10,0.0,null],"is_online":false},
                            {"name":"MohawkInteractive-1","display_name":"MohawkInteractive-1","last_activity_time":1425491151000,"selected_fields":[{"health":"yellow","health_prev":"red","change_date":1423638000000,"offset":1900800000},{"reasons":[{"value":7,"reason_term":{"type":"named_aggregation","aggregation":"licenses_utilization","duration":90,"gte":20},"term_passed":false},{"value":270,"reason_term":{"type":"activity_aggregation","activities":["Add Task"],"duration":90,"gte":1},"term_passed":true},{"value":285,"reason_term":{"type":"activity_aggregation","activities":["Create Project"],"duration":90,"gte":1},"term_passed":true},{"value":1575,"reason_term":{"type":"named_aggregation","aggregation":"minutes_on_system","duration":90,"gte":1},"term_passed":true}]},1438153200000,5000.0,"Paying-Basic",25,{"on_attention":false,"date_added":null},6,0.0,null],"is_online":false},
                            {"name":"TheOceanAgency-1","display_name":"TheOceanAgency-1","last_activity_time":1425491099000,"selected_fields":[{"health":"green","health_prev":"red","change_date":1423638000000,"offset":1900800000},{"reasons":[{"value":83,"reason_term":{"type":"named_aggregation","aggregation":"licenses_utilization","duration":90,"gte":20},"term_passed":true},{"value":451,"reason_term":{"type":"activity_aggregation","activities":["Add Task"],"duration":90,"gte":1},"term_passed":true},{"value":471,"reason_term":{"type":"activity_aggregation","activities":["Create Project"],"duration":90,"gte":1},"term_passed":true},{"value":2664,"reason_term":{"type":"named_aggregation","aggregation":"minutes_on_system","duration":90,"gte":1},"term_passed":true}]},1449990000000,2300.0,"Paying",25,{"on_attention":false,"date_added":null}

             */

            //jsonObj = new JSONObject("{\"offset\":0,\"count\":1000,\"scope\":\"all\",\"terms\":[{\"type\":\"string\",\"term\":\"health\",\"in_list\":[\"green\",\"red\",\"yellow\"]},{\"type\":\"totango_user_scope\",\"is_one_of\":[\"mobile+testme@totango.com\"]}],\"fields\":[{\"type\":\"health_trend\",\"field_display_name\":\"Health last change\",\"desc\":true},{\"type\":\"health_reason\"},{\"type\":\"date_attribute\",\"attribute\":\"Contract Renewal Date\",\"field_display_name\":\"Contract Renewal Date\"},{\"type\":\"number\",\"term\":\"contract_value\",\"field_display_name\":\"Value\"},{\"type\":\"string\",\"term\":\"status\",\"field_display_name\":\"Status\"},{\"type\":\"number\",\"term\":\"score\",\"field_display_name\":\"Engagement\"},{\"type\":\"on_attention\",\"user_id\":\"\\mobile+testme@totango.com\"},{\"type\":\"named_aggregation\",\"aggregation\":\"unique_users\",\"duration\":14,\"field_display_name\":\"Active users (14d)\"},{\"type\":\"number_metric_change\",\"metric\":\"unique_users\",\"duration\":14,\"relative_to\":14,\"field_display_name\":\"Active users % change (14d)\"},{\"type\":\"last_touch\"}]}&date_term:{\"type\":\"date\",\"term\":\"date\",\"eq\":0}");
            jsonObj = new JSONObject("{\"offset\":0,\"count\":1000,\"scope\":\"all\",\"terms\":[{\"type\":\"string\",\"term\":\"health\",\"in_list\":[\"green\",\"red\",\"yellow\"]},{\"type\":\"totango_user_scope\",\"is_one_of\":[\"mobile+testme@totango.com\"]}],\"fields\":[{\"type\":\"health_trend\",\"field_display_name\":\"Health last change\",\"desc\":true},{\"type\":\"health_reason\"},{\"type\":\"date_attribute\",\"attribute\":\"Contract Renewal Date\",\"field_display_name\":\"Contract Renewal Date\"},{\"type\":\"number\",\"term\":\"contract_value\",\"field_display_name\":\"Value\"},{\"type\":\"string\",\"term\":\"status\",\"field_display_name\":\"Status\"},{\"type\":\"number\",\"term\":\"score\",\"field_display_name\":\"Engagement\"},{\"type\":\"on_attention\",\"user_id\":\"mobile+testme@totango.com\"},{\"type\":\"named_aggregation\",\"aggregation\":\"unique_users\",\"duration\":14,\"field_display_name\":\"Active users (14d)\"},{\"type\":\"number_metric_change\",\"metric\":\"unique_users\",\"duration\":14,\"relative_to\":14,\"field_display_name\":\"Active users % change (14d)\"},{\"type\":\"last_touch\"}]}&date_term:{\"type\":\"date\",”term\":\"date\",\"eq\":0}");
            Log.i("Combined Values", jsonObj.toString());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("query", jsonObj.toString()));

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

            Log.i("Returned Data2: ",sb.toString());

            return sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

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

            Log.i("Returned Data1: ",sb.toString());

            return sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }

      return "";
    }

// Returned Data1:﹕ {"service_id":"899","_revision":"r41edc37","_type":"status","_version":1.0,"hits":{"missing":51318,"health":{"missing":51318,"red":{"total_hits":5963,"contract_value":{"sum":1.44786301E8,"avg":24623.52057823129}},"green":{"total_hits":7,"contract_value":{"sum":197276.0,"avg":28182.285714285714}},"yellow":{"total_hits":11130,"contract_value":{"sum":7.57392278E8,"avg":150365.74905697835}}},"total_hits":68418,"contract_value":{"sum":1.080631843E9,"avg":57425.43538101817}},"status":"success"}
// Returned Data2:﹕ {"response":{"accounts":null},"service_id":"899","_revision":"r41edc37","_type":"status","_version":1.0,"status":"Server error","took":13}


}
