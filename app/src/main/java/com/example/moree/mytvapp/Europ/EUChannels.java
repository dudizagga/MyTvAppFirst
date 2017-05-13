package com.example.moree.mytvapp.Europ;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.moree.mytvapp.MyCountries.MyCountries;
import com.example.moree.mytvapp.MyCountries.MyCountryAdapter;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Video;

import java.util.ArrayList;
import java.util.List;

public class EUChannels extends Fragment {
    Context context;
    List<String> Eulink = new ArrayList<>();
    List<String> EuPics1 = new ArrayList<>();
    GridView EUchannels;
    MyCountries myCountries;

    public EUChannels() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        myCountries = new MyCountries();
        //Save_EuLinks();
        Eulink.clear();
        EuPics1.clear();
        Get_EuData();
        myCountries.Find(context);
        View EUChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        EUchannels = (GridView) EUChannelInf.findViewById(R.id.MyGridView);
        EUchannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//sport
                /*
                link.add("http://1.442244.info/tur_ntv_spor/index.m3u8");
                link.add("http://1.442244.info/tur_trt_spor/index.m3u8");
                */
//News
                //Music
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", ((Eulink.get(i))));
                context.startActivity(intent);

            }
        });
        EUchannels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myCountries.MyAlertDialog1(context, Eulink.get(position), EuPics1.get(position), EuPics1.get(position), Eulink.get(position));
                return false;
            }
        });
        return EUchannels;
    }

    private void Save_EuLinks() {
        EuData euData = new EuData();
        euData.EuChannel_Link = "http://1.442244.info/tur_ntv_spor/index.m3u8";

        Backendless.Persistence.of(EuData.class).save(euData, new AsyncCallback<EuData>() {
            @Override
            public void handleResponse(EuData response) {
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }


    private void Get_EuData() {
        Backendless.Persistence.of(EuData.class).find(new AsyncCallback<BackendlessCollection<EuData>>() {
            @Override
            public void handleResponse(BackendlessCollection<EuData> response) {
                for (EuData item : response.getData()) {
                    Eulink.add(item.EuChannel_Link);
                    EuPics1.add(item.EuChannel_Pic);

                }
                EUchannels.setAdapter(new MyCountryAdapter(context, EuPics1, Eulink));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }

        });
    }

}
