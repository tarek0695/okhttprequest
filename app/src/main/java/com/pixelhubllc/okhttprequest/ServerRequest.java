package com.pixelhubllc.okhttprequest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerRequest {
    private String ServerResponse;
    HashMap<String,String>mymap;
    HashMap<String,HashMap<String,String>> mymap1;
    public boolean isCompleted=false;

    public ServerRequest(String URL, Context context){
        Request request= new Request.Builder().url(URL).build();
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                ServerResponse=response.body().string();
                Log.e("server response",ServerResponse);
                mymap=new HashMap<>();


//                try {
//                    JSONObject jsonObject=new JSONObject(ServerResponse);
//                        Log.e("jsonobject",jsonObject.toString());
//                        JSONObject jsonObject1=jsonObject.getJSONObject("data");
//
//                        mymap.put("id",jsonObject1.getString("id"));
//                        mymap.put("email",jsonObject1.getString("email"));
//                        mymap.put("first_name",jsonObject1.getString("first_name"));
//                        mymap.put("last_name",jsonObject1.getString("last_name"));
//                        mymap.put("avatar",jsonObject1.getString("avatar"));
//
//
//
//                        Log.e("id",mymap.get("id"));
//                        Log.e("email",mymap.get("email"));
//                        Log.e("firstname",mymap.get("first_name"));
//                    Log.e("lastname",mymap.get("last_name"));
//                    Log.e("avatar",mymap.get("avatar"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                JSONObject jsonObject;
                JSONArray jsonArray;
                mymap1=new HashMap<>();
                try {

                    jsonObject = new JSONObject(ServerResponse);
                    Log.e("json object", jsonObject.toString());
                    mymap.put("page",jsonObject.getString("page"));
                    mymap.put("per_page",jsonObject.getString("per_page"));
                    mymap.put("total",jsonObject.getString("total"));
                    mymap.put("total_pages",jsonObject.getString("total_pages"));
                    mymap.put("data",jsonObject.getString("data"));

                    Log.e("page",mymap.get("page"));
                    Log.e("per_page",mymap.get("per_page"));
                    Log.e("total",mymap.get("total"));
                    Log.e("total_pages",mymap.get("total_pages"));
                    Log.e("data",mymap.get("data"));

                    jsonArray=jsonObject.getJSONArray("data");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("id",jsonObject1.getString("id"));
                        map.put("name",jsonObject1.getString("name"));
                        map.put("year",jsonObject1.getString("year"));
                        map.put("color",jsonObject1.getString("color"));
                        map.put("pantone_value",jsonObject1.getString("pantone_value"));
                        mymap1.put(String.valueOf(i),map);
                    }
                    for(int j=0;j<mymap1.size();j++){
                        Log.e("data",mymap1.get(String.valueOf(j)).toString());
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(context,mymap.get("email"),Toast.LENGTH_SHORT).show();
                        //MainActivity.serverresponse=mymap.get("email");
                        //MainActivity.fun();
                    }
                });

            }
        });
    }
}
