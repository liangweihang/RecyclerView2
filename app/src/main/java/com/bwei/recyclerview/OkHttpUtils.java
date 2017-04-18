package com.bwei.recyclerview;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 作者： 宋智豪
 * * 时间： 2017/3/8 18:43
 * * 描述： 封装的OkHttpUtils类
 */

public class OkHttpUtils {

    private static OkHttpUtils httpUtils;
    private final OkHttpClient mOkHttpClient;
    private final Gson mGson;
    private final Handler mHandler;

    private OkHttpUtils(){
        mOkHttpClient = new OkHttpClient();
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtils getHttpUtils(){
        if (httpUtils == null){
            synchronized (OkHttpUtils.class){
                if (httpUtils == null){
                    httpUtils = new OkHttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public <T> void loadDataFromNet(final String url,final Class<T> clazz,final CallBackListener<T> callBack){
        //实例化Request对象
        final Request request = new Request.Builder().url(url).build();
        //请求网络数据
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("TAG", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //服务器返回数据类型为String类型
                //得到gson串
                String json = response.body().string();
                final T result = mGson.fromJson(json, clazz);
                //线程转换
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //完成线程转换
                        callBack.onSuccess(result);
                    }
                });
            }
        });
    }

    public interface CallBackListener<T>{
        void onSuccess(T result);
        void onFail();
    }
}
