package com.pixelhubllc.okhttprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    public static String serverresponse;
    static TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytext=findViewById(R.id.mytextview);


        String api="";

      new ServerRequest("https:/reqres.in/api/users/2",this);

    }

    public static void fun(){
        mytext.setText(serverresponse);
    }
}

//.addInterceptor(new Interceptor() {
//@NotNull
//@Override
//public Response intercept(@NotNull Chain chain) throws IOException {
//
//        Request request=chain.request();
//
//        Request newRequest= request.newBuilder()
//        .addHeader("Authorization", "c2VyYS1hYW0tYXBpLXYxLWJ5LWRldnhwYXJ0 ")
//        .addHeader("Content-Type","application/json")
//        .build();
//        return chain.proceed(newRequest);
//        }
//        })