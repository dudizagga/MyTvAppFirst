package com.example.moree.mytvapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moree.mytvapp.MyCountries.MyCountries;

/**
 * Created by moree on 5/1/2017.
 */

public class Refresh extends Fragment {
    Context context;
    MainActivity mainActivity;
    FragmentManager fm;
    FragmentTransaction ft;

    public Refresh() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        mainActivity = (MainActivity) getActivity();
        final View reFresh = inflater.inflate(R.layout.refresh, container, false);
        Button RefreshButton = (Button) reFresh.findViewById(R.id.btnrefresh);
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                fm.popBackStack();
                ft.commit();
            }
        });
        return reFresh;
    }
}
