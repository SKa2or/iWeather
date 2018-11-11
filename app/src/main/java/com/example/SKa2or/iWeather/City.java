package com.example.SKa2or.iWeather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 选择已添加过城市的活动
 */
public class City extends AppCompatActivity implements ChangeCity,View.OnClickListener{
    private RecyclerView change_city;
    private SQLiteDatabase sqLiteDatabase;
    private WeatherDatabase weatherDatabase;
    private List<String> cityName;
    private String weather_url;
    private Handler handler;
    private List<WeatherBean> list;
    private ChangeCityItemAdapter changeCityItem;
    private ImageButton city_back;
    private Button city_add;
    private accessBroadCast abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        readDatabase();
        init();
    }

    //读取数据库中的数据及重网上下载对应的数据
    private void readDatabase() {
        list=new ArrayList<>();
        handler=new Handler();
        weather_url="https://www.sojson.com/open/api/weather/json.shtml?city=";
        try{
            cityName=new ArrayList<>();
            weatherDatabase=new WeatherDatabase(this,"weather",null,1);
            sqLiteDatabase=weatherDatabase.getWritableDatabase();
            final Cursor cursor=sqLiteDatabase.query("weatherInfo",null,null,null,null,null,null);
            while (cursor.moveToNext()){
                cityName.add(cursor.getString(0));
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(weather_url+cursor.getString(0))
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String i=response.body().string();
                        Log.e("json数据为",i);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Gson gson=new Gson();
                                    list.add(gson.fromJson(i,WeatherBean.class));
                                    if (list.size()==cursor.getCount()){
                                        changeCityItem.notifyDataSetChanged();
                                    }
                                }catch (NullPointerException e){
                                    Log.e("设置数据出错","错误");
                                }
                            }
                        });
                    }
                });
            }
        }catch (Exception e){
            Log.e("错误","获取数据库失败");
        }
    }

    private void init() {
        change_city=findViewById(R.id.change_city);
        changeCityItem=new ChangeCityItemAdapter(list,this);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        change_city.setLayoutManager(llm);
        change_city.setAdapter(changeCityItem);
        city_add=findViewById(R.id.add_city);
        city_back=findViewById(R.id.city_back);
        city_add.setOnClickListener(this);
        city_back.setOnClickListener(this);

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("CITY_ADD");
        registerReceiver(abc,intentFilter);
    }

    @Override
    public void changeCity(String cityName) {
        SharedPreferences saveCity=getSharedPreferences("city", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editorCity=saveCity.edit();
        editorCity.putString("cityName",cityName);
        editorCity.commit();
        Intent intent=new Intent();
        intent.setAction("CITY_CHANGE");
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.city_back:
                finish();
                break;
            case R.id.add_city:
                Intent intent=new Intent(this,AddCity.class);
                startActivity(intent);
                break;
                default:
                    break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            unregisterReceiver(abc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class accessBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            readDatabase();
        }
    }
}
