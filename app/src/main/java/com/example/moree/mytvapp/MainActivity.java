package com.example.moree.mytvapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.example.moree.mytvapp.MyCountries.MyCountries;
import com.example.moree.mytvapp.UnitedKindom.UKChannels;

import static com.example.moree.mytvapp.R.id.Favorites;
import static com.example.moree.mytvapp.R.id.myBookmarks;

public class MainActivity extends AppCompatActivity {
    Context context;
    EditText userId, usePass;
    Button Login, register;
    FirstMain firstMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.initApp(this, "B69DDA46-E458-378B-FF0E-F5F182F4A800", "B506595D-64F7-320B-FF90-227125992900", "v1");
        setPointer();

    }

    private void setPointer() {
        this.context = this;
        userId = (EditText) findViewById(R.id.userName);
        usePass = (EditText) findViewById(R.id.usePass);
        Login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.InRegister);
        firstMain = new FirstMain();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    private void Login() {
        Toast.makeText(context, "myLogin", Toast.LENGTH_SHORT).show();
        Intent First = new Intent(context, FirstMain.class);
        startActivity(First);

    }

    private void Register() {
        AlertDialog.Builder myRegister = new AlertDialog.Builder(context);
        View inflateRegister = LayoutInflater.from(context).inflate(R.layout.register, null, false);
         final EditText username = (EditText) inflateRegister.findViewById(R.id.RegisterName);
         final EditText userpass1 = (EditText) inflateRegister.findViewById(R.id.userPass1);
         final EditText userpass2 = (EditText) inflateRegister.findViewById(R.id.userPass2);
      myRegister.setPositiveButton("REgister", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(final DialogInterface dialog, int which) {
              if (userpass1.getText().toString().equals(userpass2.getText().toString()))
              {
                  BackendlessUser user = new BackendlessUser();
                  BackendlessDataQuery users=new BackendlessDataQuery();

                  user.setEmail(username.getText().toString());
                  user.setPassword(userpass1.getText().toString());
                  Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
                      @Override
                      public void handleResponse(BackendlessUser response) {
                          Toast.makeText(context, "Registed", Toast.LENGTH_SHORT).show();
                      }
                  });

              }


          }
      });

        myRegister.setView(inflateRegister);
        myRegister.show();

    }


}
