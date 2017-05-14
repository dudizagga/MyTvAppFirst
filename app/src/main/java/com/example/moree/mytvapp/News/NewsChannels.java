package com.example.moree.mytvapp.News;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.moree.mytvapp.MyCountries.MyCountryAdapter;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Video;

import java.util.ArrayList;


/**
 * Created by DudiZagga on 12/05/2017.
 */

public class NewsChannels extends Fragment {
    Context context;
    ArrayList<String> link;
    ArrayList<String> getNewsPics=new ArrayList<>();
    ArrayList<String> getNewsNames=new ArrayList<>();
    public GridView listNews;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        getNewsData();
        Toast.makeText(context, "News", Toast.LENGTH_SHORT).show();
        View movInf = inflater.inflate(R.layout.activity_categories, container, false);
        listNews = (GridView) movInf.findViewById(R.id.TvShow);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                Intent intent = new Intent(context,Video.class);
                intent.putExtra("link",getNewsNames.get(i));
                context.startActivity(intent);

            }

        });


        return movInf;

    }
    private void getNewsData()
    {
        Backendless.Persistence.of(NewsData.class).find(new AsyncCallback<BackendlessCollection<NewsData>>() {
            @Override
            public void handleResponse(BackendlessCollection<NewsData> response) {
                for (NewsData item:response.getData())
                {
                    getNewsNames.add(item.NewsChannel_Link);
                    getNewsPics.add(item.NewsChannel_Pic);
                }
                listNews.setAdapter(new MyCountryAdapter(context,getNewsPics,getNewsNames));
           return;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
