package com.example.moree.mytvapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class FirstMain extends AppCompatActivity {
    Context context;
    FragmentManager fm;
    FragmentTransaction ft;
    LinearLayout contain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
        setPointer();
    }

    private void setPointer() {

        this.context = this;
        contain = (LinearLayout) findViewById(R.id.fcontainer);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fcontainer, new Panel());
        ft.commit();
    }

    public String nextFragment(int container, Fragment className) {
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(container, className).addToBackStack(null);
        ft.commit();
        return null;
    }
}
