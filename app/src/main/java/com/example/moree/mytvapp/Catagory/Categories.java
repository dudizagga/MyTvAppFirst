package com.example.moree.mytvapp.Catagory;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.moree.mytvapp.FirstMain;
import com.example.moree.mytvapp.Music.MusicChannels;
import com.example.moree.mytvapp.MyCountries.MyCountryAdapter;
import com.example.moree.mytvapp.News.NewsChannels;
import com.example.moree.mytvapp.Sport.SportChannels;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Movies.MoviesChannels;

import java.util.ArrayList;

/**
 * Created by moree on 1/2/2017.
 */

public class Categories extends Fragment {
    Context context;
    ArrayList<String> link;
    ArrayList<String> getCatagoryPics=new ArrayList<>();
    ArrayList<String> getCatagoryNames=new ArrayList<>();
    public GridView list;
    public Categories() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        final FirstMain first=(FirstMain)getActivity();
       // savedata();
        getCatagoryNames.clear();
        getCatagoryPics.clear();
         getPic();
        View movInf = inflater.inflate(R.layout.activity_categories, container, false);
       list = (GridView) movInf.findViewById(R.id.TvShow);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
switch (i)
{
    case 0:
        Toast.makeText(context, "sports", Toast.LENGTH_SHORT).show();
        first.nextFragment(R.id.fcontainer,new SportChannels());
       break;
    case 1:
        Toast.makeText(context, "News", Toast.LENGTH_SHORT).show();
        first.nextFragment(R.id.fcontainer,new NewsChannels());
        break;
    case 2:
        Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show();
        first.nextFragment(R.id.fcontainer,new MusicChannels());
        break;
    case 3:
        Toast.makeText(context, "tv show", Toast.LENGTH_SHORT).show();
        first.nextFragment(R.id.fcontainer,new MoviesChannels());
        break;
}

/*

                Intent intent = new Intent(context,Video.class);
                intent.setData(Uri.parse(String.valueOf(link.get(i))));
                context.startActivity(intent);
*/
            }

        });


        return movInf;

    }

    /*
    public   ArrayList<String> link(){
        link=new ArrayList<>();
        link.add(" http://1.442244.info/cro_sport_klub_2/index.m3u8");
        link.add(" http://1.442244.info/fin_mtv_sport_1_hd/index.m3u8");
        return link;
    }
    */
    private void savedata()
    {
       CatagoeryData cata=new CatagoeryData();
        cata.cataName="fsfd";
        Backendless.Persistence.of(CatagoeryData.class).save(cata, new AsyncCallback<CatagoeryData>() {
            @Override
            public void handleResponse(CatagoeryData response) {
                Toast.makeText(context, "name WAs saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
    private void getPic()
    {
        Backendless.Persistence.of(CatagoeryData.class).find(new AsyncCallback<BackendlessCollection<CatagoeryData>>() {
            @Override
            public void handleResponse(BackendlessCollection<CatagoeryData> response) {
              for (CatagoeryData data:response.getData())
              {
                  getCatagoryPics.add(data.cataImg);
                  getCatagoryNames.add(data.cataName);
              }
                list.setAdapter(new MyCountryAdapter(context,getCatagoryPics,getCatagoryNames));
                return;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}

