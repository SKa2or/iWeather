package com.example.SKa2or.iWeather;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 选择已在数据库中的城市的recyclerview
 */
public class ChangeCityItemAdapter extends RecyclerView.Adapter<ChangeCityItemAdapter.Cct> {
    private List<WeatherBean> list;
    private ChangeCity changeCity;

    public ChangeCityItemAdapter(List<WeatherBean> list,ChangeCity changeCity){
        this.list=list;
        this.changeCity=changeCity;
    }
    @Override
    public Cct onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Cct(LayoutInflater.from(parent.getContext()).inflate(R.layout.change_city_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final Cct holder, int position) {
        try{
            String height=list.get(position).getData().getForecast().get(0).getHigh();
            String low=list.get(position).getData().getForecast().get(0).getLow();
            holder.city_item_name.setText(list.get(position).getCity());
            holder.data_item.setText(list.get(position).getDate());
            holder.city_item_wendu.setText(list.get(position).getData().getWendu()+"℃");
            holder.data_item_wendu_detail.setText(height.substring(height.indexOf(" "),height.length())+"/"+low.substring(height.indexOf(" "),low.length()));

            switch (list.get(position).getData().getForecast().get(position).getType()) {
                case "多云":
                    holder.city_item_img.setImageResource(R.drawable.w1);
                    break;
                case "晴":
                    holder.city_item_img.setImageResource(R.drawable.w2);
                    break;
                case "阵雨":
                    holder.city_item_img.setImageResource(R.drawable.w3);
                    break;
                case "中雨":
                    holder.city_item_img.setImageResource(R.drawable.w4);
                    break;
                case "雷阵雨":
                    holder.city_item_img.setImageResource(R.drawable.w5);
                case "阴":
                    holder.city_item_img.setImageResource(R.drawable.w6);
                case "小雨":
                    holder.city_item_img.setImageResource(R.drawable.w7);
                    break;
                default:
                    break;
            }
            holder.change_city_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeCity.changeCity(holder.city_item_name.getText().toString());
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("错误提示","设置城市出现错误");
        }
    }

    @Override
    public int getItemCount() {
        int count=0;
        try{
            count=list.size();
        }catch (Exception e){
            count=0;
        }
        return list.size();
    }

    public class Cct extends RecyclerView.ViewHolder {
        private TextView city_item_name;
        private TextView data_item;
        private ImageView city_item_img;
        private TextView city_item_wendu;
        private TextView data_item_wendu_detail;
        private LinearLayout change_city_all;
        public Cct(View itemView) {
            super(itemView);
            data_item=itemView.findViewById(R.id.data_item);
            city_item_img=itemView.findViewById(R.id.city_item_img);
            city_item_name=itemView.findViewById(R.id.city_item_name);
            city_item_wendu=itemView.findViewById(R.id.city_item_wendu);
            data_item_wendu_detail=itemView.findViewById(R.id.data_item_wendu_detail);
            change_city_all=itemView.findViewById(R.id.change_city_all);
        }
    }
}
