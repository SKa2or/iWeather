package com.example.SKa2or.iWeather;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String weather_url;
    private RecyclerView recyclerView;
    private Handler handler;
    private ViewCuston custon;
    private ImageView weather_img;
    private TextView city;
    private TextView data;
    private TextView wen_du;
    private TextView inf;
    private Button btn_city;
    private Button btn_add_city;
    private String cityName="吴川";
    private SQLiteDatabase sqLiteDatabase;
    private WeatherDatabase weatherDatabase;
    private accessBroadCast abc;
    private LinearLayout info_weather;
    //private Toolbar toolbar;


    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readDatabase();
        init();
        getWeatherInfo();
    }

    private void readDatabase() {
       try{
           String name=null;
           try{
               SharedPreferences sharedPreferences=getSharedPreferences("city",AppCompatActivity.MODE_PRIVATE);
               name=sharedPreferences.getString("cityName",null);
           }catch (NullPointerException e){
               cityName="吴川";
           }
           weatherDatabase=new WeatherDatabase(this,"weather",null,1);
           sqLiteDatabase=weatherDatabase.getWritableDatabase();
           Cursor cursor=sqLiteDatabase.query("weatherInfo",null,null,null,null,null,null);
           while (cursor.moveToNext()){
               if (cursor.getString(0).equals(name)){
                   cityName=name;
               }
           }
       }catch (Exception e){
           Log.e("错误","获取数据库失败");
       }
    }

    private void getWeatherInfo() {
        Log.e("城市名称",cityName);
        weather_url="https://www.sojson.com/open/api/weather/json.shtml?city="+cityName;
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(weather_url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()){
                            refreshLayout.setRefreshing(false);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String i=response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       try{
                           if (refreshLayout.isRefreshing()){
                               refreshLayout.setRefreshing(false);
                           }
                           Gson gson=new Gson();
                           WeatherBean info=gson.fromJson(i,WeatherBean.class);
                           AdapterWeather adapterWeather=new AdapterWeather(info);
                           LinearLayoutManager llm=new LinearLayoutManager(MainActivity.this);
                           llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                           recyclerView.setLayoutManager(llm);
                           recyclerView.setAdapter(adapterWeather);
                           //toolbar.setTitle(info.getCity());


                           setAnimation(info_weather,"scaleX",0,1);
                           setAnimation(recyclerView,"scaleX",0,1);
                           AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
                           alphaAnimation.setDuration(1000);
                           custon.startAnimation(alphaAnimation);

                           custon.setInfo(info);
                           city.setText(info.getCity());
                           data.setText(info.getData().getForecast().get(0).getDate());
                           wen_du.setText(info.getData().getWendu()+" ℃");
                           inf.setText(info.getData().getForecast().get(0).getType());



                           Log.e("weather",info.getData().getForecast().get(0).getType());
                           switch (info.getData().getForecast().get(0).getType()){
                               case "多云":
                                   weather_img.setImageResource(R.drawable.w1);
                                   break;
                               case "晴":
                                   weather_img.setImageResource(R.drawable.w2);
                                   break;
                               case "阵雨":
                                   weather_img.setImageResource(R.drawable.w3);
                                   break;
                               case "中雨":
                                   weather_img.setImageResource(R.drawable.w4);
                                   break;
                               case "雷阵雨":
                                   weather_img.setImageResource(R.drawable.w5);
                               case "阴":
                                   weather_img.setImageResource(R.drawable.w6);
                               case "小雨":
                                   weather_img.setImageResource(R.drawable.w7);
                                   break;
                               default:
                                   break;
                           }
                       }catch (NullPointerException e){
                           e.printStackTrace();
                       }
                    }
                });
            }
        });
    }

    private void setAnimation(Object ob,String name,int start,int end) {
        ObjectAnimator animator=ObjectAnimator.ofFloat(ob,name,start,end);
        animator.setDuration(500);
        animator.start();
    }

    private void init() {
        abc=new accessBroadCast();
        info_weather=findViewById(R.id.info_weather);
        recyclerView=findViewById(R.id.weather);
        handler=new Handler();
        custon=findViewById(R.id.view);
        refreshLayout=findViewById(R.id.refresh);
        btn_add_city=findViewById(R.id.btn_add_city);
        btn_city=findViewById(R.id.btn_city);
        btn_city.setOnClickListener(this);
        btn_add_city.setOnClickListener(this);
        refreshLayout.setColorSchemeColors(Color.parseColor("#40E0D0"));
        //toolbar=findViewById(R.id.toolbar);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("CITY_CHANGE");
        intentFilter.addAction("CITY_ADD");
        registerReceiver(abc,intentFilter);


        city=findViewById(R.id.city);
        data=findViewById(R.id.data);
        wen_du=findViewById(R.id.wen_du);
        inf=findViewById(R.id.info);
        weather_img=findViewById(R.id.weather_img);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherInfo();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_city:
                Intent city=new Intent(this,City.class);
                startActivity(city);
                break;
            case R.id.btn_add_city:
                Intent intent=new Intent(this,AddCity.class);
                startActivityForResult(intent,2048);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(abc);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2048:
                //Toast.makeText(this,"回传值为:"+data.getStringExtra("cityName"),Toast.LENGTH_SHORT).show();
               try{
                   sqLiteDatabase=weatherDatabase.getWritableDatabase();
                  ContentValues contentValues=new ContentValues();
                  contentValues.put("city",data.getStringExtra("cityName"));
                  sqLiteDatabase.insert("weatherInfo",null,contentValues);
                  cityName=data.getStringExtra("cityName");
                   getWeatherInfo();
               }catch (Exception e){
                   e.printStackTrace();
               }
                break;
                default:
                    break;
        }
    }

    public void updataInfo(){
        SharedPreferences sharedPreferences=getSharedPreferences("city",AppCompatActivity.MODE_PRIVATE);
        cityName=sharedPreferences.getString("cityName",null);
        getWeatherInfo();
    }

    class accessBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            updataInfo();
        }
    }
}
