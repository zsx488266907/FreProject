package zsx.com.oneproject.callback;


import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    //okhttp二次封装,封装网络架构

  Handler handler = new Handler();
  private OkHttpClient okHttpClient ;

  private static   OkHttpUtils  mInstanse;

    private OkHttpUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          okHttpClient = new OkHttpClient.Builder()
                  .addInterceptor(httpLoggingInterceptor)
                  .callTimeout(5,TimeUnit.SECONDS)
                  .writeTimeout(5,TimeUnit.SECONDS)
                  .readTimeout(5,TimeUnit.SECONDS)
                  .build();

    }

    public static OkHttpUtils getmInstanse(){
        if (mInstanse==null){
            synchronized (OkHttpUtils.class){
                if (mInstanse==null){
                    mInstanse = new OkHttpUtils();
                }
            }
        }
        return mInstanse;
    }

  public void doPost(String url, HashMap<String,String> map,final OkHttpCallBack okHttpCallBack){
      FormBody.Builder builder = new FormBody.Builder();

      for (Map.Entry<String, String> maps : map.entrySet()) {
            builder.add(maps.getKey(),maps.getValue());
      }
      final RequestBody requestBody = builder.build();
      final Request request = new Request.Builder().url(url).post(requestBody).build();

    okHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            if (okHttpCallBack!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallBack.onFair("网路中断");
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
               if (okHttpCallBack!=null){
                   if (200==response.code()){
                       final String result = response.body().string();
                       handler.post(new Runnable() {
                           @Override
                           public void run() {
                               okHttpCallBack.onSuccess(result);
                           }
                       });
                   }
               }
        }
    });
  }

    public  void  cancelAllTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
