package com.shop.myapplication.login.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.Call;
import android.util.Log;

import com.shop.myapplication.login.api.Api;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class OkHttp {
     //单例
     private static OkHttp okHttp=new OkHttp();

     public static OkHttp getOkHttp(){
         return okHttp;
     }
     public void OkHttp(){

     }
     //判断网络
    public boolean isNet(Context context){
         ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return info.isAvailable();
        }
        return false;
    }
    //get请求
    public void doHttpGet(String url, CallBack back){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().get().url(Api.url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: "+string);
            }
        });
    }
    public interface CallBack{
         void Success(String result);
         void Fail(String msg);
    }
}
