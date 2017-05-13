package com.example.moree.mytvapp.Spain;

import android.app.Fragment;
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

/**
 * Created by moree on 1/9/2017.
 */

public class SpainChannels extends Fragment {
    Context context;
    ArrayList<String> Spainlink = new ArrayList<>();
    ArrayList<String> SpainPics1 = new ArrayList<>();
    GridView SPchannels;
    MyCountries myCountries;

    public SpainChannels() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        //Save_SpainLinks();
        context = container.getContext();
        Spainlink.clear();
        SpainPics1.clear();
        Get_SpainData();
        myCountries = new MyCountries();
        myCountries.Find(context);
        View SPChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        SPchannels = (GridView) SPChannelInf.findViewById(R.id.MyGridView);
        SPchannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//sport
                /*
                link.add("http://1.442244.info/sp_la_1/index.m3u8");
                link.add("http://1.442244.info/sp_la_2/index.m3u8");
                link.add("http://1.442244.info/sp_laligatv_bar/index.m3u8");
                link.add("http://1.442244.info/sp_bein_laliga/index.m3u8");
                link.add("http://1.442244.info/sp_m_fotbol/index.m3u8");
                link.add("http://1.442244.info/sp_m_dep_1/index.m3u8");
*/
//News
                //Music
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", (Spainlink.get(i)));
                context.startActivity(intent);
            }
        });
        SPchannels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myCountries.MyAlertDialog1(context, Spainlink.get(position), SpainPics1.get(position), SpainPics1.get(position), Spainlink.get(position));
                return false;
            }
        });
        return SPchannels;
    }

    private void Save_SpainLinks() {
        SpainData spainData = new SpainData();
        spainData.SpainChannel_Link = "http://146.185.243.250:8000/play/rossija1";
        Backendless.Persistence.of(SpainData.class).save(spainData, new AsyncCallback<SpainData>() {
            @Override
            public void handleResponse(SpainData response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    private void Get_SpainData() {
        Backendless.Persistence.of(SpainData.class).find(new AsyncCallback<BackendlessCollection<SpainData>>() {
            @Override
            public void handleResponse(BackendlessCollection<SpainData> response) {
                for (SpainData item : response.getData()) {
                    Spainlink.add(item.SpainChannel_Link);
                    SpainPics1.add(item.SpainChannel_Pic);
                }
                SPchannels.setAdapter(new MyCountryAdapter(context, SpainPics1, Spainlink));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
