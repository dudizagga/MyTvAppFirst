package com.example.moree.mytvapp.Russia;

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
 * Created by moree on 1/5/2017.
 */

public class RusChannels extends Fragment {
    Context context;
    ArrayList<String> Ruslink = new ArrayList<>();
    ArrayList<String> RusPics1 = new ArrayList<>();
     GridView RUschannels;
    MyCountries myCountries;

    public RusChannels() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Save_RusLinks();
        Ruslink.clear();
        RusPics1.clear();
        Get_RusData();
        context = container.getContext();
        myCountries=new MyCountries();
        myCountries.Find(context);
        View RUSChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        RUschannels= (GridView) RUSChannelInf.findViewById(R.id.MyGridView);
        RUschannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Sport
                /*

               link.add("http://146.185.243.250:8000/play/rossija1");
                */
                //News
                //Music
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", (Ruslink.get(i)));
                context.startActivity(intent);
            }
        });
        RUschannels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myCountries.MyAlertDialog1(context, Ruslink.get(position), RusPics1.get(position),RusPics1.get(position),Ruslink.get(position));
                return false;
            }
        });
        return RUSChannelInf;
    }


    private void Save_RusLinks() {
        RusData rusData = new RusData();
        rusData.RusChannel_Link = "http://146.185.243.250:8000/play/rossija1";
        Backendless.Persistence.of(RusData.class).save(rusData, new AsyncCallback<RusData>() {
            @Override
            public void handleResponse(RusData response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    private void Get_RusData() {
        Backendless.Persistence.of(RusData.class).find(new AsyncCallback<BackendlessCollection<RusData>>() {
            @Override
            public void handleResponse(BackendlessCollection<RusData> response) {
                for (RusData item : response.getData()) {
                    Ruslink.add(item.RusChannel_Link);
                    RusPics1.add(item.RusChannel_Pic);
                }
                RUschannels.setAdapter(new MyCountryAdapter(context,RusPics1,Ruslink));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
