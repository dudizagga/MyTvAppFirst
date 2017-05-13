package com.example.moree.mytvapp.Israel;

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
import com.example.moree.mytvapp.MainActivity;
import com.example.moree.mytvapp.MyCountries.MyCountries;
import com.example.moree.mytvapp.MyCountries.MyCountryAdapter;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moree on 1/5/2017.
 */

public class IsraelChannels extends Fragment {
    Context context;
    ArrayList<String> ILlink = new ArrayList<>();
    ArrayList<String> ILPics1 = new ArrayList<>();
    MyCountries myCountries;
    GridView ILchannels;


    public IsraelChannels() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        //  Save_ILLinks();
        ILlink.clear();
        ILPics1.clear();
        Get_ILData();
        myCountries = new MyCountries();
        myCountries.Find(context);
        View ILChannelInf = inflater.inflate(R.layout.my_grid_view, container, false);
        ILchannels = (GridView) ILChannelInf.findViewById(R.id.MyGridView);
        ILchannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                link.add("");
//ערוץ 2
                link.add("http://keshethlslive-lh.akamaihd.net/i/c2n_1@195269/master.m3u8");
                Toast.makeText(context, "ערוץ 2", Toast.LENGTH_SHORT).show();
//sport

//News

                //Music
*/
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", ((ILlink.get(i))));
                context.startActivity(intent);
            }
        });
        ILchannels.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myCountries.MyAlertDialog1(context, ILlink.get(position), ILPics1.get(position), ILPics1.get(position), ILlink.get(position));
                return false;
            }
        });
        return ILChannelInf;
    }

    private void Save_ILLinks() {
        IsraelData israelData = new IsraelData();
        israelData.ILChannel_Link = "http://nana10-hdl-il.ctedgecdn.net/Nana10-Live/amlst:hd_,1000,1500,1800,/.m3u8";
        Backendless.Persistence.of(IsraelData.class).save(israelData, new AsyncCallback<IsraelData>() {
            @Override
            public void handleResponse(IsraelData response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    private void Get_ILData() {
        Backendless.Persistence.of(IsraelData.class).find(new AsyncCallback<BackendlessCollection<IsraelData>>() {
            @Override
            public void handleResponse(BackendlessCollection<IsraelData> response) {
                for (IsraelData item : response.getData()) {
                    ILlink.add(item.ILChannel_Link);
                    ILPics1.add(item.ILChannel_Pic);
                }
                ILchannels.setAdapter(new MyCountryAdapter(context, ILPics1, ILlink));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
