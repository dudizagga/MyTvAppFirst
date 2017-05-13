package com.example.moree.mytvapp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.media.gl.SurfaceManager;
import com.example.moree.mytvapp.Catagory.Categories;
import com.example.moree.mytvapp.MyCountries.MyCountries;
import com.example.moree.mytvapp.MyFavorite.Favorite;

import java.util.List;

/**
 * Created by moree on 12/31/2016.
 */

public class Panel extends Fragment {
    Context context;
    MainActivity mainActivity;
    LinearLayout container1;
    WifiManager wifiManager;
    ConnectivityManager cm;
    FirstMain firstMain;

    public Panel() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        firstMain = (FirstMain) getActivity();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final View panel = inflater.inflate(R.layout.panel, container, false);
        TextView cate = (TextView) panel.findViewById(R.id.btnCategories);
        TextView Favorites = (TextView) panel.findViewById(R.id.Favorites);
        container1 = (LinearLayout) panel.findViewById(R.id.container1);
        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstMain.nextFragment(R.id.fcontainer, new Categories());
            }
        });
        final TextView countries = (TextView) panel.findViewById(R.id.btnCountries);
        countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                netcheck();
                firstMain.nextFragment(R.id.fcontainer, new MyCountries());
            }
        });
        Favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstMain.nextFragment(R.id.fcontainer, new Favorite());
            }
        });

        return panel;
    }

    //one
    public void netcheck() {


        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                Toast.makeText(context, activeNetwork.getExtraInfo(), Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(context, "Error Getting Data..please choose wifi internet connection", Toast.LENGTH_SHORT).show();
           //Reload();
            startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
            return;

        }
        if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            Toast.makeText(context, "connect2 to" + activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //turn on the wifi
    public void Reload() {


        final ProgressDialog resetNetwork = new ProgressDialog(context);
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final AlertDialog.Builder reload = new AlertDialog.Builder(context);
        reload.setTitle("Connection Error");
        reload.setMessage("Turn on the Wifi");
        reload.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        reload.setPositiveButton("Turn ON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetNetwork.setMessage("Turning on Wifi");
                resetNetwork.show();
                wifiManager.setWifiEnabled(true);
                Toast.makeText(context, "Wifi Turned on", Toast.LENGTH_SHORT).show();
                firstMain.nextFragment(R.id.fcontainer, new Refresh());


            }
        });
        reload.show();
    }


    private void mywifi() {

        List<ScanResult> wifilist = wifiManager.getScanResults();
        AlertDialog.Builder wifi = new AlertDialog.Builder(context);
        wifi.setTitle("Wifi List");
        View inflate = LayoutInflater.from(context).inflate(R.layout.wifi_list, null, false);
        ListView Listwifi = (ListView) inflate.findViewById(R.id.wifilist);
        // ListView Wifilist=new ListView(context);
        WifiAdapter wifiAdaper = new WifiAdapter(context, wifilist);
        //Wifilist.setAdapter(wifiAdaper);
        Listwifi.setAdapter(wifiAdaper);
        wifi.setView(inflate);
        wifi.show();

    }


}