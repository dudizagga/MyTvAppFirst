package com.example.moree.mytvapp.Bulgaria;

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

public class BulgariaChannels extends Fragment {
    Context context;
    ArrayList<String> BgLink = new ArrayList<>();
    ArrayList<String> BgPics1 = new ArrayList<>();
    GridView myBGgrid;
    MyCountries myCountries;

    public BulgariaChannels() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        myCountries = new MyCountries();
        BgLink.clear();
        BgPics1.clear();
        // Save_BgLinks();
        Get_BgData();
        myCountries.Find(context);
        View BGChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        myBGgrid = (GridView) BGChannelInf.findViewById(R.id.MyGridView);
        myBGgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //sport
/*
                link.add("http://1.442244.info/bg_diema_sport/index.m3u8");
                link.add("http://1.442244.info/bg_diema_sport_2/index.m3u8");
                link.add("http://1.442244.info/bg_nova_sport/index.m3u8");
                link.add("http://1.442244.info/bg_sport_plus_hd/index.m3u8");
                */
                //news


                //music


                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", (BgLink.get(i)));
                context.startActivity(intent);
               /*
                switch (i)
                {
                    case 0:
                        Toast.makeText(context, " Sky Sports 2 ", Toast.LENGTH_SHORT).show();
                break;
                    case 1:
                        Toast.makeText(context, "Sky Sports 3", Toast.LENGTH_SHORT).show();
                break;
                    case 2:
                        Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                break;
                }
                */
            }
        });
        myBGgrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myCountries.MyAlertDialog1(context, BgLink.get(position), BgPics1.get(position), BgPics1.get(position), BgLink.get(position));
                return false;
            }
        });
        return BGChannelInf;
    }


    private void Save_BgLinks() {
        BGdata bGdata = new BGdata();
        bGdata.BgChannel_Link = "http://1.442244.info/bg_diema_sport/index.m3u8";
        Backendless.Persistence.of(BGdata.class).save(bGdata, new AsyncCallback<BGdata>() {
            @Override
            public void handleResponse(BGdata response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    private void Get_BgData() {
        Backendless.Persistence.of(BGdata.class).find(new AsyncCallback<BackendlessCollection<com.example.moree.mytvapp.Bulgaria.BGdata>>() {
            @Override
            public void handleResponse(BackendlessCollection<BGdata> response) {
                for (com.example.moree.mytvapp.Bulgaria.BGdata item : response.getData()) {
                    BgLink.add(item.BgChannel_Link);
                    BgPics1.add(item.BgChannel_Pic);
                }
                myBGgrid.setAdapter(new MyCountryAdapter(context, BgPics1, BgLink));

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
