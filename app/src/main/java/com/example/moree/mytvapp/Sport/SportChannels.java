package com.example.moree.mytvapp.Sport;

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
 * Created by moree on 2/27/2017.
 */

public class SportChannels extends Fragment {
    Context context;
    ArrayList<String> link;
    ArrayList<String> getSportsPics=new ArrayList<>();
    ArrayList<String> getSportNames=new ArrayList<>();
    public GridView listSports;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();

        // savedata();
        getSportData();
        Toast.makeText(context, "TvShow", Toast.LENGTH_SHORT).show();
        View movInf = inflater.inflate(R.layout.activity_categories, container, false);
        listSports = (GridView) movInf.findViewById(R.id.TvShow);
        listSports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




                Intent intent = new Intent(context,Video.class);
                intent.putExtra("link",getSportNames.get(i));
                context.startActivity(intent);

            }

        });


        return movInf;

    }
    private void getSportData()
    {
        Backendless.Persistence.of(SportData.class).find(new AsyncCallback<BackendlessCollection<SportData>>() {
            @Override
            public void handleResponse(BackendlessCollection<SportData> response) {
                for (SportData item:response.getData())
                {
                    getSportNames.add(item.SportChannel_Link);
                    getSportsPics.add(item.SportChannel_Pic);
                }
                listSports.setAdapter(new MyCountryAdapter(context,getSportsPics,getSportNames));
return;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }


}
