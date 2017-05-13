package com.example.moree.mytvapp.UnitedKindom;

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
import com.example.moree.mytvapp.Europ.EuData;
import com.example.moree.mytvapp.MainActivity;
import com.example.moree.mytvapp.MyCountries.MyCountries;
import com.example.moree.mytvapp.MyCountries.MyCountryAdapter;
import com.example.moree.mytvapp.Panel;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Video;

import java.util.ArrayList;
import java.util.List;

public class UKChannels extends Fragment {
    Context context;
    GridView UKchannels;
     ArrayList<String> Uklink = new ArrayList<>();
     ArrayList<String> UKpic1 = new ArrayList<>();
     ArrayList<String> GetId = new ArrayList<>();
    MyCountries myCountries;

    public UKChannels() {

    }

    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        //Save_UKLinks();
        Uklink.clear();
        UKpic1.clear();
        Get_UKData();
        context = container.getContext();
        //mainActivity = (MainActivity) getActivity();
        myCountries = new MyCountries();
        myCountries.Find(context);
        View UKChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        UKchannels = (GridView) UKChannelInf.findViewById(R.id.MyGridView);
        UKchannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//sport
                /*
                link.add(" http://mcdn-play-358.5centscdn.net:80/truemovies1extv/stream1.stream/playlist.m3u8");
                link.add(" http://mcdn-play-358.5centscdn.net:80/sky1extv/stream1.stream/playlist.m3u8");
                link.add(" http://mcdn-play-358.5centscdn.net/e4extv/stream1.stream/index.m3u8");
                link.add(" http://mcdn-play-358.5centscdn.net/bbc2extv/stream1.stream/playlist.m3u8");
                link.add(" http://mcdn-play-358.5centscdn.net/skysports1extv/stream1.stream/playlist.m3u8");
                link.add("http://mcdn-play-358.5centscdn.net/bbc1scotextv/stream1.stream/playlist.m3u8");
                link.add("http://1.442244.info/uk_premier_sport/index.m3u8");
*/
//News
                //Music
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", (Uklink.get(i)));
                context.startActivity(intent);

            }

        });
        UKchannels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                myCountries.MyAlertDialog1(context, Uklink.get(position), UKpic1.get(position), UKpic1.get(position), Uklink.get(position));

                return false;
            }
        });
        return UKchannels;
    }


    private void Save_UKLinks() {
        UKData ukData = new UKData();
        ukData.UKChannel_Link = "http://146.185.243.250:8000/play/rossija1";
        Backendless.Persistence.of(UKData.class).save(ukData, new AsyncCallback<UKData>() {
            @Override
            public void handleResponse(UKData response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void Get_UKData() {

        Backendless.Persistence.of(UKData.class).find(new AsyncCallback<BackendlessCollection<UKData>>() {
            @Override
            public void handleResponse(BackendlessCollection<UKData> response) {
                for (UKData item : response.getData()) {
                    Uklink.add(item.UKChannel_Link);
                    UKpic1.add(item.UkChannel_Pic);
                    GetId.add(item.objectId);

                }
                UKchannels.setAdapter(new MyCountryAdapter(context, UKpic1, Uklink));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

}