package com.example.SKa2or.iWeather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义布局
 * Created by yls on 2018/4/26.
 */

public class ViewCuston extends View {
    private static WeatherBean weatherBean;
    private Paint paint;

    public ViewCuston(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(60);
        canvas.save();
        canvas.drawText("气温变化：",100,150,paint);
        canvas.drawLine(0,200,canvas.getWidth(),200,paint);
        canvas.restore();
        paint.setTextSize(40);
        try{
            for (int i=0;i<weatherBean.getData().getForecast().size();i++){
                String a=weatherBean.getData().getForecast().get(i).getHigh();
                String b=a.substring(a.indexOf("温")+2,a.length()-1);
                float h=Float.valueOf(b);
                canvas.save();
                canvas.drawCircle((canvas.getWidth()/6)*(i+1),600-h*10,10,paint);
                canvas.drawText(b+"",(canvas.getWidth()/6)*(i+1)-40,600-h*12,paint);

                if (i<weatherBean.getData().getForecast().size()-1){
                    String c=weatherBean.getData().getForecast().get(i+1).getHigh();
                    String d=c.substring(c.indexOf("温")+2,c.length()-1);
                    float e=Float.valueOf(d);
                    canvas.drawLine((canvas.getWidth()/6)*(i+1),600-h*10,(canvas.getWidth()/6)*(i+2),600-e*10,paint);
                }
                canvas.restore();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        paint.setTextSize(40);
        try{
            for (int i=0;i<weatherBean.getData().getForecast().size();i++){
                String a=weatherBean.getData().getForecast().get(i).getLow();
                String b=a.substring(a.indexOf("温")+2,a.length()-1);
                float h=Float.valueOf(b);
                canvas.save();
                canvas.drawCircle((canvas.getWidth()/6)*(i+1),800-h*10,10,paint);
                canvas.drawText(b+"",(canvas.getWidth()/6)*(i+1)-40,800-h*12,paint);

                if (i<weatherBean.getData().getForecast().size()-1){
                    String c=weatherBean.getData().getForecast().get(i+1).getLow();
                    String d=c.substring(c.indexOf("温")+2,c.length()-1);
                    float e=Float.valueOf(d);
                    canvas.drawLine((canvas.getWidth()/6)*(i+1),800-h*10,(canvas.getWidth()/6)*(i+2),800-e*10,paint);
                }
                canvas.restore();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        canvas.save();
        canvas.drawLine(0,850,canvas.getWidth(),850,paint);
        try{
            paint.setTextSize(50);
            canvas.drawText("日落",canvas.getWidth()/4-50,1000,paint);
            canvas.drawText("风力",((canvas.getWidth()/4)*3)-50,1000,paint);
            canvas.drawText("日出",canvas.getWidth()/4-50,1300,paint);
            canvas.drawText("api",((canvas.getWidth()/4)*3)-50,1300,paint);
            canvas.drawText("风向",((canvas.getWidth()/4))-50,1600,paint);
            canvas.drawText("湿度",((canvas.getWidth()/4)*3)-50,1600,paint);
            canvas.drawText("质量",((canvas.getWidth()/4))-50,1900,paint);
            canvas.drawText("PM25",((canvas.getWidth()/4)*3)-50,1900,paint);

            canvas.drawText(weatherBean.getData().getForecast().get(0).getSunset(),canvas.getWidth()/4-45,1080,paint);
            canvas.drawText(weatherBean.getData().getForecast().get(0).getFl(),((canvas.getWidth()/4)*3)-45,1080,paint);
            canvas.drawText(weatherBean.getData().getForecast().get(0).getSunrise(),canvas.getWidth()/4-45,1380,paint);
            canvas.drawText(weatherBean.getData().getForecast().get(0).getAqi()+"",((canvas.getWidth()/4)*3)-45,1380,paint);
            canvas.drawText(weatherBean.getData().getForecast().get(0).getFx()+"",((canvas.getWidth()/4))-45,1680,paint);
            canvas.drawText(weatherBean.getData().getShidu()+"",((canvas.getWidth()/4)*3)-45,1680,paint);
            canvas.drawText(weatherBean.getData().getQuality()+"",((canvas.getWidth()/4))-45,1980,paint);
            canvas.drawText(weatherBean.getData().getPm25()+"",((canvas.getWidth()/4)*3)-45,1980,paint);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        canvas.drawLine(canvas.getWidth()/2,850,canvas.getWidth()/2,canvas.getHeight(),paint);
        canvas.drawLine(0,1150,canvas.getWidth(),1150,paint);
        canvas.drawLine(0,1450,canvas.getWidth(),1450,paint);
        canvas.drawLine(0,1750,canvas.getWidth(),1750,paint);
        canvas.drawLine(0,canvas.getHeight(),canvas.getWidth(),canvas.getHeight(),paint);
        canvas.restore();
        invalidate();
    }

    public void setInfo(WeatherBean weatherBean){
        this.weatherBean=weatherBean;
    }
}
