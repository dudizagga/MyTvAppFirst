package com.example.moree.mytvapp.Movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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

public class MoviesChannels extends Fragment {
    Context context;
    ArrayList<String> link;
    ArrayList<String> getTvShowsPics = new ArrayList<>();
    ArrayList<String> getTvShowsNames = new ArrayList<>();
    public GridView listTvShows;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();

        //saveTvShow();
        getTvShow();
        Toast.makeText(context, "TvShow", Toast.LENGTH_SHORT).show();
        View movInf = inflater.inflate(R.layout.activity_categories, container, false);
        listTvShows = (GridView) movInf.findViewById(R.id.TvShow);
        listTvShows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//check point
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("link", (getTvShowsNames.get(i)));
                context.startActivity(intent);

/*
                Intent intent = new Intent(context,Video.class);
                intent.setData(Uri.parse(String.valueOf(getTvShowsNames.get(i))));
                context.startActivity(intent);
*/
            }

        });


        return movInf;

    }




    private void saveTvShow() {
        MoviesData MoviesData = new MoviesData();
        MoviesData.MoviesChannel_Link = "dasdasda";
        MoviesData.MoviesChannel_Pic = "dsfsdfsd";
        Backendless.Persistence.of(MoviesData.class).save(MoviesData, new AsyncCallback<com.example.moree.mytvapp.Movies.MoviesData>() {
            @Override
            public void handleResponse(MoviesData response) {
                Toast.makeText(context, "Movies data was saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    private void getTvShow() {

        Backendless.Persistence.of(MoviesData.class).find(new AsyncCallback<BackendlessCollection<MoviesData>>() {
            @Override
            public void handleResponse(BackendlessCollection<MoviesData> response) {
                for (MoviesData item : response.getData()) {
                    getTvShowsNames.add(item.MoviesChannel_Link);
                    getTvShowsPics.add(item.MoviesChannel_Pic);
                }
                listTvShows.setAdapter(new MyCountryAdapter(context, getTvShowsPics, getTvShowsNames));
                return;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
