package com.example.moree.mytvapp;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moree on 4/30/2017.
 */

public class WifiAdapter extends BaseAdapter {
    List<ScanResult> Wifi;
    Context context;

    public WifiAdapter(Context context,List<ScanResult> wifi) {
        this.context=context;
        this.Wifi=wifi;
    }

    @Override
    public int getCount() {
        return Wifi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
View wifiXml= LayoutInflater.from(context).inflate(R.layout.my_wifi,null,false);
        TextView wifiName=(TextView)wifiXml.findViewById(R.id.wifiName);
       TextView type=(TextView)wifiXml.findViewById(R.id.Vtype);
            wifiName.setText(Wifi.get(position).SSID);
            type.setText(Wifi.get(position).capabilities);


        return wifiXml;
    }
}
