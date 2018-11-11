package com.example.SKa2or.iWeather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * 常用城市的recyclerview的适配器
 */
public class AddCityItemAdapter extends RecyclerView.Adapter<AddCityItemAdapter.Aci> {
    private List<String> list;
    private SetCity setCity;

    public AddCityItemAdapter(List<String> listAddCityItem, SetCity setCity){
        list=listAddCityItem;
        this.setCity=setCity;
    }
    @Override
    public Aci onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Aci(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_city_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final Aci holder, int position) {
        holder.city.setText(list.get(position));
        holder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCity.setCity(holder.city.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Aci extends RecyclerView.ViewHolder {
        private Button city;
        public Aci(View itemView) {
            super(itemView);
            city=itemView.findViewById(R.id.btn_host_city_item);
        }
    }
}
