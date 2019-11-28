package com.pixelhubllc.okhttprequest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerRequest {
    private String ServerResponse;
    HashMap<String,String>mymap;
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

                HashMap<String,HashMap<String,String>> mymap2;



                try {
                    JSONObject jsonObject=new JSONObject(ServerResponse);
                        Log.e("jsonobject",jsonObject.toString());
                        JSONObject jsonObject1=jsonObject.getJSONObject("data");

                        mymap.put("id",jsonObject1.getString("id"));
                        mymap.put("email",jsonObject1.getString("email"));
                        mymap.put("first_name",jsonObject1.getString("first_name"));
                        mymap.put("last_name",jsonObject1.getString("last_name"));
                        mymap.put("avatar",jsonObject1.getString("avatar"));



                        Log.e("id",mymap.get("id"));
                        Log.e("email",mymap.get("email"));
                        Log.e("firstname",mymap.get("first_name"));
                    Log.e("lastname",mymap.get("last_name"));
                    Log.e("avatar",mymap.get("avatar"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,mymap.get("email"),Toast.LENGTH_SHORT).show();
                        MainActivity.serverresponse=mymap.get("email");
                        MainActivity.fun();
                    }
                });

            }
        });
    }
}
