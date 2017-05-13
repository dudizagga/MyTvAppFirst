package com.example.moree.mytvapp.MyCountries;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import com.backendless.persistence.QueryOptions;
import com.example.moree.mytvapp.Bulgaria.BulgariaChannels;
import com.example.moree.mytvapp.Denmark.DenmarkChannels;
import com.example.moree.mytvapp.Europ.EUChannels;
import com.example.moree.mytvapp.FirstMain;
import com.example.moree.mytvapp.Israel.IsraelChannels;
import com.example.moree.mytvapp.Italy.ItalyChannels;
import com.example.moree.mytvapp.MyFavorite.FavoriteData;
import com.example.moree.mytvapp.Panel;
import com.example.moree.mytvapp.R;
import com.example.moree.mytvapp.Russia.RusChannels;
import com.example.moree.mytvapp.Spain.SpainChannels;
import com.example.moree.mytvapp.Turky.TurkyChannels;
import com.example.moree.mytvapp.UnitedKindom.UKChannels;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.moree.mytvapp.R.id.myBookmarks;

public class MyCountries extends Fragment {
    Context context;

   public GridView myCountryGist;
    List<String> CountryFlags = new ArrayList<>(12);
    List<String> CountryNames = new ArrayList<>();
    ArrayList<String> FOjectid = new ArrayList<>();
    ArrayList<String> Flink = new ArrayList<>();
    Panel panel;
    FirstMain firstMain;
    UKChannels ukChannels;


    int t = 0;

    public MyCountries() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        context = container.getContext();
        firstMain = (FirstMain)getActivity();
        panel = new Panel();
        Toast.makeText(context, "goting to getdata()", Toast.LENGTH_SHORT).show();
        getData();
        Toast.makeText(context, "get data() done", Toast.LENGTH_SHORT).show();

        View MyCountryInf = inflater.inflate(R.layout.my_grid_view, container, false);
        myCountryGist = (GridView) MyCountryInf.findViewById(R.id.MyGridView);
        myCountryGist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    //checked
                    case 0:
                        firstMain.nextFragment(R.id.fcontainer, new UKChannels());
                        break;
                    case 1:
                        //checked
                        firstMain.nextFragment(R.id.fcontainer, new EUChannels());

                        break;
                    case 2:
                        //checked

                        firstMain.nextFragment(R.id.fcontainer, new IsraelChannels());
                        break;
                    case 3:
                        //checked


                        firstMain.nextFragment(R.id.fcontainer, new SpainChannels());

                        break;
                    case 4:
                        //checked

                        firstMain.nextFragment(R.id.fcontainer, new RusChannels());
                        break;

                    case 5:
                        //checked
                        firstMain.nextFragment(R.id.fcontainer, new BulgariaChannels());
                        break;

                    case 6:
                        //checked

                        //activity.nextFragment(R.id.fcontainer, new CroatiaChannels());
                        Toast.makeText(context, "UN Active", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        //checked

                        firstMain.nextFragment(R.id.fcontainer, new DenmarkChannels());
                        break;
                    case 8:
                        //checked
                        firstMain.nextFragment(R.id.fcontainer, new ItalyChannels());
                        break;
                    case 9:
                        //checked
                        // panel.netcheck();

                        firstMain.nextFragment(R.id.fcontainer, new TurkyChannels());
                        break;
                    case 10:
                        //checked
                        //activity.nextFragment(R.id.fcontainer, new NetherlandChannels());
                        //panel.netcheck();

                        Toast.makeText(context, "UN Active", Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        //checked
                        //activity.nextFragment(R.id.fcontainer, new SerbiaChannels());
                        Toast.makeText(context, "UN Active", Toast.LENGTH_SHORT).show();
                        break;


                }


            }
        });
        return MyCountryInf;

    }


    private void getData() {
        //progress dailog
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Getting Data");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        /////////////////////////////////
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(100);
        queryOptions.setOffset(0);
        dataQuery.setQueryOptions(queryOptions);
        Backendless.Persistence.of(MyCountryData.class).find(dataQuery, new AsyncCallback<BackendlessCollection<MyCountryData>>() {
            @Override
            public void handleResponse(BackendlessCollection<MyCountryData> response) {
                for (MyCountryData data : response.getData()) {
                    //my Arraylist CountryFlags,CountryNames
                    CountryFlags.add(data.CountryPic);
                    CountryNames.add(data.CountryName);
                    Flink.add(data.CountryName);

                }
                Toast.makeText(context, "its empty", Toast.LENGTH_SHORT).show();
                //my GridView set adapter for MyCountryAdapter and passing context and the other Arrays
                myCountryGist.setAdapter(new MyCountryAdapter(context, CountryFlags, CountryNames));
                return;
            }


            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        progressDialog.dismiss();
    }


    //get data and add it to an array

    //my main alert



    public void Find(final Context context) {
        this.context = context;

        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        Backendless.Persistence.of(FavoriteData.class).find(new AsyncCallback<BackendlessCollection<FavoriteData>>() {
            @Override
            public void handleResponse(BackendlessCollection<FavoriteData> response) {
                for (FavoriteData item : response.getData()) {
                    Flink.add(item.FavoriteLink);

                }
                Toast.makeText(context, "Data", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void MyAlertDialog1(final Context context, final String SaveLink, final String SavePic, final String ChannelPic, final String getString) {
        this.context = context;
        Toast.makeText(context, "My Alert", Toast.LENGTH_SHORT).show();
        //my alert
        AlertDialog.Builder mysingleAlert = new AlertDialog.Builder(context);
        // my inflate for my alert
        final View FavoriteIn = LayoutInflater.from(context).inflate(R.layout.alert_favorite, null, false);
        //my bookmark sign
        final Button bookmarks = (Button) FavoriteIn.findViewById(myBookmarks);
        //my image
        final ImageView Image = (ImageView) FavoriteIn.findViewById(R.id.myimg);
        Picasso.with(context)
                .load(ChannelPic)
                .into(Image);
        if (Flink.isEmpty()) {
            bookmarks.setTextColor(Color.WHITE);
        }
        if (Flink.contains(SaveLink)) {
            Toast.makeText(context, "Channel Exists", Toast.LENGTH_SHORT).show();
            bookmarks.setTextColor(Color.YELLOW);
            mysingleAlert.setView(FavoriteIn);
            mysingleAlert.show();
            return;
        } else {
            bookmarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog pd = new ProgressDialog(context);
                    pd.setMessage("Loading");
                    pd.setCancelable(false);
                    pd.show();
                    Backendless.Persistence.of(FavoriteData.class).find(new AsyncCallback<BackendlessCollection<FavoriteData>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<FavoriteData> response) {
                            for (FavoriteData item : response.getData()) {
                                Flink.add(item.FavoriteLink);
                            }

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });
                    if (Flink.contains(SaveLink)) {
                        bookmarks.setTextColor(Color.YELLOW);
                        Backendless.Persistence.of(FavoriteData.class).find(new AsyncCallback<BackendlessCollection<FavoriteData>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<FavoriteData> response) {
                                for (FavoriteData item : response.getData()) {
                                    Flink.add(item.FavoriteLink);
                                    pd.dismiss();
                                }
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                            }
                        });
                        pd.dismiss();
                        return;
                    } else {
                        FavoriteData fData = new FavoriteData();
                        fData.FavoriteLink = SaveLink;
                        fData.FavoritePic = SavePic;
                        Backendless.Persistence.of(FavoriteData.class).save(fData, new AsyncCallback<FavoriteData>() {
                            @Override
                            public void handleResponse(FavoriteData response) {
                                Toast.makeText(context, "Channel Saved", Toast.LENGTH_SHORT).show();
                                bookmarks.setTextColor(Color.YELLOW);
                                Find(context);
                                pd.dismiss();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                            }
                        });
                        return;
                    }

                }
            });
        }

        mysingleAlert.setView(FavoriteIn);
        mysingleAlert.show();
    }



    public void DeleteData(String ojectId) {
        FavoriteData fav = new FavoriteData();
        fav.objectId = ojectId;
        Backendless.Persistence.of(FavoriteData.class).remove(fav, new AsyncCallback<Long>() {
            @Override
            public void handleResponse(Long response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}








