package com.example.SKa2or.iWeather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 五天内的天气情况的recyclerview适配器
 * Created by yls on 2018/4/26.
 */

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.Ad> {
    private WeatherBean weatherBean;

    public AdapterWeather(WeatherBean weatherBean){
        this.weatherBean=weatherBean;
    }

    @Override
    public Ad onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Ad(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather,parent,false));
    }

    @Override
    public void onBindViewHolder(Ad holder, int position) {
        try{
            String i=weatherBean.getData().getForecast().get(position).getDate();

            holder.inf.setText(i.substring(i.indexOf("星"),i.length()));
            switch (weatherBean.getData().getForecast().get(position).getType()){
                case "多云":
                    holder.imageView.setImageResource(R.drawable.w1);
                    break;
                case "晴":
                    holder.imageView.setImageResource(R.drawable.w2);
                    break;
                case "阵雨":
                    holder.imageView.setImageResource(R.drawable.w3);
                    break;
                case "中雨":
                    holder.imageView.setImageResource(R.drawable.w4);
                    break;
                case "雷阵雨":
                    holder.imageView.setImageResource(R.drawable.w5);
                case "阴":
                    holder.imageView.setImageResource(R.drawable.w6);
                case "小雨":
                    holder.imageView.setImageResource(R.drawable.w7);
                    break;
                default:
                    break;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        int count=0;
        try{
            count=weatherBean.getData().getForecast().size();
        }catch (NullPointerException e){
            count=0;
        }

        return count;
    }

    public class Ad extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView inf;
        public Ad(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            inf=itemView.findViewById(R.id.info);
        }
    }
}
