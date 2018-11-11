package com.example.SKa2or.iWeather;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加城市的活动
 */
public class AddCity extends AppCompatActivity implements SetCity,View.OnClickListener{
    private EditText input_city;
    private ImageButton back;
    private Button location;
    private RecyclerView host_city;
    private String cityName;
    private SQLiteDatabase sqLiteDatabase;
    private WeatherDatabase weatherDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acity);
        init();

    }

    private void init() {
        weatherDatabase=new WeatherDatabase(this,"weather",null,1);
        input_city=findViewById(R.id.input_city);
        back=findViewById(R.id.back);
        location=findViewById(R.id.location);
        host_city=findViewById(R.id.host_city);
        location.setOnClickListener(this);
        back.setOnClickListener(this);


        //设置常用城市
        List<String> list=new ArrayList<>();
        list.add("北京"); list.add("天津"); list.add("上海"); list.add("重庆"); list.add("长沙"); list.add("沈阳"); list.add("长春"); list.add("哈尔滨");
        list.add("成都"); list.add("广州"); list.add("杭州"); list.add("香港"); list.add("澳门"); list.add("青岛"); list.add("厦门"); list.add("深圳");
        AddCityItemAdapter addCityItem=new AddCityItemAdapter(list,AddCity.this);
        GridLayoutManager glm=new GridLayoutManager(AddCity.this,4);
        host_city.setLayoutManager(glm);
        host_city.setAdapter(addCityItem);
    }

    @Override
    public void setCity(String cityName) {
        //保存当前选中的城市，当程序启动时，会读取city中的值来获取选中城市
        SharedPreferences sharedPreferences=getSharedPreferences("city",AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("cityName",cityName);
        editor.commit();

        try{
            //将选中的城市保存到数据库
            sqLiteDatabase=weatherDatabase.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("city",cityName);
            sqLiteDatabase.insert("weatherInfo",null,contentValues);
        }catch (NullPointerException e){
            Log.e("添加城市异常","未写入数据库");
        }

        this.cityName=cityName;
        //Toast.makeText(this,cityName,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("cityName",cityName);
        setResult(1024,intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.location:
                cityName=input_city.getText().toString();
                if (cityName==null || cityName.equals("") || cityName.equals(" ") || cityName.contains("1234567890")){
                    Toast.makeText(this,"请输入合法的地址",Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    sqLiteDatabase=weatherDatabase.getWritableDatabase();
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("city",cityName);
                    sqLiteDatabase.insert("weatherInfo",null,contentValues);
                }catch (NullPointerException e){
                    Log.e("添加城市异常","未写入数据库");
                }

                //作用与setCity（）中的方法一致.
                SharedPreferences sharedPreferences=getSharedPreferences("city",AppCompatActivity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("cityName",cityName);
                editor.commit();

                Intent intent=new Intent();
                intent.setAction("CITY_ADD");
                sendBroadcast(intent);
                finish();
                break;
            case R.id.back:
                finish();
                default:
                    break;
        }
    }
}
